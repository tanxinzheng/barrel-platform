package com.github.tanxinzheng.module.authorization.domain.dto;

import com.github.tanxinzheng.framework.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Data
@ApiModel(value = "角色")
public class RoleDTO extends BaseModel implements Serializable {
    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    @ApiModelProperty(value = "主键")
    private String id;
    /** 角色（用户组）代码 */
    @NotBlank(message = "角色（用户组）代码为必填项")
    @Length(max = 30, message = "角色（用户组）代码字符长度限制[0,30]")
    @ApiModelProperty(value = "角色（用户组）代码")
    private String roleCode;
    /** 角色（用户组）名称 */
    @NotBlank(message = "角色（用户组）名称为必填项")
    @Length(max = 100, message = "角色（用户组）名称字符长度限制[0,100]")
    @ApiModelProperty(value = "角色（用户组）名称")
    private String roleName;
    /** 描述 */
    @NotBlank(message = "描述为必填项")
    @Length(max = 200, message = "描述字符长度限制[0,200]")
    @ApiModelProperty(value = "描述")
    private String description;
    /** 激活 */
    @NotNull(message = "激活为必填项")
    @ApiModelProperty(value = "激活")
    private Boolean active;
    /** 角色（用户组）类型 */
    @NotBlank(message = "角色（用户组）类型为必填项")
    @Length(max = 20, message = "角色（用户组）类型字符长度限制[0,20]")
    @ApiModelProperty(value = "角色（用户组）类型")
    private String roleType;

}
