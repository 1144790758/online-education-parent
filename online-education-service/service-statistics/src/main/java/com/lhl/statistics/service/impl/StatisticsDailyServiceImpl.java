package com.lhl.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhl.statistics.client.UcenterClient;
import com.lhl.statistics.domain.StatisticsDaily;
import com.lhl.statistics.mapper.StatisticsDailyMapper;
import com.lhl.statistics.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author lhl
 * @since 2020-07-31
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public StatisticsDaily create_statistics__by_date(String date) {
        //首先需要判断是否有相同的日期,有就直接查询后返回
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", date);
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setDateCalculated(date);
        statisticsDaily.setRegisterNum(ucenterClient.count_register(date));
        //TODO登录人数,每日播放视频数,每日新增课程数
        int people = (int) Math.random() * 200;
        int video = (int) Math.random() * 200;
        int course = (int) Math.random() * 200;
        statisticsDaily.setLoginNum(people);
        statisticsDaily.setVideoViewNum(video);
        statisticsDaily.setCourseNum(course);
        if (baseMapper.selectCount(wrapper) == 0) {
            //等于0说明需要添加insert
            baseMapper.insert(statisticsDaily);
        } else {
            //不等于0说明需要更新
            baseMapper.update(statisticsDaily, wrapper);
        }

        wrapper.eq("date_calculated", date);
        StatisticsDaily result = baseMapper.selectOne(wrapper);
        return result;

    }

    @Override
    public Map<String, Integer> getChartData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.select(type, "date_calculated");
        dayQueryWrapper.between("date_calculated", begin, end);
        dayQueryWrapper.orderByAsc("gmt_create");

        List<StatisticsDaily> list = baseMapper.selectList(dayQueryWrapper);
        LinkedHashMap<String,Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < list.size(); i++) {
            StatisticsDaily daily = list.get(i);
            switch (type) {
                case "register_num":
                    map.put(daily.getDateCalculated(),daily.getRegisterNum());
                    break;
                case "login_num":
                    map.put(daily.getDateCalculated(),daily.getLoginNum());
                    break;
                case "video_view_num":
                    map.put(daily.getDateCalculated(),daily.getVideoViewNum());
                    break;
                case "course_num":
                    map.put(daily.getDateCalculated(),daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        return map;
    }
}
