package com.github.tanxinzheng.module.system.logger.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-7 10:42:23
 */
@Data
@ApiModel(value = "操作日志")
public class ActionLogVO implements Serializable {

    @ApiModelProperty(value = "物理主键")
    private String id;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "操作名称")
    private String actionName;
    @ApiModelProperty(value = "操作时间")
    private LocalDateTime actionDate;
    @ApiModelProperty(value = "参数")
    private String actionParams;
    @ApiModelProperty(value = "客户端IP")
    private String clientIp;
    @ApiModelProperty(value = "操作结果")
    private String actionResult;
    @ApiModelProperty(value = "类名")
    private String targetClass;
    @ApiModelProperty(value = "方法名")
    private String targetMethod;


}
