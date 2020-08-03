package com.lhl.order.mapper;

import com.lhl.order.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author lhl
 * @since 2020-07-30
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
