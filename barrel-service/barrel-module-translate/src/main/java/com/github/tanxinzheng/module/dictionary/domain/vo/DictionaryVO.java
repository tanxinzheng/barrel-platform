package com.github.tanxinzheng.module.dictionary.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "数据字典")
public class DictionaryVO implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "字典组名称")
    private String groupName;
    @ApiModelProperty(value = "字典组代码")
    private String groupCode;
    @ApiModelProperty(value = "名称")
    private String dictionaryName;
    @ApiModelProperty(value = "代码")
    private String dictionaryCode;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "激活")
    private Boolean active;
    @ApiModelProperty(value = "父节点")
    private String parentId;
    @ApiModelProperty(value = "显示")
    private Boolean isShow;
    @ApiModelProperty(value = "创建人")
    private String createdUserId;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    @ApiModelProperty(value = "更新人")
    private String updatedUserId;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
    @ApiModelProperty(value = "数据版本号")
    private Integer dataVersion;


}
