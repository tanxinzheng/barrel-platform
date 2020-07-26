package com.github.tanxinzheng.module.system.feign.fallback;

import com.github.tanxinzheng.module.system.feign.ISystemClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/*
 * @Description 回退接口工厂类，可根据回退异常自定义回退信息
 * @Author tanxinzheng
 * @Date 2020/7/25
 */
@Slf4j
@Component
public class UserClientFallbackFactory implements FallbackFactory<ISystemClient> {

//    @Resource
//    SystemClientFallback fallback;

    @Override
    public ISystemClient create(Throwable throwable) {
        log.error(throwable.getMessage(), throwable);
        log.debug("use default fallback class");
//        return fallback;
        return null;
    }
}
