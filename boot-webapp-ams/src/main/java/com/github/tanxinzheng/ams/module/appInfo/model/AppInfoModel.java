package com.github.tanxinzheng.ams.module.appInfo.model;

import com.github.tanxinzheng.framework.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import org.springframework.beans.BeanUtils;

import java.lang.Integer;
import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2018-9-11 14:54:49
 * @version 1.0.0
 */
public @Data class AppInfoModel extends BaseModel implements Serializable {

    /**  */
    @Length(max = 50, message = "字符长度限制[0,50]")
    private String id;
    /** 应用名称 */
    @NotBlank(message = "应用名称为必填项")
    @Length(max = 200, message = "应用名称字符长度限制[0,200]")
    private String appName;
    /** 应用代码 */
    @NotBlank(message = "应用代码为必填项")
    @Length(max = 100, message = "应用代码字符长度限制[0,100]")
    private String appCode;
    /** 描述 */
    @NotBlank(message = "描述为必填项")
    @Length(max = 500, message = "描述字符长度限制[0,500]")
    private String description;
    /** URL地址 */
    @NotBlank(message = "URL地址为必填项")
    @Length(max = 100, message = "URL地址字符长度限制[0,100]")
    private String url;
    /** 状态：0-准备中，1-已上线，9-已废弃 */
    @NotNull(message = "状态：0-准备中，1-已上线，9-已废弃为必填项")
    @Range(max = 999999999, min = -999999999, message = "状态：0-准备中，1-已上线，9-已废弃数值范围[999999999,-999999999]")
    private Integer state;
    /** 备注 */
    @Length(max = 1000, message = "备注字符长度限制[0,1,000]")
    private String remark;
    /** 开发分组 */
    @Length(max = 50, message = "开发分组字符长度限制[0,50]")
    private String devGroup;
    /** 测试分组 */
    @Length(max = 50, message = "测试分组字符长度限制[0,50]")
    private String testGroup;

    /**
    * Get AppInfo Entity Object
    * @return
    */
    @JsonIgnore
    public AppInfo getEntity(){
        AppInfo appInfo = new AppInfo();
        BeanUtils.copyProperties(this, appInfo);
        return appInfo;
    }


}
