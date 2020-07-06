package com.github.tanxinzheng.module.notification.service;

import com.github.tanxinzheng.module.notification.domain.dto.NotificationTemplateDTO;
import freemarker.cache.TemplateLoader;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by tanxinzheng on 2018/6/10.
 */
@Service
public class NotificationTemplateLoader implements TemplateLoader {

    @Resource
    NotificationTemplateService notificationTemplateService;


    @Override
    public NotificationTemplateDTO findTemplateSource(String templateCode) throws IOException {
        NotificationTemplateDTO notificationTemplateModel = notificationTemplateService.findByCode(templateCode);
        if(notificationTemplateModel == null){
            return null;
        }
        return notificationTemplateModel;
    }

    @Override
    public long getLastModified(Object o) {
        return 0;
    }

    @Override
    public Reader getReader(Object o, String s) throws IOException {
        return null;
    }

    @Override
    public void closeTemplateSource(Object o) throws IOException {

    }
}
