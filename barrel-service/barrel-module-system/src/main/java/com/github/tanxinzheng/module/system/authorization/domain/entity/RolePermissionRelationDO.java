package com.github.tanxinzheng.module.system.authorization.domain.entity;

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
 * @Date   2020-6-25 15:43:46
 */
@Data
@TableName(value = "xmo_role_permission_relation")
public class RolePermissionRelationDO extends BaseEntity implements Serializable {

    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /** 组表ID */
    @TableField(value = "ROLE_ID")
    private String roleId;
    /** 权限表ID */
    @TableField(value = "PERMISSION_ID")
    private String permissionId;


}
