package com.github.tanxinzheng.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.tanxinzheng.framework.web.handler.LogbackMDCInterceptor;
import com.github.tanxinzheng.framework.web.json.CustomDateDeserialize;
import com.github.tanxinzheng.framework.web.support.DateConverter;
import com.github.tanxinzheng.framework.web.support.LoginUserResolver;
import com.github.tanxinzheng.module.dictionary.web.DictionaryAnnotationIntrospector;
import com.github.tanxinzheng.module.fss.EnableFSSAliyun;
import org.jeecgframework.poi.excel.view.JeecgMapExcelView;
import org.jeecgframework.poi.excel.view.JeecgSingleExcelView;
import org.jeecgframework.poi.excel.view.JeecgTemplateExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.MultipartConfigElement;
import java.util.Date;
import java.util.List;

/**
 * Created by tanxinzheng on 17/8/23.
 */
@Configuration
@ComponentScan(value = {"com.github.tanxinzheng.**"})
@EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class})
@EnableFSSAliyun
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginUserResolver());
    }

    @Bean
    public DictionaryAnnotationIntrospector getDictionaryIntrospector(){
        DictionaryAnnotationIntrospector dictionaryAnnotationIntrospector = new DictionaryAnnotationIntrospector();
        dictionaryAnnotationIntrospector.setApplicationContext(applicationContext);
        return dictionaryAnnotationIntrospector;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        builder.timeZone("GMT+8");
        builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
        builder.deserializerByType(Date.class, new CustomDateDeserialize());
        builder.featuresToDisable(
                SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,
                DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
        );
        builder.annotationIntrospector(getDictionaryIntrospector());
        builder.featuresToEnable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return builder.build();
    }

    @Bean
    public JeecgTemplateExcelView getJeecgTemplateExcelView(){
        return new JeecgTemplateExcelView();
    }
    @Bean
    public JeecgSingleExcelView getJeecgSingleExcelView(){
        return new JeecgSingleExcelView();
    }
    @Bean
    public JeecgMapExcelView getJeecgMapExcelView(){
        return new JeecgMapExcelView();
    }
    @Bean
    public BeanNameViewResolver getBeanNameViewResolver(){
        BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
        beanNameViewResolver.setOrder(0);
        return beanNameViewResolver;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.APPLICATION_JSON_UTF8)
                .parameterName("format")
                .favorParameter(true)
                .ignoreUnknownPathExtensions(false)
                .ignoreAcceptHeader(false)
                .useJaf(true);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(false, new MappingJackson2JsonView());
        registry.enableContentNegotiation(
                new MappingJackson2JsonView()
        );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogbackMDCInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("128KB");
        factory.setMaxRequestSize("128KB");
        return factory.createMultipartConfig();
    }

}
