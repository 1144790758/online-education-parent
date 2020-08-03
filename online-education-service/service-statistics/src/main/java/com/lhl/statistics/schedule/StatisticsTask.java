package com.lhl.statistics.schedule;

import com.lhl.statistics.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @athor:lhl
 * @create:2020-07-31 23:43
 */
@Component
public class StatisticsTask {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    //springboot支持6位的七子表达式,最后一位删除默认为今年
    //每天0时统计昨天的数据
    @Scheduled(cron = "* * 0 * * ? ")
    public void StatisticsEveryday(){
        //获取昨天的日期
        Date date = new Date(new Date().getTime() - 500);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = format.format(date);
        statisticsDailyService.create_statistics__by_date(yesterday);
    }

}
