package com.lhl.statistics.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @athor:lhl
 * @create:2020-07-31 20:59
 */
@FeignClient("service-usercenter")
public interface UcenterClient {

    @GetMapping("/serviceUC/ucenter-member/count_register/{date}")
    public Integer count_register(@PathVariable("date") String date);
}
