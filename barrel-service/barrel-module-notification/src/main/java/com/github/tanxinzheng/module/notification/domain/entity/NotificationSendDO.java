package com.github.tanxinzheng.module.notification.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 23:27:09
 */
@Data
@TableName(value = "xmo_notification_send")
public class NotificationSendDO extends BaseEntity implements Serializable {

    /** 物理主键 */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /** 通知主键 */
    @TableField(value = "notification_id")
    private String notificationId;
    /** 发送人主键 */
    @TableField(value = "sender")
    private String sender;
    /** 发送时间 */
    @TableField(value = "send_time")
    private LocalDateTime sendTime;
    /** 状态 */
    @TableField(value = "data_state")
    private String dataState;


}
