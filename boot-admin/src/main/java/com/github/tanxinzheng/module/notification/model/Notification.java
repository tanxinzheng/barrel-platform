package com.github.tanxinzheng.module.notification.model;

import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
public @Data class Notification extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 模板主键 */
    private String templateId;
    /** 标题 */
    private String title;
    /** 内容 */
    private String body;
    /** 失效时间 */
    private Date expireTime;
    /** 通知类型 */
    private String notificationType;


}
