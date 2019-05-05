package com.graduation.sportsvenue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.multipart.MultipartResolver;


@SpringBootApplication
@MapperScan("com.graduation.sportsvenue.dao")
public class SportsvenueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsvenueApplication.class, args);
    }

}
