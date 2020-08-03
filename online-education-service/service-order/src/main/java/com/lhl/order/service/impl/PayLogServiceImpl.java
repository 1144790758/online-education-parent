package com.lhl.order.service.impl;

import com.lhl.order.domain.PayLog;
import com.lhl.order.mapper.PayLogMapper;
import com.lhl.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author lhl
 * @since 2020-07-30
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
