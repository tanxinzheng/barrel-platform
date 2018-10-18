package com.xmomen.config;

import com.xmomen.framework.adapter.WebAppConfigurerAdapter;
import com.xmomen.framework.web.authentication.CurrentAccountService;
import com.xmomen.module.core.service.impl.CurrentAccountServiceImpl;
import com.xmomen.module.jwt.support.JwtUser;
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
