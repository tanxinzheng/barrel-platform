package com.github.tanxinzheng.module.authorization.model;

import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@Data
public class UserGroup extends BaseEntity implements Serializable {

    /**  */
    private String id;
    /** 用户表ID */
    private String userId;
    /** 组表ID */
    private String groupId;


}
