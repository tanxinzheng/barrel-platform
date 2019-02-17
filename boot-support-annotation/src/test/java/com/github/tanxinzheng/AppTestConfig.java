package com.github.tanxinzheng;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.tanxinzheng.module.dictionary.web.AccountInterpreterService;
import com.github.tanxinzheng.module.dictionary.web.DictionaryAnnotationIntrospector;
import com.github.tanxinzheng.module.dictionary.web.DictionaryInterpreterService;
import com.github.tanxinzheng.module.dictionary.web.DictionaryTransferService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Map;

/**
 * Created by tanxinzheng on 2018/11/15.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class AppTestConfig extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(AppTestConfig.class, args);
    }

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public DictionaryTransferService getUserDictionaryInterpreterService(){
        return new DictionaryTransferService() {
            @Override
            public Map<String, Object> translate(String dictionaryType, String dictionaryCode) {
                Map<String, Object> data = Maps.newHashMap();
                Map<String, String> user = Maps.newHashMap();
                user.put("userId", "1");
                user.put("userName", "管理员");
                user.put("link", "http://avatar.xxx.com/avatar/1.jpg");
                data.put("1", user);
                Map<String, String> user2 = Maps.newHashMap();
                user2.put("userId", "2");
                user2.put("userName", "用户");
                user2.put("link", "http://avatar.xxx.com/avatar/2.jpg");
                data.put("2", user2);
                return data;
            }

            /**
             * 字典索引
             *
             * @return
             */
            @Override
            public String getDictionaryIndex() {
                return "USER";
            }
        };
    }

    @Bean
    public DictionaryInterpreterService getDictionaryInterpreterService(){
        return new DictionaryInterpreterService() {

            @Cacheable(cacheNames = "dictionariesCache", key = "#dictionaryType||dictionaryCode")
            @Override
            public Map<String, Object> translateDictionary(String dictionaryType, String dictionaryCode) {
                Map<String, Object> data = Maps.newHashMap();
                if(dictionaryType.equalsIgnoreCase("SEX")){
                    data.put("W", "女");
                    data.put("M", "男");
                }else if(dictionaryType.equalsIgnoreCase("DISABLE")){
                    data.put("true", "禁用");
                    data.put("false", "启用");
                }
                return data;
            }
        };
    }

    @Bean
    public AccountInterpreterService getAccountInterpreterService(){
        return new AccountInterpreterService() {

            /**
             * 翻译
             *
             * @param userId
             * @return
             */
            @Override
            public Object translateAccount(String userId) {
                Map<String, Object> data = Maps.newHashMap();
                Map<String, String> user = Maps.newHashMap();
                user.put("userId", "1");
                user.put("userName", "管理员");
                user.put("link", "http://avatar.xxx.com/avatar/1.jpg");
                data.put("1", user);
                Map<String, String> user2 = Maps.newHashMap();
                user2.put("userId", "2");
                user2.put("userName", "用户");
                user2.put("link", "http://avatar.xxx.com/avatar/2.jpg");
                data.put("2", user2);
                return data;
            }
        };
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
        builder.featuresToDisable(
                SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,
                DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
        );
        builder.annotationIntrospector(getDictionaryIntrospector());
        return builder.build();
    }

}
