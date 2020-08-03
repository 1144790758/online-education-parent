package com.lhl.order.service.impl;

import com.google.gson.Gson;
import com.lhl.commonUtils.CommonResult;
import com.lhl.order.client.CourseClient;
import com.lhl.order.client.TeacherClient;
import com.lhl.order.client.UCenterClient;
import com.lhl.order.domain.EduCourse;
import com.lhl.order.domain.EduTeacher;
import com.lhl.order.domain.Order;
import com.lhl.order.domain.UcenterMember;
import com.lhl.order.mapper.OrderMapper;
import com.lhl.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhl.order.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author lhl
 * @since 2020-07-30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private UCenterClient uCenterClient;

    @Autowired
    private CourseClient courseClient ;

    @Autowired
    private TeacherClient teacherClient;


    @Override
    public String createOrder(String userId, String courseId) {
        UcenterMember user = uCenterClient.getUserById(userId);
        EduCourse course = courseClient.get_course(courseId);

        //生成订单号
        String orderNo = OrderNoUtil.getOrderNo();
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setCourseId(course.getId());
        order.setCourseTitle(course.getTitle());
        order.setCourseCover(course.getCover());
        CommonResult result = teacherClient.getById(course.getTeacherId());
        Map<String, Object> data = result.getData();
        LinkedHashMap linkedHashMap =(LinkedHashMap) data.get("item");
        String teacher_name = linkedHashMap.get("name").toString();
        order.setTeacherName(teacher_name);
        order.setMemberId(user.getId());
        order.setNickname(user.getNickname());
        order.setMobile(user.getMobile());
        order.setTotalFee(course.getPrice());

        order.setPayType(1);
        //TODO
        order.setStatus(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
