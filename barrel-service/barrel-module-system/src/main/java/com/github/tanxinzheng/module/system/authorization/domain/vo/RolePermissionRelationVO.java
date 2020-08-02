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
@ApiModel(value = "角色权限关系")
public class RolePermissionRelationVO implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "组表ID")
    private String roleId;
    @ApiModelProperty(value = "权限表ID")
    private String permissionId;

}
