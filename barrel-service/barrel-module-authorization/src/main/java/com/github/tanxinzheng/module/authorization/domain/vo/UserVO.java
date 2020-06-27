package com.github.tanxinzheng.module.authorization.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "用户")
public class UserVO implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "真实姓名")
    private String nickname;
    @ApiModelProperty(value = "密码盐值")
    private String salt;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "锁定")
    private Boolean disable;
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime createdTime;


}
