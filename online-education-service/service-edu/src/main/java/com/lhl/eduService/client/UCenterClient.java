package com.lhl.eduService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @athor:lhl
 */
@FeignClient("service-usercenter")
public interface UCenterClient {
    //从token中获取数据
    @GetMapping("/serviceUC/ucenter-member/getUserDetail/{token}")
    public String getUserDetail(@PathVariable String token);
}
