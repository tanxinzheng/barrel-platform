package com.github.tanxinzheng.module.fss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by tanxinzheng on 2018/9/25.
 */
@EnableFSSAliyun
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class FSSStartApp {

    public static void main(String[] args) {
        SpringApplication.run(FSSStartApp.class, args);
    }
}
