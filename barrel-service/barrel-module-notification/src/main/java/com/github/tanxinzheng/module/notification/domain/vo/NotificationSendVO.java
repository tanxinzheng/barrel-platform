package com.github.tanxinzheng.module.notification.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class NotificationSendVO implements Serializable {

    @ApiModelProperty(value = "物理主键")
    private String id;
    @ApiModelProperty(value = "通知主键")
    private String notificationId;
    @ApiModelProperty(value = "发送人主键")
    private String sender;
    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;
    @ApiModelProperty(value = "状态")
    private String dataState;


}
