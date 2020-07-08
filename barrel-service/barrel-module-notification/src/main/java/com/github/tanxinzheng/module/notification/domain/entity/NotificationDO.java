package com.github.tanxinzheng.module.notification.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 23:27:09
 */
@Data
@TableName(value = "xmo_notification")
public class NotificationDO extends BaseEntity implements Serializable {

    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /** 通知类型 */
    @TableField(value = "NOTIFICATION_TYPE")
    private String notificationType;
    /** 模板ID */
    @TableField(value = "TEMPLATE_ID")
    private String templateId;
    /** 标题 */
    @TableField(value = "TITLE")
    private String title;
    /** 内容 */
    @TableField(value = "BODY")
    private String body;


}
