package com.github.tanxinzheng.module.authorization.model;

import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
public @Data class Permission extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 权限类型：菜单，请求 */
    private String permissionType;

    private String permissionKey;
    /** 权限组 */
    private String permissionGroup;
    /** 权限URL */
    private String permissionUrl;
    /** 权限ACTION */
    private String permissionAction;
    /** 权限描述 */
    private String description;
    /** 激活 */
    private String active;
    /** 父节点 */
    private String parentId;
    /** 创建人 */
    private String createdUserId;
    /** 创建时间 */
    private Date createdTime;
    /** 更新人 */
    private String updatedUserId;
    /** 更新时间 */
    private Date updatedTime;
    /** 数据版本号 */
    private Integer dataVersion;

    public void setPermissionAction(String permissionAction) {
        this.permissionAction = permissionAction;
        if(permissionAction != null){
            this.permissionAction = permissionAction.toUpperCase();
        }
    }
}
