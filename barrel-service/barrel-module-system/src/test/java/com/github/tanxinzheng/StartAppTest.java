package com.github.tanxinzheng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
        "com.github.tanxinzheng.**",
        "springfox.documentation.swagger2.**"
})
@MapperScan(basePackages = {
        "com.github.tanxinzheng.module.**.mapper.**"
})
@SpringBootApplication(scanBasePackages = "com.github.tanxinzheng.**")
public class StartAppTest {

    public static void main(String[] args) {
        SpringApplication.run(StartAppTest.class, args);
    }
}


