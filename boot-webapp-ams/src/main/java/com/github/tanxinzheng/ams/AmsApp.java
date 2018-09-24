package com.github.tanxinzheng.ams;

import com.github.tanxinzheng.cas.client.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCasClient
@SpringBootApplication
public class AmsApp {

    public static void main(String[] args) {
        SpringApplication.run(AmsApp.class, args);
    }
}
