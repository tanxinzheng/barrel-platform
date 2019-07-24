package com.github.tanxinzheng.module.authorization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.tanxinzheng.framework.model.BaseModel;
import com.github.tanxinzheng.framework.web.json.DictionaryIndex;
import com.github.tanxinzheng.module.dictionary.web.DictionaryTransfer;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */
@ExcelTarget(value = "GroupModel")
public @Data class GroupModel extends BaseModel implements Serializable {

    public static final String GROUP_TYPE_SYSTEM = "GROUP_TYPE_SYSTEM";
    public static final String GROUP_TYPE_CUSTOM = "GROUP_TYPE_CUSTOM";

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 用户组类型 */
    @Excel(name = "用户组类型")
    @Length(max = 100, message = "用户组类型字符长度限制[1,100]")
    @DictionaryTransfer(index = DictionaryIndex.USER_GROUP)
    private String groupType;
    /** 用户组代码 */
    @Excel(name = "用户组代码")
    @NotBlank(message = "用户组代码为必填项")
    @Length(max = 30, message = "用户组代码字符长度限制[1,30]")
    private String groupCode;
    /** 用户组名称 */
    @Excel(name = "用户组名称")
    @NotBlank(message = "用户组名称为必填项")
    @Length(max = 100, message = "用户组名称字符长度限制[1,100]")
    private String groupName;
    /** 用户组描述 */
    @Excel(name = "用户组描述")
    @Length(max = 200, message = "用户组描述字符长度限制[0,200]")
    private String description;
    /** 激活 */
    @Excel(name = "激活")
    private Boolean active;

    /**
    * Get Group Entity Object
    * @return
    */
    @JsonIgnore
    public Group getEntity(){
        Group group = new Group();
        BeanUtils.copyProperties(this, group);
        return group;
    }


}
