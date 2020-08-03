package com.lhl.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhl.commonUtils.CommonResult;
import com.lhl.order.client.TeacherClient;
import com.lhl.order.domain.EduTeacher;
import com.lhl.order.domain.Order;
import com.lhl.order.service.OrderService;
import com.lhl.servicebase.JwtUtils;
import javafx.beans.binding.StringBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author lhl
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/orderservice")
//@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    TeacherClient teacherClient;

    //根据课程id和用户id创建订单，返回订单id
    @PostMapping("/create_order/{courseId}")
    public CommonResult create_order(@PathVariable String courseId, HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        String orderId=orderService.createOrder(userId,courseId);
        return CommonResult.ok().data("orderId",orderId);
    }

    //测试说明远程调用后返回的CommonResult中的对象变为了一个链表不能转换为原来的vo对象了
    //要取其中对象的属性值只能teacher.get("id").toString()
    @GetMapping("/test/{id}")
    public String test(@PathVariable String id){
        CommonResult result = teacherClient.getById(id);
        Map<String, Object> data = result.getData();
        LinkedHashMap teacher = (LinkedHashMap) data.get("item");
        String id1 = teacher.get("id").toString();
        return id1;
    }

    @GetMapping("/get_order/{orderId}")
    public CommonResult get(@PathVariable String orderId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return CommonResult.ok().data("order", order);
    }

}

