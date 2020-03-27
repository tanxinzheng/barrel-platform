package com.github.tanxinzheng.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableCasClient
@EnableSwagger2
@SpringBootApplication
public class AmsApp  {

    public static void main(String[] args) {
        SpringApplication.run(AmsApp.class, args);
    }


}
