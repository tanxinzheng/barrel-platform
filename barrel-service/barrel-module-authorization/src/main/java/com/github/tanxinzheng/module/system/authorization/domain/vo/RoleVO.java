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
@ApiModel(value = "角色")
public class RoleVO implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "角色（用户组）代码")
    private String roleCode;
    @ApiModelProperty(value = "角色（用户组）名称")
    private String roleName;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "激活")
    private Boolean active;
    @ApiModelProperty(value = "角色（用户组）类型")
    private String roleType;


}
