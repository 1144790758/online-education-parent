package com.lhl.eduService.controller;

import com.lhl.commonUtils.CommonResult;
import org.springframework.web.bind.annotation.*;

/**
 * @athor:lhl
 * 模拟登陆的controller
 */
@RestController
@RequestMapping("/eduservice/user")
//@CrossOrigin //跨域问题:只要端口协议ip中有一个不同就会有跨域问题,请求将不会被正常执行
//两种解决方法:1 将@CrossOrigin注解加载controller上 2 使用网关解决
public class EduLoginController {

    @PostMapping("login")
    public CommonResult login(){
        //不连接数据库模拟登陆
        return CommonResult.ok().data("token","admin");
    }

    @GetMapping("info")
    public CommonResult info(){
        //模拟登陆
//        return CommonResult.ok().data("roles","user").data("name","lhl").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return CommonResult.ok().data("roles","user").data("name","lhl").data("avatar","https://c-ssl.duitang.com/uploads/item/201808/15/20180815212850_qskye.jpeg");

    }
}
