package com.github.tanxinzheng.fss;

import com.github.tanxinzheng.fss.service.FileStoreService;
import com.github.tanxinzheng.fss.service.FileStoreServiceByOss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tanxinzheng on 2018/9/20.
 */
@Configuration
@EnableConfigurationProperties(value = FssConfigProperties.class)
@ConditionalOnProperty(prefix = "fss", value = "enable", matchIfMissing = true)
public class FssAutoConfiguration {

    @Autowired
    FssConfigProperties fssConfigProperties;

    @Bean
    public FileStoreService getFss(){
        return new FileStoreServiceByOss(fssConfigProperties);
    }
}
