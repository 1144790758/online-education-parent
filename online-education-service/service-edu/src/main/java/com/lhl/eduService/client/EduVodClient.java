package com.lhl.eduService.client;

import com.lhl.commonUtils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @athor:lhl
 */
@FeignClient("service-vod")
public interface EduVodClient {
    @DeleteMapping("/eduvod/deleteById/{id}")
    public CommonResult deleteById(@PathVariable("id") String id);
}
