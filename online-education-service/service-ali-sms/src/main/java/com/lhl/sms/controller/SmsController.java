package com.lhl.sms.controller;

import com.lhl.commonUtils.CommonResult;
import com.lhl.servicebase.RedisUtil;
import com.lhl.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @athor:lhl
 * @create:2020-07-21 16:18
 */
@RestController
//@CrossOrigin
@RequestMapping("/servicesms")
public class SmsController {

    @Autowired
    SmsService smsService;

    //接受发送短信请求
    @GetMapping("/sendSms/{phone}")
    public CommonResult sendSms(@PathVariable String phone){
        String result = smsService.sendSms(phone);
        return CommonResult.ok().data("result",result);
    }

}
