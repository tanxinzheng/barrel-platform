package com.github.tanxinzheng.ams.module.appInfo.model;

import com.github.tanxinzheng.framework.model.BaseQuery;
import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2018-9-11 14:54:49
 * @version 1.0.0
 */
public @Data class AppInfoQuery extends BaseQuery implements Serializable {

    private String keyword;
    private String id;
    private String[] ids;
    private String[] excludeIds;

}
