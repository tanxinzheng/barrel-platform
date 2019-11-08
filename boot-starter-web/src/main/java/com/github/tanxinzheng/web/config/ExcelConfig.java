package com.github.tanxinzheng.web.config;

import org.jeecgframework.poi.excel.view.JeecgMapExcelView;
import org.jeecgframework.poi.excel.view.JeecgSingleExcelView;
import org.jeecgframework.poi.excel.view.JeecgTemplateExcelView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelConfig {

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
}
