package com.github.tanxinzheng.module.dictionary.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.tanxinzheng.framework.web.annotation.AccountField;
import com.github.tanxinzheng.module.dictionary.domain.entity.Dictionary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "数据字典")
@Data
public class DictionaryResponse implements Serializable {

    /** 主键 */
    @ApiModelProperty(value = "主键")
    private String id;
    /** 字典组名称 */
    @ApiModelProperty(value = "字典组名称")
    private String groupName;
    /** 字典组代码 */
    @ApiModelProperty(value = "字典组代码")
    private String groupCode;
    /** 名称 */
    @ApiModelProperty(value = "名称")
    private String dictionaryName;
    /** 代码 */
    @ApiModelProperty(value = "代码")
    private String dictionaryCode;
    /** 排序 */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /** 激活 */
    @ApiModelProperty(value = "激活")
    private Boolean active;
    /** 父节点 */
    @ApiModelProperty(value = "父节点")
    private String parentId;
    /** 显示 */
    @ApiModelProperty(value = "显示")
    private Boolean isShow;
    /** 创建人 */
    @AccountField
    @ApiModelProperty(value = "创建人")
    private String createdUserId;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /** 更新人 */
    @AccountField
    @ApiModelProperty(value = "更新人")
    private String updatedUserId;
    /** 更新时间 */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /** 数据版本号 */
    @ApiModelProperty(value = "数据版本号")
    private Integer dataVersion;

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

    /**
    * entity object convert to response object
    * @param dictionary
    * @return
    */
    @JsonIgnore
    public static DictionaryResponse toResponse(Dictionary dictionary){
        if(null == dictionary){
            return null;
        }
        DictionaryResponse dictionaryResponse = new DictionaryResponse();
        BeanUtils.copyProperties(dictionary, dictionaryResponse);
        return dictionaryResponse;
    }


}
