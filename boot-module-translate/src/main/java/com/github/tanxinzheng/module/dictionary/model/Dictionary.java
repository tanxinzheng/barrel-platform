package com.github.tanxinzheng.module.dictionary.model;

import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  tanxinzheng
 * @date    2017-6-12 1:53:38
 * @version 1.0.0
 */
@Data
public class Dictionary extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 字典组名称 */
    private String groupName;
    /** 字典组代码 */
    private String groupCode;
    /** 名称 */
    private String dictionaryName;
    /** 代码 */
    private String dictionaryCode;
    /** 排序 */
    private Integer sort;
    /** 激活 */
    private Boolean active;
    /** 父节点 */
    private String parentId;
    /** 显示 */
    private Boolean isShow;
    /** 创建人 */
    private String createdUserId;
    /** 创建时间 */
    private Date createdTime;
    /** 更新人 */
    private String updatedUserId;
    /** 更新时间 */
    private Date updatedTime;
    /** 数据版本号 */
    private Integer dataVersion;

    public void setGroupCode(String groupCode) {
        if(groupCode == null){
            return;
        }
        this.groupCode = groupCode.toUpperCase();
    }

    public void setDictionaryCode(String dictionaryCode) {
        if(dictionaryCode == null){
            return;
        }
        this.dictionaryCode = dictionaryCode.toUpperCase();
    }
}
