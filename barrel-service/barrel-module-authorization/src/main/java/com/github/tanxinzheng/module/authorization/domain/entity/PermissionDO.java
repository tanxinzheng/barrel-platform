package com.github.tanxinzheng.module.authorization.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Data
@TableName(value = "xmo_permission")
public class PermissionDO extends BaseEntity implements Serializable {

    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /** 权限KEY */
    @TableField(value = "PERMISSION_KEY")
    private String permissionKey;
    /** 权限组 */
    @TableField(value = "PERMISSION_GROUP")
    private String permissionGroup;
    /** 权限URL */
    @TableField(value = "PERMISSION_URL")
    private String permissionUrl;
    /** 权限ACTION */
    @TableField(value = "PERMISSION_ACTION")
    private String permissionAction;
    /** 权限描述 */
    @TableField(value = "DESCRIPTION")
    private String description;
    /** 激活 */
    @TableField(value = "ACTIVE")
    private Boolean active;
    /** 父节点 */
    @TableField(value = "PARENT_ID")
    private String parentId;
    /** 创建人 */
    @TableField(value = "CREATED_USER_ID")
    private String createdUserId;
    /** 创建时间 */
    @TableField(value = "CREATED_TIME")
    private LocalDateTime createdTime;
    /** 更新人 */
    @TableField(value = "UPDATED_USER_ID")
    private String updatedUserId;
    /** 更新时间 */
    @TableField(value = "UPDATED_TIME")
    private LocalDateTime updatedTime;


}
