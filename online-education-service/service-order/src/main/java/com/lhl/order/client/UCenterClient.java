package com.lhl.order.client;

import com.lhl.order.domain.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @athor:lhl
 */
@FeignClient("service-usercenter")
public interface UCenterClient {
    //从token中获取数据
    @GetMapping("/serviceUC/ucenter-member/getUserDetail/{token}")
    public String getUserDetail(@PathVariable String token);

    @GetMapping("/serviceUC/ucenter-member/getUserById/{id}")
    public UcenterMember getUserById(@PathVariable String id);
}
