package com.github.tanxinzheng.module.menu.model;

import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.lang.Integer;
import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2019-1-11 22:32:46
 * @version 1.0.0
 */
public @Data class Menu extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 编码 */
    private String code;
    /** 标题 */
    private String title;
    /** 父节点 */
    private String parentId;
    /** 路径 */
    private String url;
    /** 图标 */
    private String icon;
    /** 排序 */
    private Integer sort;
    /** 描述 */
    private String description;
    /** 启用 */
    private Boolean enable;


}
