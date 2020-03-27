package com.github.tanxinzheng.framework.poi;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.WordUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class FreemarkerUtils {

    /**
     * 处理模板
     * @param templateContent
     * @param data
     * @return
     */
    private String processTemplate(String templateContent, Map data){
        Configuration cfg = new Configuration();
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("myTemplate", templateContent);
        cfg.setTemplateLoader(stringLoader);
        cfg.setClassForTemplateLoading(WordUtils.class, "/");
        cfg.setEncoding(Locale.getDefault(), "UTF-8");
        cfg.setClassicCompatible(true);
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setNumberFormat("#");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        try {
            Template template = cfg.getTemplate("myTemplate","utf-8");
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            return writer.toString();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (TemplateException e) {
            log.error(e.getMessage(), e);
        }
        return templateContent;
    }
}
