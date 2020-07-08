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
@TableName(value = "xmo_notification_template")
public class NotificationTemplateDO extends BaseEntity implements Serializable {

    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /** 模板代码 */
    @TableField(value = "TEMPLATE_CODE")
    private String templateCode;
    /** 模板标题 */
    @TableField(value = "TEMPLATE_TITLE")
    private String templateTitle;
    /** 模板内容 */
    @TableField(value = "TEMPLATE_BODY")
    private String templateBody;
    /** 模板名称 */
    @TableField(value = "TEMPLATE_NAME")
    private String templateName;


}
