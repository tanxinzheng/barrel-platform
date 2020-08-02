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
@TableName(value = "xmo_role")
public class RoleDO extends BaseEntity implements Serializable {

    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /** 角色（用户组）代码 */
    @TableField(value = "ROLE_CODE")
    private String roleCode;
    /** 角色（用户组）名称 */
    @TableField(value = "ROLE_NAME")
    private String roleName;
    /** 描述 */
    @TableField(value = "DESCRIPTION")
    private String description;
    /** 激活 */
    @TableField(value = "ACTIVE")
    private Boolean active;
    /** 角色（用户组）类型 */
    @TableField(value = "ROLE_TYPE")
    private String roleType;


}
