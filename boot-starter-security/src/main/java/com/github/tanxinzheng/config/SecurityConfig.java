package com.github.tanxinzheng.config;

import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.jwt.access.JwtAccessDecisionManager;
import com.github.tanxinzheng.jwt.access.JwtFilterInvocationSecurityMetadataSource;
import com.github.tanxinzheng.jwt.config.JwtConfigProperties;
import com.github.tanxinzheng.jwt.filter.JwtAuthorizationFilter;
import com.github.tanxinzheng.jwt.handler.TokenAccessDeniedHandler;
import com.github.tanxinzheng.jwt.handler.TokenSecurityMetadataHandler;
import com.github.tanxinzheng.jwt.support.RestAuthenticationEntryPoint;
import com.github.tanxinzheng.module.authorization.model.UserGroupModel;
import com.github.tanxinzheng.module.authorization.model.UserGroupQuery;
import com.github.tanxinzheng.module.authorization.model.UserModel;
import com.github.tanxinzheng.module.authorization.service.UserGroupService;
import com.github.tanxinzheng.module.authorization.service.UserService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * Created by tanxinzheng on 2018/9/20.
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(value = JwtConfigProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtConfigProperties jwtConfigProperties;

    @Autowired
    UserService userService;

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(getStaticResourcesUrl());
    }

    /**
     * 允许跨域调用的过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return new CorsFilter(source);
    }

    @Bean
    public JwtAuthorizationFilter getJwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter();
    }

    private String[] getStaticResourcesUrl(){
        String[] permitUrls = jwtConfigProperties.getPermitUrls();
        List<String> list = Lists.newArrayList(permitUrls);
        list.add("/access/**");
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
                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
                .permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers(getStaticResourcesUrl())
                .permitAll()
                .anyRequest().authenticated()
                // 用于动态URL权限控制
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    public <O extends FilterSecurityInterceptor> O postProcess(
                            O fsi) {
                        fsi.setSecurityMetadataSource(getSecurityMetadataSource());
                        fsi.setAccessDecisionManager(getAccessDecisionManager());
                        return fsi;
                    }
                })
                // 除上面外的所有请求全部需要鉴权认证
                .and()
                .addFilterBefore(getJwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                    .accessDeniedHandler(new TokenAccessDeniedHandler()) // 访问未授权资源异常处理
                .and()
                // 禁用缓存
                .logout()
                .and()
                .headers().cacheControl();
    }

    @Bean
    public AccessDecisionManager getAccessDecisionManager() {
        return new JwtAccessDecisionManager();
    }

    @Bean
    public TokenSecurityMetadataHandler getJwtSecurityMetadataHandler(){
        return new TokenSecurityMetadataHandler();
    }

    @Bean
    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        JwtFilterInvocationSecurityMetadataSource securityMetadataSource = new JwtFilterInvocationSecurityMetadataSource(getJwtSecurityMetadataHandler());
        return securityMetadataSource;
    }

    @Autowired
    UserGroupService userGroupService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            UserModel userModel = userService.getOneUserModelByUsername(username);
            if (userModel != null) {
                // 缺少权限验证
                CurrentLoginUser currentLoginUser = new CurrentLoginUser();
                currentLoginUser.setId(userModel.getId());
                currentLoginUser.setUsername(userModel.getUsername());
                currentLoginUser.setPassword(userModel.getPassword());
                currentLoginUser.setName(userModel.getNickname());
                currentLoginUser.setEmail(userModel.getEmail());
                UserGroupQuery userGroupQuery = new UserGroupQuery();
                userGroupQuery.setUserId(userModel.getId());
                List<UserGroupModel> userGroupModelList = userGroupService.getUserGroupModelList(userGroupQuery);
                List<SimpleGrantedAuthority> simpleGrantedAuthorities = Lists.newArrayList();
                if(CollectionUtils.isNotEmpty(userGroupModelList)){
                    userGroupModelList.stream().forEach(userGroupModel -> {
                        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userGroupModel.getGroupName());
                        simpleGrantedAuthorities.add(simpleGrantedAuthority);
                    });
                }
                currentLoginUser.setAuthorities(simpleGrantedAuthorities);
                return currentLoginUser;
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

}
