package com.lhl.statistics.service;

import com.lhl.statistics.domain.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author lhl
 * @since 2020-07-31
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    StatisticsDaily create_statistics__by_date(String date);

    Map<String,Integer> getChartData(String type, String begin, String end);
}
