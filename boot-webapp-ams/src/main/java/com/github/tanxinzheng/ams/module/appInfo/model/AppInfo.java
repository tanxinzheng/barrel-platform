package com.github.tanxinzheng.ams.module.appInfo.model;

import lombok.Data;
import com.xmomen.framework.model.BaseEntity;

import java.lang.Integer;
import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2018-9-11 14:54:49
 * @version 1.0.0
 */
public @Data class AppInfo extends BaseEntity implements Serializable {

    /**  */
    private String id;
    /** 应用名称 */
    private String appName;
    /** 应用代码 */
    private String appCode;
    /** 描述 */
    private String description;
    /** URL地址 */
    private String url;
    /** 状态：0-准备中，1-已上线，9-已废弃 */
    private Integer state;
    /** 备注 */
    private String remark;
    /** 开发分组 */
    private String devGroup;
    /** 测试分组 */
    private String testGroup;


}
