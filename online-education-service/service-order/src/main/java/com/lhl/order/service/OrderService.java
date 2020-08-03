package com.lhl.order.service;

import com.lhl.order.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author lhl
 * @since 2020-07-30
 */
public interface OrderService extends IService<Order> {

    String createOrder(String userId, String courseId);
}
