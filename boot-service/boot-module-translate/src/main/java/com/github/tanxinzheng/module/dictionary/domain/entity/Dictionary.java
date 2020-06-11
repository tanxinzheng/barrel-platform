package com.github.tanxinzheng.module.dictionary.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "xmo_dictionary")
public class Dictionary extends BaseEntity implements Serializable {

    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /** 字典组名称 */
    @TableField(value = "GROUP_NAME")
    private String groupName;
    /** 字典组代码 */
    @TableId(value = "GROUP_CODE")
    private String groupCode;
    /** 名称 */
    @TableField(value = "DICTIONARY_NAME")
    private String dictionaryName;
    /** 代码 */
    @TableField(value = "DICTIONARY_CODE")
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
