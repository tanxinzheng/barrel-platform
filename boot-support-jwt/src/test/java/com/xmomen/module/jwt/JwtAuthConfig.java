package com.xmomen.module.jwt;

import com.xmomen.module.jwt.support.JwtLoadService;
import com.xmomen.module.jwt.support.TestJwtLoadService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tanxinzheng on 2018/9/24.
 */
@Configuration
public class JwtAuthConfig extends JwtConfigAdapter {

    @Bean
    @Override
    public JwtLoadService getJwtLoadService() {
        return new TestJwtLoadService();
    }
}
