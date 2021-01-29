package com.github.tanxinzheng.module.system.menu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
public class MenuVO implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "父节点")
    private String parentId;
    @ApiModelProperty(value = "路径")
    private String url;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "启用")
    private Boolean isEnable;
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


}
