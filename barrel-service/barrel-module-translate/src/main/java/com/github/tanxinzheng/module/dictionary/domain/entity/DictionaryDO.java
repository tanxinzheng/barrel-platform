package com.github.tanxinzheng.module.dictionary.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 14:25:34
 */
@Data
@TableName(value = "xmo_dictionary")
public class DictionaryDO extends BaseEntity implements Serializable {

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
    /** 创建人 */
    @TableField(value = "CREATED_USER_ID")
    private String createdUserId;
    /** 创建时间 */
    @TableField(value = "CREATED_TIME")
    private LocalDateTime createdTime;
    /** 更新人 */
    @TableField(value = "UPDATED_USER_ID")
    private String updatedUserId;
    /** 更新时间 */
    @TableField(value = "UPDATED_TIME")
    private LocalDateTime updatedTime;
    /** 数据版本号 */
    @TableField(value = "DATA_VERSION")
    private Integer dataVersion;


}
