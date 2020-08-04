package com.lhl.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.lhl.servicebase.RedisUtil;
import com.lhl.sms.service.SmsService;
import com.lhl.sms.utils.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @athor:lhl
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    RedisUtil redisUtil;

    @Value("${accessKeyId}")
    private String accessKeyId;

    @Value("${accessKeySecret}")
    private String accessSecret;

    @Override
    public String sendSms(String phone) {
            long expireTime = redisUtil.getExpire(phone);
            if (expireTime<60){
                //小于60说明可以重新发送了,并重新设置缓存的值与时间
                String code = CodeUtils.randomCode(4);
                boolean ali = sendSmsAli(phone, code);
                if (ali){
                    redisUtil.set(phone,code,60*3);//验证码3分钟失效
                    return "验证码发送成功";
                }return "发送失败,请校验手机号";

            }else {
                return "请稍后再发送验证码";
            }
    }

    public boolean sendSmsAli(String phone,String code){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "LhlOnlineEdu");
        request.putQueryParameter("TemplateCode", "SMS_197385044");
        Map<String,String> map=new HashMap<>();
        map.put("code",code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));//注意code必须是json参数
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
