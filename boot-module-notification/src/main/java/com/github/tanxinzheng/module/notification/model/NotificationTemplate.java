package com.github.tanxinzheng.module.notification.model;

import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:47
 * @version 1.0.0
 */
@Data
public class NotificationTemplate extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 模板名称 */
    private String templateName;
    /** 模板标题 */
    private String templateTitle;
    /** 模板内容 */
    private String templateBody;
    /** 模板代码 */
    private String templateCode;
    /** 是否启用 */
    private Boolean active;


}
