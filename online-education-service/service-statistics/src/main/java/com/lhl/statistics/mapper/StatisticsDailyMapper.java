package com.lhl.statistics.mapper;

import com.lhl.statistics.domain.StatisticsDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author lhl
 * @since 2020-07-31
 */
@Mapper
@Repository
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {

}
