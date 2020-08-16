package com.CarLineShop.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({ SwaggerConfig.class})
@ComponentScan(basePackages = {"com.CarLineShop.dao","com.CarLineShop.biz","com.CarLineShop.controllers" })
@MapperScan("com.CarLineShop.dao.mapper")
@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);

        System.out.println("service run ");

    }
}
