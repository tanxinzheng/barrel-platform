package com.github.tanxinzheng.module.system.dictionary.domain.dto;

import com.github.tanxinzheng.framework.model.BaseModel;
import com.github.tanxinzheng.framework.web.validate.VInsert;
import com.github.tanxinzheng.framework.web.validate.VUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(groups = { VUpdate.class }, message = "主键为必填项")
    @Length(groups = { VUpdate.class, VInsert.class }, max = 32, message = "主键字符长度限制[0,32]")
    @ApiModelProperty(value = "主键")
    private String id;
    /** 字典组名称 */
    @NotBlank(groups = { VUpdate.class, VInsert.class }, message = "字典组名称为必填项")
    @Length(groups = { VUpdate.class, VInsert.class }, max = 50, message = "字典组名称字符长度限制[0,50]")
    @ApiModelProperty(value = "字典组名称")
    private String groupName;
    /** 字典组代码 */
    @Length(groups = { VUpdate.class, VInsert.class }, max = 50, message = "字典组代码字符长度限制[0,50]")
    @ApiModelProperty(value = "字典组代码")
    private String groupCode;
    /** 名称 */
    @NotBlank(message = "名称为必填项")
    @Length(groups = { VUpdate.class, VInsert.class }, max = 50, message = "名称字符长度限制[0,50]")
    @ApiModelProperty(value = "名称")
    private String dictionaryName;
    /** 代码 */
    @Length(groups = { VUpdate.class, VInsert.class }, max = 50, message = "代码字符长度限制[0,50]")
    @ApiModelProperty(value = "代码")
    private String dictionaryCode;
    /** 排序 */
    @Range(groups = { VUpdate.class, VInsert.class }, max = 10000000, min = 0, message = "排序数值范围[10000000,0]")
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /** 激活 */
    @ApiModelProperty(value = "激活")
    private Boolean active;
    /** 父节点 */
    @Length(groups = { VUpdate.class, VInsert.class }, max = 32, message = "父节点字符长度限制[0,32]")
    @ApiModelProperty(value = "父节点")
    private String parentId;
    /** 显示 */
    @ApiModelProperty(value = "显示")
    private Boolean isShow;
    /** 创建人 */
    @Length(groups = { VUpdate.class, VInsert.class }, max = 32, message = "创建人字符长度限制[0,32]")
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    /** 更新人 */
    @Length(groups = { VUpdate.class, VInsert.class }, max = 32, message = "更新人字符长度限制[0,32]")
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
    /** 更新时间 */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
    /** 数据版本号 */
    @Range(groups = { VUpdate.class, VInsert.class }, max = 10000000, min = 0, message = "数据版本号数值范围[10000000,0]")
    @ApiModelProperty(value = "数据版本号")
    private Integer dataVersion;

}
