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
@Data
public class NotificationSend extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 通知内容表主键 */
    private String notificationId;
    /** 发送人 */
    private String sender;
    /** 发送时间 */
    private Date sendTime;
    /** 状态 */
    private String dataState;


}
