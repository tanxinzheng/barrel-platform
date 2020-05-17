package com.github.tanxinzheng.module.dictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.tanxinzheng.framework.model.BaseModel;
import com.github.tanxinzheng.module.dictionary.domain.entity.Dictionary;
import com.github.tanxinzheng.module.dictionary.web.AccountField;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

//import org.jeecgframework.poi.excel.annotation.Excel;
//import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * @author  tanxinzheng
 * @date    2017-6-12 1:53:38
 * @version 1.0.0
 */
//@ExcelTarget(value = "DictionaryModel")
@Data
public class DictionaryModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 字典组名称 */
    //@Excel(name = "字典组名称")
    @NotBlank(message = "字典组名称为必填项")
    @Length(max = 50, message = "字典组名称字符长度限制[0,50]")
    private String groupName;
    /** 字典组代码 */
    //@Excel(name = "字典组代码")
    @NotBlank(message = "字典组代码为必填项")
    @Length(max = 50, message = "字典组代码字符长度限制[0,50]")
    private String groupCode;
    /** 名称 */
    //@Excel(name = "名称")
    @NotBlank(message = "名称为必填项")
    @Length(max = 50, message = "名称字符长度限制[0,50]")
    private String dictionaryName;
    /** 代码 */
    //@Excel(name = "代码")
    @NotBlank(message = "代码为必填项")
    @Length(max = 50, message = "代码字符长度限制[0,50]")
    private String dictionaryCode;
    /** 排序 */
    //@Excel(name = "排序")
    @Range(max = 999999999, min = -999999999, message = "排序数值范围[999999999,-999999999]")
    private int sort;
    /** 激活 */
    //@Excel(name = "激活", replace = { "是_true", "否_false" })
    private boolean active;
    /** 父节点 */
//    //@Excel(name = "父节点")
    @Length(max = 32, message = "父节点字符长度限制[0,32]")
    private String parentId;
    /** 显示 */
    //@Excel(name = "显示", replace = { "是_true", "否_false" })
    @NotNull(message = "显示为必填项")
    private boolean isShow;
    /** 创建人 */
//    //@Excel(name = "创建人")
    @Length(max = 32, message = "创建人字符长度限制[0,32]")
    @AccountField
    private String createdUserId;
    /** 创建时间 */
//    //@Excel(name = "创建时间")
    private Date createdTime;
    /** 更新人 */
//    //@Excel(name = "更新人")
    @Length(max = 32, message = "更新人字符长度限制[0,32]")
    @AccountField
    private String updatedUserId;
    /** 更新时间 */
//    //@Excel(name = "更新时间")
    private Date updatedTime;
    /** 数据版本号 */
    @Range(max = 999999999, min = -999999999, message = "数据版本号数值范围[999999999,-999999999]")
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

    /**
    * Get Dictionary Entity Object
    * @return
    */
    @JsonIgnore
    public Dictionary getEntity(){
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(this, dictionary);
        return dictionary;
    }


}
