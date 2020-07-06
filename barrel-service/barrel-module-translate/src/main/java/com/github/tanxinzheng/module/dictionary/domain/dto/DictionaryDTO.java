package com.github.tanxinzheng.module.dictionary.domain.dto;

import com.github.tanxinzheng.framework.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-28 10:15:30
 */
@Data
@ApiModel(value = "数据字典")
public class DictionaryDTO extends BaseModel implements Serializable {
    /** 主键 */
    @NotBlank(message = "主键为必填项")
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    @ApiModelProperty(value = "主键")
    private String id;
    /** 字典组名称 */
    @NotBlank(message = "字典组名称为必填项")
    @Length(max = 50, message = "字典组名称字符长度限制[0,50]")
    @ApiModelProperty(value = "字典组名称")
    private String groupName;
    /** 字典组代码 */
    @Length(max = 50, message = "字典组代码字符长度限制[0,50]")
    @ApiModelProperty(value = "字典组代码")
    private String groupCode;
    /** 名称 */
    @NotBlank(message = "名称为必填项")
    @Length(max = 50, message = "名称字符长度限制[0,50]")
    @ApiModelProperty(value = "名称")
    private String dictionaryName;
    /** 代码 */
    @Length(max = 50, message = "代码字符长度限制[0,50]")
    @ApiModelProperty(value = "代码")
    private String dictionaryCode;
    /** 排序 */
    @NotNull(message = "排序为必填项")
    @Range(max = 10000000, min = 0, message = "排序数值范围[10000000,0]")
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /** 激活 */
    @NotNull(message = "激活为必填项")
    @ApiModelProperty(value = "激活")
    private Boolean active;
    /** 父节点 */
    @Length(max = 32, message = "父节点字符长度限制[0,32]")
    @ApiModelProperty(value = "父节点")
    private String parentId;
    /** 显示 */
    @NotNull(message = "显示为必填项")
    @ApiModelProperty(value = "显示")
    private Boolean isShow;
    /** 创建人 */
    @NotBlank(message = "创建人为必填项")
    @Length(max = 32, message = "创建人字符长度限制[0,32]")
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /** 创建时间 */
    @NotNull(message = "创建时间为必填项")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    /** 更新人 */
    @NotBlank(message = "更新人为必填项")
    @Length(max = 32, message = "更新人字符长度限制[0,32]")
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
    /** 更新时间 */
    @NotNull(message = "更新时间为必填项")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
    /** 数据版本号 */
    @Range(max = 10000000, min = 0, message = "数据版本号数值范围[10000000,0]")
    @ApiModelProperty(value = "数据版本号")
    private Integer dataVersion;

}
