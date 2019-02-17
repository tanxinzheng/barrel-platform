package com.github.tanxinzheng.module;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

/**
 * Created by tanxinzheng on 17/8/23.
 */
@Configuration
@ImportResource(locations = "spring-quartz.xml")
public class QuartzConfig {

}
