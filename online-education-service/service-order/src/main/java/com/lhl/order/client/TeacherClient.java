package com.lhl.order.client;

import com.lhl.commonUtils.CommonResult;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @athor:lhl
 * @create:2020-07-31 0:41
 */
@FeignClient("service-edu")
public interface TeacherClient {
    //CommonResult.ok().data("item", teacher),需要根据讲师id返回讲师信息
    @GetMapping("/eduservice/edu-teacher/{id}")
    public CommonResult getById(@PathVariable String id);

}
