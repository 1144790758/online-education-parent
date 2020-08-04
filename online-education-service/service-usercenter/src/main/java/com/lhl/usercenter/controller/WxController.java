package com.lhl.usercenter.controller;

import com.google.gson.Gson;
import com.lhl.commonUtils.CustomException;
import com.lhl.servicebase.JwtUtils;
import com.lhl.usercenter.domain.UcenterMember;
import com.lhl.usercenter.mapper.UcenterMemberMapper;
import com.lhl.usercenter.service.UcenterMemberService;
import com.lhl.usercenter.utils.WxProperties;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @athor:lhl
 * 用于微信登录的模块
 */
@Controller
@RequestMapping("/api")//公共的模块就叫api了,懒得在搞nginx的路由了
// *注意* 由于我们用的是尚硅谷的注册app,他访问后302重定向了的,我们要根据他的url进行请求处理
//@CrossOrigin
public class WxController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UcenterMemberService ucenterService;

    @Autowired
    RestTemplate template;

    @GetMapping("/ucenter/wx/callback")
    public String callback(String code, String state, HttpServletRequest request,HttpServletResponse response) {
        //开发文档中说会返回两个参数,state是我们请求时就发的,可以做验证操作
//        System.out.println(code);
//        System.out.println(state);
        String session = request.getSession().getId();
        String state_redis = redisTemplate.opsForValue().get(session);

        String token="";
        UcenterMember member = null;

        if (state_redis.equals(state)) {
            //验证成功,拿着code换access_token和openId
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";

            String accessTokenUrl = String.format(baseAccessTokenUrl,
                    WxProperties.AppId,
                    WxProperties.AppSecret,
                    code);
            String result = template.getForObject(accessTokenUrl, String.class);
            System.out.println(result);

            Gson gson = new Gson();
            Map map = gson.fromJson(result, HashMap.class);
            String access_token = (String) map.get("access_token");
            String openid = (String) map.get("openid");


            //在数据库中检索是否已经注册过了
            if (ucenterService.selectByOpenId(openid)<=0){
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(baseUserInfoUrl, access_token, openid);

                //发送请求
                String userInfo = template.getForObject(userInfoUrl, String.class);
                System.out.println(userInfo);
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);

                //没有注册过添加进去

                member.setOpenid(openid);
                member.setNickname((String) userInfoMap.get("nickname"));
                member.setAvatar((String) userInfoMap.get("headimgurl"));
                ucenterService.save(member);
            }else {
                //从数据库中查注册过后的
                member = ucenterService.getByOpenId(openid);
            }
//            String s = gson.toJson(member);
//            //通过cookie 能实现但是无法跨域,本机倒是可以(其实不可以),都是localhost
//            //终极解决方法放在url的token中
//                Cookie member2=new Cookie("user_info",s);
//                member2.setDomain("localhost");
//                response.addCookie(member2);
            token = JwtUtils.getJwtToken(member.getId(), member.getNickname());

        } else {
            throw new CustomException(403, "验证失败,请求超时");
        }
        return "redirect:http://localhost:3000?token="+token; //返回首页面
    }

    //发送微信认证的请求
    @GetMapping("/wx_auth_send_request")
    public String wx_auth(HttpServletRequest request) throws UnsupportedEncodingException {
        String url = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        String url2 = URLEncoder.encode(WxProperties.RedirectUrl, "utf-8");
        String state = UUID.randomUUID().toString().substring(0, 6);//生成6位的UUID,放到redis中
        // 采用redis等进行缓存state 使用sessionId为key 1分钟后过期
        String sessionId = request.getSession().getId();
        redisTemplate.opsForValue().set(sessionId, state, 1, TimeUnit.MINUTES);
        System.out.println("sessionID:" + sessionId);
        System.out.println("state:" + state);
        String lhl_url = String.format(url, WxProperties.AppId, WxProperties.RedirectUrl, state);
        return "redirect:" + lhl_url;
    }
}
