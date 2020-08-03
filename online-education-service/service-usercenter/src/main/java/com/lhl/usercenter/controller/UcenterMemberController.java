package com.lhl.usercenter.controller;


import com.google.gson.Gson;
import com.lhl.commonUtils.CommonResult;
import com.lhl.servicebase.JwtUtils;
import com.lhl.usercenter.domain.UcenterMember;
import com.lhl.usercenter.domain.vo.UcenterLoginInfoVo;
import com.lhl.usercenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author lhl
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/serviceUC/ucenter-member")
//@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody UcenterMember member){
        String token=ucenterMemberService.login(member);
        return CommonResult.ok().data("token",token);
    }

    @PostMapping("/register")
    public CommonResult register(@RequestBody UcenterMember member){
        ucenterMemberService.register(member);
        return CommonResult.ok().data("result","注册成功");
    }

    //从token中获取数据
    @GetMapping("/getUserInfo")
    public CommonResult getUserInfo(HttpServletRequest request){
        String id = JwtUtils.getMemberIdByJwtToken(request);
        UcenterLoginInfoVo loginInfoVo = ucenterMemberService.getLoginInfo(id);
        return CommonResult.ok().data("info",loginInfoVo);
    }

    //从token中解析完整的用户信息
    //远程调用出现了问题,猜测可能是传个request不太行,因为本省就是http远程调用自己也会主动注入request
    //上面注释也采用了一下,发现还是不行,request是不能序列化的!!!
    @GetMapping("/getUserDetail/{token}")
    public String getUserDetail(@PathVariable String token){
        String id = JwtUtils.getMemberIdByJwtToken(token);
        UcenterMember member = ucenterMemberService.getById(id);
        Gson gson=new Gson();
        String json = gson.toJson(member);
        return json;
    }

    @GetMapping("/getUserById/{id}")
    public UcenterMember getUserById(@PathVariable String id){
        UcenterMember member = ucenterMemberService.getById(id);
        return member;
    }

    //TODO 根据日期获取某日的登录人数
    //TODO 根据日期获取某日的每日播放视频数
    //TODO 根据日期获取某日的每日每日新增课程数 (根据课程创建的日期来做)
    //根据日期获取当日的注册人数,传入日期格式为 yyyy-MM-dd
    @GetMapping("/count_register/{date}")
    public Integer count_register(@PathVariable("date") String date){
        Integer count=ucenterMemberService.countRegister(date);
        return count;
    }

}

