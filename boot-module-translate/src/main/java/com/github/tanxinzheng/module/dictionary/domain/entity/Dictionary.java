package com.github.tanxinzheng.module.dictionary.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;
import java.io.Serializable;

@Data
@TableName(value = "xmo_dictionary")
public class Dictionary extends BaseEntity implements Serializable {

    /** 主键 */
    @TableField(value = "ID")
    private String id;
    /** 字典组名称 */
    @TableField(value = "GROUP_NAME")
    private String groupName;
    /** 字典组代码 */
    @TableId(value = "GROUP_CODE", type = IdType.UUID)
    private String groupCode;
    /** 名称 */
    @TableField(value = "DICTIONARY_NAME")
    private String dictionaryName;
    /** 代码 */
    @TableId(value = "DICTIONARY_CODE", type = IdType.UUID)
    private String dictionaryCode;
    /** 排序 */
    @TableField(value = "SORT")
    private Integer sort;
    /** 激活 */
    @TableField(value = "ACTIVE")
    private Boolean active;
    /** 父节点 */
    @TableField(value = "PARENT_ID")
    private String parentId;
    /** 显示 */
    @TableField(value = "IS_SHOW")
    private Boolean isShow;
    /** 数据版本号 */
    @TableField(value = "DATA_VERSION")
    private Integer dataVersion;


}
