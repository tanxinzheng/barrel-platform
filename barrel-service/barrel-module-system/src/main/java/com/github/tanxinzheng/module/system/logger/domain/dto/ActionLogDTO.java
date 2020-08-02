package com.github.tanxinzheng.module.system.logger.domain.dto;

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
 * @Date   2020-7-7 10:42:23
 */
@Data
@ApiModel(value = "操作日志")
public class ActionLogDTO extends BaseModel implements Serializable {
    /** 物理主键 */
    @Length(max = 32, message = "物理主键字符长度限制[0,32]")
    @ApiModelProperty(value = "物理主键")
    private String id;
    /** 用户ID */
    @Length(max = 32, message = "用户ID字符长度限制[0,32]")
    @ApiModelProperty(value = "用户ID")
    private String userId;
    /** 操作名称 */
    @NotBlank(message = "操作名称为必填项")
    @Length(max = 50, message = "操作名称字符长度限制[0,50]")
    @ApiModelProperty(value = "操作名称")
    private String actionName;
    /** 操作时间 */
    @NotNull(message = "操作时间为必填项")
    @ApiModelProperty(value = "操作时间")
    private LocalDateTime actionDate;
    /** 参数 */
    @Length(max = 255, message = "参数字符长度限制[0,255]")
    @ApiModelProperty(value = "参数")
    private String actionParams;
    /** 客户端IP */
    @NotBlank(message = "客户端IP为必填项")
    @Length(max = 100, message = "客户端IP字符长度限制[0,100]")
    @ApiModelProperty(value = "客户端IP")
    private String clientIp;
    /** 操作结果 */
    @Length(max = 500, message = "操作结果字符长度限制[0,500]")
    @ApiModelProperty(value = "操作结果")
    private String actionResult;
    /** 类名 */
    @Length(max = 1000, message = "类名字符长度限制[0,1000]")
    @ApiModelProperty(value = "类名")
    private String targetClass;
    /** 方法名 */
    @Length(max = 1000, message = "方法名字符长度限制[0,1000]")
    @ApiModelProperty(value = "方法名")
    private String targetMethod;

}
