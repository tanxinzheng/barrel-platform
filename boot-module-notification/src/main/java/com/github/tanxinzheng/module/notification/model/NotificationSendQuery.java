package com.github.tanxinzheng.module.notification.model;

import lombok.Data;
import com.github.tanxinzheng.framework.model.BaseQuery;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
public @Data class NotificationSendQuery extends BaseQuery implements Serializable {

    private String keyword;
    private String id;
    private String[] ids;
    private String[] excludeIds;

}