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
public class NotificationReceive extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 关联通知主键 */
    private String notificationId;
    /** 接收时间 */
    private Date receiveTime;
    /** 接收人 */
    private String receiver;
    /** 状态 */
    private String dataState;


}
