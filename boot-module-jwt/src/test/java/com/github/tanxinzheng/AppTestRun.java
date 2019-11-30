package com.github.tanxinzheng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.tanxinzheng.**.mapper")
public class AppTestRun {

    public static void main(String[] args) {
        SpringApplication.run(AppTestRun.class, args);
    }
}
