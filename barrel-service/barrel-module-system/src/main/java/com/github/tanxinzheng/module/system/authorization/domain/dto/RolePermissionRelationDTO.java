package com.github.tanxinzheng.module.system.authorization.domain.dto;

import com.github.tanxinzheng.framework.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Data
@ApiModel(value = "角色权限关系")
public class RolePermissionRelationDTO extends BaseModel implements Serializable {
    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    @ApiModelProperty(value = "主键")
    private String id;
    /** 组表ID */
    @NotBlank(message = "组表ID为必填项")
    @Length(max = 32, message = "组表ID字符长度限制[0,32]")
    @ApiModelProperty(value = "组表ID")
    private String roleId;
    /** 权限表ID */
    @NotBlank(message = "权限表ID为必填项")
    @Length(max = 32, message = "权限表ID字符长度限制[0,32]")
    @ApiModelProperty(value = "权限表ID")
    private String permissionId;

}
