package com.github.tanxinzheng.module.menu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.tanxinzheng.framework.model.BaseModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2019-1-11 22:32:46
 * @version 1.0.0
 */
@ExcelTarget(value = "MenuModel")
@Data
public class MenuModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 50, message = "主键字符长度限制[0,50]")
    private String id;
    /** 编码 */
    @Excel(name = "编码")
    @NotBlank(message = "编码为必填项")
    @Length(max = 100, message = "编码字符长度限制[0,100]")
    private String code;
    /** 标题 */
    @Excel(name = "标题")
    @NotBlank(message = "标题为必填项")
    @Length(max = 100, message = "标题字符长度限制[0,100]")
    private String title;
    /** 父节点 */
    @Excel(name = "父节点")
    @Length(max = 50, message = "父节点字符长度限制[0,50]")
    private String parentId;
    /** 路径 */
    @Excel(name = "路径")
    @NotBlank(message = "路径为必填项")
    @Length(max = 200, message = "路径字符长度限制[0,200]")
    private String url;
    /** 图标 */
    @Excel(name = "图标")
    @Length(max = 50, message = "图标字符长度限制[0,50]")
    private String icon;
    /** 排序 */
    @Excel(name = "排序")
    @NotNull(message = "排序为必填项")
    @Range(max = 100, min = 0, message = "排序数值范围[0,100]")
    private Integer sort;
    /** 描述 */
    @Excel(name = "描述")
    @Length(max = 500, message = "描述字符长度限制[0,500]")
    private String description;
    /** 启用 */
    @Excel(name = "启用")
    @NotBlank(message = "启用为必填项")
    private Boolean enable;

    /**
    * Get Menu Entity Object
    * @return
    */
    @JsonIgnore
    public Menu getEntity(){
        Menu menu = new Menu();
        BeanUtils.copyProperties(this, menu);
        return menu;
    }


}
