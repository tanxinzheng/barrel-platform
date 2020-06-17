package com.github.tanxinzheng.module.authorization.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@Data
@TableName(value = "xmo_user_group")
public class UserGroup extends BaseEntity implements Serializable {

    /**  */
    @TableId
    private String id;
    /** 用户表ID */
    @TableField
    private String userId;
    /** 组表ID */
    @TableField
    private String groupId;


}
