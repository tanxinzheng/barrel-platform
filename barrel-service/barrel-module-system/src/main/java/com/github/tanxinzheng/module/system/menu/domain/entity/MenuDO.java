package com.github.tanxinzheng.module.system.menu.domain.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

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
@TableName(value = "xmo_menu")
public class MenuDO extends BaseEntity implements Serializable {

    /** 主键 */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /** 编码 */
    @TableField(value = "code")
    private String code;
    /** 标题 */
    @TableField(value = "title")
    private String title;
    /** 父节点 */
    @TableField(value = "parent_id")
    private String parentId;
    /** 路径 */
    @TableField(value = "url")
    private String url;
    /** 图标 */
    @TableField(value = "icon")
    private String icon;
    /** 排序 */
    @TableField(value = "sort")
    private Integer sort;
    /** 描述 */
    @TableField(value = "description")
    private String description;
    /** 启用 */
    @TableField(value = "is_enable")
    private Boolean isEnable;
    /** 创建人 */
    @TableField(value = "created_by")
    private String createdBy;
    /** 创建时间 */
    @TableField(value = "created_time")
    private LocalDateTime createdTime;
    /** 更新人 */
    @TableField(value = "updated_by")
    private String updatedBy;
    /** 更新时间 */
    @TableField(value = "updated_time")
    private LocalDateTime updatedTime;


}
