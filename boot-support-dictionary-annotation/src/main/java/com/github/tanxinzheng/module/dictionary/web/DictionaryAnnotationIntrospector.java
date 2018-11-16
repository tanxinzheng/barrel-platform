package com.github.tanxinzheng.module.dictionary.web;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 字典注解
 */
public class DictionaryAnnotationIntrospector extends JacksonAnnotationIntrospector implements ApplicationContextAware {

    private Map<Class, Class> annotationJsonSerializerMap = Maps.newHashMap();

    private ApplicationContext applicationContext;

    public DictionaryAnnotationIntrospector() {
        annotationJsonSerializerMap.put(DictionaryTransfer.class, DictionaryJsonSerializer.class);
        annotationJsonSerializerMap.put(AccountField.class, AccountJsonSerializer.class);
    }

    @Override
    public Object findSerializer(Annotated a) {
        Iterable<Annotation> annotated = a.annotations();
        for (Annotation annotation : annotated) {
            for (Class key : annotationJsonSerializerMap.keySet()) {
                if(annotation.annotationType().getName().equals(key.getName())){
                    Object dictionaryTransfer = a.getAnnotation(key);
                    return applicationContext.getBean(annotationJsonSerializerMap.get(key), dictionaryTransfer);
                }
            }
        }
        return super.findSerializer(a);
    }

    /**
     * 设置注解类及对应的序列化类
     * @param annotated
     * @param jsonSerializer
     */
    public void appendAnnotationJsonSerializer(Class annotated, Class jsonSerializer){
        annotationJsonSerializerMap.put(annotated, jsonSerializer);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
