package com.github.tanxinzheng;

import com.github.tanxinzheng.framework.core.service.AuthManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by tanxinzheng on 2018/11/15.
 */
@SpringBootApplication
@MapperScan(value = "com.github.tanxinzheng.**.mapper")
public class AppTestConfig {

    public static void main(String[] args) {
        SpringApplication.run(AppTestConfig.class, args);
    }

}
