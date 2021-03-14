package com.javatrainingcamp.week08;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class Week08Application {

    public static void main(String[] args) {
        SpringApplication.run(Week08Application.class, args);
    }

}
