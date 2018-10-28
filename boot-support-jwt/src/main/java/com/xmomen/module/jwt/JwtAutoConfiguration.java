package com.xmomen.module.jwt;

import com.google.common.collect.Lists;
import com.xmomen.module.jwt.support.*;
import com.xmomen.module.jwt.support.access.MyAccessDecisionManager;
import com.xmomen.module.jwt.support.access.MyFilterInvocationSecurityMetadataSource;
import com.xmomen.module.jwt.support.access.SecurityMetadataHandler;
import com.xmomen.module.jwt.support.filter.JwtAuthenticationFilter;
import com.xmomen.module.jwt.support.filter.JwtAuthorizationFilter;
import com.xmomen.module.jwt.support.handler.JwtAuthenticationFailureHandler;
import com.xmomen.module.jwt.support.handler.JwtAuthenticationSuccessHandler;
import com.xmomen.module.jwt.support.handler.JwtLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

/**
 * Created by tanxinzheng on 2018/9/20.
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(value = JwtConfigProperties.class)
@ConditionalOnProperty(prefix = "jwt", value = "enable", matchIfMissing = true)
@ConditionalOnBean({RedisTemplate.class, JwtLoadService.class})
public class JwtAutoConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtLoadService jwtLoadService;

    @Autowired
    JwtConfigProperties jwtConfigProperties;

    @Autowired
    RedisTemplate redisTemplate;

    @Bean
    public JwtAuthenticationProvider getJwtAuthenticationProvider(){
        return new JwtAuthenticationProvider(jwtLoadService, getJwtTokenService(), jwtConfigProperties);
    }


    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers(getStaticResourcesUrl());
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .authenticationProvider(getJwtAuthenticationProvider());
    }

    @Bean
    @Primary
    public JwtTokenService getJwtTokenService(){
        return new DefaultJwtTokenService(redisTemplate, jwtConfigProperties);
    }

    @Bean
    public JwtAuthenticationSuccessHandler getJwtAuthenticationSuccessHandler(){
        return new JwtAuthenticationSuccessHandler();
    }

    @Bean
    public JwtAuthenticationFailureHandler getJwtAuthenticationFailureHandler(){
        return new JwtAuthenticationFailureHandler();
    }

    @Bean
    public JwtAuthenticationFilter getJwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter =
                new JwtAuthenticationFilter(authenticationManagerBean());
        jwtAuthenticationFilter.setPostOnly(false);
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(getJwtAuthenticationSuccessHandler());
        jwtAuthenticationFilter.setAuthenticationFailureHandler(getJwtAuthenticationFailureHandler());
        jwtAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login"));
        return jwtAuthenticationFilter;
    }

    @Bean
    public JwtAuthorizationFilter getJwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManagerBean(), getJwtTokenService());
    }

    private String[] getStaticResourcesUrl(){
        String[] permitUrls = jwtConfigProperties.getPermitUrls();
        List<String> list = Lists.newArrayList(permitUrls);
        list.add("/**.css");
        list.add("/**.js");
        String[] data = list.toArray(new String[list.size()]);
        return data;
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .headers()
                // 允许frame（支持iframe下载功能）
                .frameOptions().sameOrigin()
                .and()
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
//                .anonymous().disable()
                // 基于token，所以不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers(getStaticResourcesUrl())
                .permitAll()
                .anyRequest().authenticated()
                // 用于动态URL权限控制
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    public <O extends FilterSecurityInterceptor> O postProcess(
//                            O fsi) {
//                        fsi.setSecurityMetadataSource(mySecurityMetadataSource());
//                        fsi.setAccessDecisionManager(myAccessDecisionManager());
//                        return fsi;
//                    }
//                })
                // 除上面外的所有请求全部需要鉴权认证
                .and()
//                .addFilterBefore(getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(getJwtAuthorizationFilter(), BasicAuthenticationFilter.class)
                .formLogin()
                .and()
                .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                // 禁用缓存
                .logout()
                .logoutSuccessHandler(new JwtLogoutSuccessHandler(getJwtTokenService()))
                .and()
                .headers().cacheControl();
    }

    @Autowired
    SecurityMetadataHandler securityMetadataHandler;

    @Bean
    public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
        MyFilterInvocationSecurityMetadataSource securityMetadataSource = new MyFilterInvocationSecurityMetadataSource(securityMetadataHandler);
        return securityMetadataSource;
    }

    @Bean
    public AccessDecisionManager myAccessDecisionManager() {
        return new MyAccessDecisionManager();
    }
}
