package com.github.tanxinzheng.module.scheduler.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * Created by tanxinzheng on 17/8/23.
 */
@Configuration
@Profile(value = "quartz")
@ImportResource(locations = "config/spring-quartz.xml")
public class QuartzConfig {

    /**
     * 解决时区问题
     */
    @PostConstruct
    void setDefaultTimezone(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
