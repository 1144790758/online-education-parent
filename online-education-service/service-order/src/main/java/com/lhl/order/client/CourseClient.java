package com.lhl.order.client;

import com.lhl.order.domain.EduCourse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @athor:lhl
 * @create:2020-07-31 0:30
 */
@FeignClient("service-edu")
public interface CourseClient {
    //用于支付模块的远程调用,需要根据id返回课程信息
    @GetMapping("/eduservice/edu-course/get_course/{id}")
    public EduCourse get_course(@PathVariable String id);
}
