package com.github.tanxinzheng.module.notification.domain.dto;

import com.github.tanxinzheng.framework.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 23:27:09
 */
@Data
@ApiModel(value = "通知发送")
public class NotificationSendDTO extends BaseModel implements Serializable {
    /** 物理主键 */
    @Length(max = 50, message = "物理主键字符长度限制[0,50]")
    @ApiModelProperty(value = "物理主键")
    private String id;
    /** 通知主键 */
    @NotBlank(message = "通知主键为必填项")
    @Length(max = 50, message = "通知主键字符长度限制[0,50]")
    @ApiModelProperty(value = "通知主键")
    private String notificationId;
    /** 发送人主键 */
    @NotBlank(message = "发送人主键为必填项")
    @Length(max = 50, message = "发送人主键字符长度限制[0,50]")
    @ApiModelProperty(value = "发送人主键")
    private String sender;
    /** 发送时间 */
    @NotNull(message = "发送时间为必填项")
    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;
    /** 状态 */
    @NotBlank(message = "状态为必填项")
    @Length(max = 10, message = "状态字符长度限制[0,10]")
    @ApiModelProperty(value = "状态")
    private String dataState;

}
