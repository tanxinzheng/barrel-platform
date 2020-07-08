package com.github.tanxinzheng.module.notification.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 23:27:09
 */
@Data
@ApiModel(value = "通知模板")
public class NotificationTemplateVO implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "模板代码")
    private String templateCode;
    @ApiModelProperty(value = "模板标题")
    private String templateTitle;
    @ApiModelProperty(value = "模板内容")
    private String templateBody;
    @ApiModelProperty(value = "模板名称")
    private String templateName;


}
