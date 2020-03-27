package com.github.tanxinzheng;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

@ExcelTarget(value = "NotificationTemplateModel")
@Data
public class DemoModel {

    @Excel(name = "员工自编码")
    private String code;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "邮箱")
    private String email;
    @Excel(name = "员工核保结果")
    private String result;
    @Excel(name = "子女核保结果")
    private String result2;
    @Excel(name = "配偶核保结果")
    private String result3;
    private String result4;
    private String result5;
}
