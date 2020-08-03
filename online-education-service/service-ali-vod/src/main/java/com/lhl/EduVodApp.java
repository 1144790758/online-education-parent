package com.lhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @athor:lhl
 * @create:2020-07-17 12:12
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.lhl"})
@EnableFeignClients
public class    EduVodApp {
    public static void main(String[] args) {
        SpringApplication.run(EduVodApp.class,args);
    }
}
