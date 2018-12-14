package com.github.tanxinzheng.module.authorization.model;

import lombok.Data;
import com.github.tanxinzheng.framework.model.BaseQuery;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-6-16 22:59:53
 * @version 1.0.0
 */
public @Data class UserQuery extends BaseQuery implements Serializable {

    private String keyword;
    private String id;
    private String[] ids;
    private String[] excludeIds;
    private String notInGroupId;
    private String email;
    private String phone;

}
