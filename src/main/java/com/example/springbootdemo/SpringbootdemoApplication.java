package com.example.springbootdemo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@Slf4j
@MapperScan("com.example.springbootdemo.mapper")
public class SpringbootdemoApplication{

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
        log.info("测试系统已成功启动！！！！！");
    }


}
