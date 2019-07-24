package com.github.tanxinzheng.jwt;

import com.github.tanxinzheng.framework.utils.PasswordHelper;
import com.github.tanxinzheng.jwt.support.JwtUser;
import com.github.tanxinzheng.jwt.support.RestAuthenticationEntryPoint;
import com.github.tanxinzheng.jwt.support.filter.JwtAuthorizationFilter;
import com.github.tanxinzheng.module.authorization.model.UserModel;
import com.github.tanxinzheng.module.authorization.service.UserService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
//@ConditionalOnProperty(prefix = "jwt", value = "enable", matchIfMissing = true)
//@ConditionalOnBean({RedisTemplate.class, JwtLoadService.class})
public class JwtAutoConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtConfigProperties jwtConfigProperties;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserService userService;

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
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
        list.add("/**.css");
        list.add("/**.js");
        list.add("/**/*.css");
        list.add("/**/*.js");
        list.add("/favicon.ico");
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
                .addFilterBefore(getJwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .and()
                .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                // 禁用缓存
                .logout()
                .and()
                .headers().cacheControl();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            UserModel userModel = userService.getOneUserModelByUsername(username);
            if (userModel != null) {
                // 缺少权限验证
                JwtUser jwtUser = new JwtUser();
                jwtUser.setId(userModel.getId());
                jwtUser.setUsername(userModel.getUsername());
                jwtUser.setPassword(userModel.getPassword());
                jwtUser.setName(userModel.getNickname());
                jwtUser.setEmail(userModel.getEmail());
                return jwtUser;
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setSaltSource(saltSource());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
       return new PasswordEncoder() {
           @Override
           public String encodePassword(String rawPass, Object salt) {
               return PasswordHelper.encryptPassword(rawPass, (String) salt);
           }

           @Override
           public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
               if(StringUtils.isBlank(encPass) || StringUtils.isBlank(rawPass)){
                   return false;
               }
               String realPassword = PasswordHelper.encryptPassword(rawPass, (String) salt);
               if(encPass.equals(realPassword)){
                   return true;
               }
               return false;
           }
       };
    }

    @Bean
    public SaltSource saltSource(){
        return new SaltSource() {
            @Override
            public String getSalt(UserDetails user) {
                UserModel userModel = userService.getOneUserModelByUsername(user.getUsername());
                return userModel.getSalt();
            }
        };
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
