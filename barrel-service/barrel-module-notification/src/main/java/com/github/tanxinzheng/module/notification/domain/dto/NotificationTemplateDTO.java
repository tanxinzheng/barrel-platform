package com.github.tanxinzheng.module.notification.domain.dto;

import com.github.tanxinzheng.framework.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 23:27:09
 */
@Data
@ApiModel(value = "通知模板")
public class NotificationTemplateDTO extends BaseModel implements Serializable {
    /** 主键 */
    @Length(max = 50, message = "主键字符长度限制[0,50]")
    @ApiModelProperty(value = "主键")
    private String id;
    /** 模板代码 */
    @NotBlank(message = "模板代码为必填项")
    @Length(max = 200, message = "模板代码字符长度限制[0,200]")
    @ApiModelProperty(value = "模板代码")
    private String templateCode;
    /** 模板标题 */
    @NotBlank(message = "模板标题为必填项")
    @Length(max = 1000, message = "模板标题字符长度限制[0,1000]")
    @ApiModelProperty(value = "模板标题")
    private String templateTitle;
    /** 模板内容 */
    @NotBlank(message = "模板内容为必填项")
    @Length(max = 65535, message = "模板内容字符长度限制[0,65535]")
    @ApiModelProperty(value = "模板内容")
    private String templateBody;
    /** 模板名称 */
    @NotBlank(message = "模板名称为必填项")
    @Length(max = 200, message = "模板名称字符长度限制[0,200]")
    @ApiModelProperty(value = "模板名称")
    private String templateName;

}
