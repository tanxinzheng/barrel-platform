package com.xmomen.config;

import com.xmomen.framework.adapter.WebAppConfigurerAdapter;
import com.xmomen.framework.web.authentication.CurrentAccountService;
import com.xmomen.framework.web.authentication.CurrentAccountServiceBySSS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tanxinzheng on 2018/9/20.
 */
@Configuration
public class WebAppConfig extends WebAppConfigurerAdapter {

    @Bean
    @Override
    public CurrentAccountService getCurrentAccountService() {
        return new CurrentAccountServiceBySSS();
    }
}
