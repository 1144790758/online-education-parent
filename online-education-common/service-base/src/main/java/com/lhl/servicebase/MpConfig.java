package com.lhl.servicebase;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @athor:lhl
 */
@Configuration
@MapperScan("com.lhl.EduService.mapper") //可以被@Mapper注解代替,咋们统一用@MapperScan
public class MpConfig {
    //逻辑删除3.3后不用插件
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 自动注入的bean
     */
    @Bean
    public MetaObjectHandler MyMetaObjectHandler(){
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.setFieldValByName("gmtCreate", new Date(),metaObject);
                this.setFieldValByName("gmtModified", new Date(),metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("gmtModified", new Date(), metaObject);
            }
        };

    }
}
