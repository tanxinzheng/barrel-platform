package com.github.tanxinzheng.module.notification.model;

import com.github.tanxinzheng.framework.model.BaseQuery;
import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
@Data
public class NotificationQuery extends BaseQuery implements Serializable {

    private String keyword;
    private String id;
    private String[] ids;
    private String[] excludeIds;
    private String userId;
    private String dataState;


}
