package com.github.tanxinzheng.module.system.menu.domain.dto;

import com.github.tanxinzheng.framework.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;
import java.io.Serializable;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2021-1-28 11:48:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "菜单")
public class MenuDTO extends BaseModel implements Serializable {
    /** 主键 */
    @Length(max = 50, message = "主键字符长度限制[0,50]")
    @ApiModelProperty(value = "主键")
    private String id;
    /** 编码 */
    @Length(max = 100, message = "编码字符长度限制[0,100]")
    @ApiModelProperty(value = "编码")
    private String code;
    /** 标题 */
    @Length(max = 100, message = "标题字符长度限制[0,100]")
    @ApiModelProperty(value = "标题")
    private String title;
    /** 父节点 */
    @Length(max = 50, message = "父节点字符长度限制[0,50]")
    @ApiModelProperty(value = "父节点")
    private String parentId;
    /** 路径 */
    @Length(max = 200, message = "路径字符长度限制[0,200]")
    @ApiModelProperty(value = "路径")
    private String url;
    /** 图标 */
    @Length(max = 50, message = "图标字符长度限制[0,50]")
    @ApiModelProperty(value = "图标")
    private String icon;
    /** 排序 */
    @Range(max = 10000000, min = 0, message = "排序数值范围[10000000,0]")
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /** 描述 */
    @Length(max = 500, message = "描述字符长度限制[0,500]")
    @ApiModelProperty(value = "描述")
    private String description;
    /** 启用 */
    @ApiModelProperty(value = "启用")
    private Boolean isEnable;
    /** 创建人 */
    @Length(max = 255, message = "创建人字符长度限制[0,255]")
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    /** 更新人 */
    @Length(max = 255, message = "更新人字符长度限制[0,255]")
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
    /** 更新时间 */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

}
