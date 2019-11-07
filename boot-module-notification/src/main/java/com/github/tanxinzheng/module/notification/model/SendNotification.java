package com.github.tanxinzheng.module.notification.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by tanxinzheng on 17/8/25.
 */
@Data
public class SendNotification implements Serializable {

    /**
     * 模板代码
     */
    private String templateCode;
    /**
     *  通知类型（邮件，短信，站内信，微信）
     */
    private String notificationType;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 接收人
     */
    private String[] receiver;
    /**
     * 接收用户组
     */
    private String[] receiveGroup;
    /**
     * 失效时间
     */
    private Date expireTime;
    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String body;

    /**
     * 发送人
     */
    private String sender;

    /**
     * 模板数据
     */
    private Map<String, Object> templateData;
}
