package com.github.tanxinzheng.jwt.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_user")
public class UserDO {

    @TableField(value = "id")
    private String id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "slat")
    private String slat;
    @TableField(value = "age")
    private Integer age;
    @TableField(value = "email")
    private String email;

}
