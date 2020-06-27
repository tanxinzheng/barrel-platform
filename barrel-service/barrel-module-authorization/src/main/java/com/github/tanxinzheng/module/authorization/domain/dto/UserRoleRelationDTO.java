package com.github.tanxinzheng.module.authorization.domain.dto;

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
@ApiModel(value = "用户角色关系")
public class UserRoleRelationDTO extends BaseModel implements Serializable {
    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    @ApiModelProperty(value = "主键")
    private String id;
    /** 用户表ID */
    @NotBlank(message = "用户表ID为必填项")
    @Length(max = 32, message = "用户表ID字符长度限制[0,32]")
    @ApiModelProperty(value = "用户表ID")
    private String userId;
    /** 组表ID */
    @NotBlank(message = "组表ID为必填项")
    @Length(max = 32, message = "组表ID字符长度限制[0,32]")
    @ApiModelProperty(value = "组表ID")
    private String roleId;

}
