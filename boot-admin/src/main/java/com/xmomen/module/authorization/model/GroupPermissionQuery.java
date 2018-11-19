package com.xmomen.module.authorization.model;

import lombok.Data;
import com.github.tanxinzheng.framework.model.BaseQuery;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
public @Data class GroupPermissionQuery extends BaseQuery implements Serializable {

    private String keyword;
    private String id;
    private String[] ids;
    private String[] excludeIds;
    private String groupId;
    private String[] groupCodes;
    private String[] permissionIds;
    // true：已绑定的用户组，false：未绑定的用户组
    private Boolean hasBindPermission;

}
