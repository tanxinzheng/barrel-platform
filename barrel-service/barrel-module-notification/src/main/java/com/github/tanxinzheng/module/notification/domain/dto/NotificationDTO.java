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
@ApiModel(value = "通知")
public class NotificationDTO extends BaseModel implements Serializable {
    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    @ApiModelProperty(value = "主键")
    private String id;
    /** 通知类型 */
    @NotBlank(message = "通知类型为必填项")
    @Length(max = 20, message = "通知类型字符长度限制[0,20]")
    @ApiModelProperty(value = "通知类型")
    private String notificationType;
    /** 模板ID */
    @Length(max = 200, message = "模板ID字符长度限制[0,200]")
    @ApiModelProperty(value = "模板ID")
    private String templateId;
    /** 标题 */
    @NotBlank(message = "标题为必填项")
    @Length(max = 500, message = "标题字符长度限制[0,500]")
    @ApiModelProperty(value = "标题")
    private String title;
    /** 内容 */
    @NotBlank(message = "内容为必填项")
    @Length(max = 65535, message = "内容字符长度限制[0,65535]")
    @ApiModelProperty(value = "内容")
    private String body;

}
