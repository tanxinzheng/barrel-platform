package com.github.tanxinzheng.test;

import com.github.tanxinzheng.framework.adapter.WebAppConfigurerAdapter;
import com.github.tanxinzheng.framework.web.authentication.CurrentAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * Created by tanxinzheng on 2018/11/18.
 */
@Configuration
public class TestAppConfig extends WebAppConfigurerAdapter {

    @Bean
    @Override
    public CurrentAccountService getCurrentAccountService() {
        return new CurrentAccountService() {
            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public String getAccountId() {
                return "TEST_USER";
            }

            @Override
            public String getAccountName() {
                return "测试人";
            }

            @Override
            public Object getAccountDetail() {
                return null;
            }

            @Override
            public Set<String> getRoles() {
                return null;
            }

            @Override
            public Set<String> getPermissions() {
                return null;
            }
        };
    }
}
