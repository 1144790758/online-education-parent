package com.lhl.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @athor:lhl
 * @create:2020-07-31 20:51
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.lhl"}) //这可以使其它模块其他依赖的我写的包被扫描到
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling //开启定时任务
public class StatisticsApp {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApp.class,args);
    }
}
