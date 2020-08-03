package com.lhl.eduService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @athor:lhl
 * @create:2020-06-24 19:04
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.lhl"}) //这可以使其它模块其他依赖的我写的包被扫描到
@EnableDiscoveryClient
@EnableFeignClients
public class EduApp {
    public static void main(String[] args) {
        SpringApplication.run(EduApp.class,args);
    }
}
