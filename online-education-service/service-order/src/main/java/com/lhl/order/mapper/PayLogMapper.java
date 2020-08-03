package com.lhl.order.mapper;

import com.lhl.order.domain.PayLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 支付日志表 Mapper 接口
 * </p>
 *
 * @author lhl
 * @since 2020-07-30
 */
@Mapper
public interface PayLogMapper extends BaseMapper<PayLog> {

}
