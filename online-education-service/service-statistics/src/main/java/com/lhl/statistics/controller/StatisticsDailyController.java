package com.lhl.statistics.controller;

import com.lhl.commonUtils.CommonResult;
import com.lhl.statistics.domain.StatisticsDaily;
import com.lhl.statistics.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author lhl
 * @since 2020-07-31
 */
@RestController
@RequestMapping("/statistics")
//@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    //根据日期创建统计数据,日期是yyyy-MM-dd格式的
    @GetMapping("/create_statistics__by_date/{date}")
    public CommonResult create_statistics__by_date(@PathVariable("date")String date){
        StatisticsDaily statisticsDaily= statisticsDailyService.create_statistics__by_date(date);
        return CommonResult.ok().data("data",statisticsDaily);
    }

    @GetMapping("/show_chart/{type}/{begin}/{end}")
    public CommonResult show_chart(@PathVariable String type, @PathVariable String begin, @PathVariable String end){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date l_begin = sdf.parse(begin);
            Date l_end = sdf.parse(end);
            if (l_end.getTime()-l_begin.getTime()<=0){
                return CommonResult.error().message("日期输入错误");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return CommonResult.error().message(e.getMessage());
        }
        Map<String, Integer> map = statisticsDailyService.getChartData(type,begin, end );
        ArrayList<String> keys=new ArrayList<>();
        ArrayList<Integer> values=new ArrayList<>();
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            keys.add(entry.getKey());
            values.add(entry.getValue());
        }
        return CommonResult.ok().data("keys",keys).data("values",values);
    }
}

