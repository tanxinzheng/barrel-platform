package com.github.tanxinzheng.module.system.authorization.domain.entity;

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
@TableName(value = "xmo_user")
public class UserDO extends BaseEntity implements Serializable {

    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /** 用户名 */
    @TableField(value = "USERNAME")
    private String username;
    /** 真实姓名 */
    @TableField(value = "NICKNAME")
    private String nickname;
    /** 密码盐值 */
    @TableField(value = "SALT")
    private String salt;
    /** 密码 */
    @TableField(value = "PASSWORD")
    private String password;
    /** 邮箱 */
    @TableField(value = "EMAIL")
    private String email;
    /** 手机号码 */
    @TableField(value = "PHONE_NUMBER")
    private String phoneNumber;
    /** 头像 */
    @TableField(value = "AVATAR")
    private String avatar;
    /** 锁定 */
    @TableField(value = "DISABLE")
    private Boolean disable;
    /** 更新人 */
    @TableField(value = "UPDATED_BY")
    private String updatedBy;
    /** 更新时间 */
    @TableField(value = "UPDATED_TIME")
    private LocalDateTime updatedTime;
    /** 创建人 */
    @TableField(value = "CREATED_BY")
    private String createdBy;
    /** 注册时间 */
    @TableField(value = "CREATED_TIME")
    private LocalDateTime createdTime;


}
