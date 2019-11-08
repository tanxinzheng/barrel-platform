package com.github.tanxinzheng.module.dictionary.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tanxinzheng.module.dictionary.web.DictionaryAnnotationIntrospector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class TranslateWebConfig {

    @Resource
    ObjectMapper objectMapper;

    @Bean
    public DictionaryAnnotationIntrospector getDictionaryIntrospector(){
        DictionaryAnnotationIntrospector dictionaryAnnotationIntrospector = new DictionaryAnnotationIntrospector();
        objectMapper.setAnnotationIntrospector(dictionaryAnnotationIntrospector);
        return dictionaryAnnotationIntrospector;
    }
}
