package com.github.tanxinzheng.config;

import com.github.tanxinzheng.framework.adapter.WebAppConfigurerAdapter;
import com.github.tanxinzheng.framework.core.service.impl.CurrentAccountServiceImpl;
import com.github.tanxinzheng.framework.web.authentication.CurrentAccountService;
import com.github.tanxinzheng.jwt.support.JwtUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tanxinzheng on 2018/9/20.
 */
@Configuration
public class WebAppConfig extends WebAppConfigurerAdapter {

    @Bean
    @Override
    public CurrentAccountService<JwtUser> getCurrentAccountService() {
        return new CurrentAccountServiceImpl();
    }
}
