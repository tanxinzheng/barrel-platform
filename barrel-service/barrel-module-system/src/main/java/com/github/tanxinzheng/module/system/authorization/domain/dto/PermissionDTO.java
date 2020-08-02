package com.github.tanxinzheng.module.system.authorization.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.tanxinzheng.framework.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class PermissionDTO extends BaseModel implements Serializable {
    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    @ApiModelProperty(value = "主键")
    private String id;
    /** 权限KEY */
    @NotBlank(message = "权限KEY为必填项")
    @Length(max = 100, message = "权限KEY字符长度限制[0,100]")
    @ApiModelProperty(value = "权限KEY")
    private String permissionKey;
    /** 权限组 */
    @NotBlank(message = "权限组为必填项")
    @Length(max = 100, message = "权限组字符长度限制[0,100]")
    @ApiModelProperty(value = "权限组")
    private String permissionGroup;
    /** 权限URL */
    @NotBlank(message = "权限URL为必填项")
    @Length(max = 100, message = "权限URL字符长度限制[0,100]")
    @ApiModelProperty(value = "权限URL")
    private String permissionUrl;
    /** 权限ACTION */
    @NotBlank(message = "权限ACTION为必填项")
    @Length(max = 100, message = "权限ACTION字符长度限制[0,100]")
    @ApiModelProperty(value = "权限ACTION")
    private String permissionAction;
    /** 权限描述 */
    @NotBlank(message = "权限描述为必填项")
    @Length(max = 2000, message = "权限描述字符长度限制[0,2000]")
    @ApiModelProperty(value = "权限描述")
    private String description;
    /** 资源类型： MENU-菜单，BUTTON-按钮 */
    @ApiModelProperty(value = "资源类型： MENU-菜单，BUTTON-按钮")
    private String permissionType;
    /** 图标 */
    @ApiModelProperty(value = "图标")
    private String icon;
    /** 排序 */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /** 激活 */
    @NotNull(message = "激活为必填项")
    @ApiModelProperty(value = "激活")
    private Boolean active;
    /** 父节点 */
    @Length(max = 50, message = "父节点字符长度限制[0,50]")
    @ApiModelProperty(value = "父节点")
    private String parentId;
    /** 创建人 */
    @NotBlank(message = "创建人为必填项")
    @Length(max = 32, message = "创建人字符长度限制[0,32]")
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /** 创建时间 */
    @NotNull(message = "创建时间为必填项")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    /** 更新人 */
    @NotBlank(message = "更新人为必填项")
    @Length(max = 32, message = "更新人字符长度限制[0,32]")
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
    /** 更新时间 */
    @NotNull(message = "更新时间为必填项")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

}
