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
@ApiModel(value = "权限资源")
public class PermissionVO implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "权限KEY")
    private String permissionKey;
    @ApiModelProperty(value = "权限组")
    private String permissionGroup;
    @ApiModelProperty(value = "权限URL")
    private String permissionUrl;
    @ApiModelProperty(value = "权限ACTION")
    private String permissionAction;
    @ApiModelProperty(value = "权限描述")
    private String description;
    @ApiModelProperty(value = "激活")
    private Boolean active;
    @ApiModelProperty(value = "父节点")
    private String parentId;
    @ApiModelProperty(value = "创建人")
    private String createdUserId;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    @ApiModelProperty(value = "更新人")
    private String updatedUserId;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


}
