package com.github.tanxinzheng.module.system.authorization.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Data
@ApiModel(value = "用户角色关系")
public class UserRoleRelationVO implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "用户表ID")
    private String userId;
    @ApiModelProperty(value = "组表ID")
    private String roleId;


}
