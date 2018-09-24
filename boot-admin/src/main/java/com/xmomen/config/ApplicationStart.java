package com.xmomen.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by tanxinzheng on 17/6/24.
 */
@SpringBootApplication
@MapperScan(value = "com.xmomen.**.mapper")
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

}