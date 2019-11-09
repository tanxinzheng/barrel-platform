package com.github.tanxinzheng.module.notification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.tanxinzheng.framework.model.BaseModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

//import org.jeecgframework.poi.excel.annotation.Excel;
//import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
//@ExcelTarget(value = "NotificationReceiveModel")
@Data
public class NotificationReceiveModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 关联通知主键 */
    //@Excel(name = "关联通知主键")
    @NotBlank(message = "关联通知主键为必填项")
    @Length(max = 32, message = "关联通知主键字符长度限制[0,32]")
    private String notificationId;
    /** 接收时间 */
    //@Excel(name = "接收时间")
    @NotNull(message = "接收时间为必填项")
    private Date receiveTime;
    /** 接收人 */
    //@Excel(name = "接收人")
    @NotBlank(message = "接收人为必填项")
    @Length(max = 32, message = "接收人字符长度限制[0,32]")
    private String receiver;
    /** 状态 */
    //@Excel(name = "状态")
    @NotBlank(message = "状态为必填项")
    @Length(max = 10, message = "状态字符长度限制[0,10]")
    private String dataState;

    /**
    * Get NotificationReceive Entity Object
    * @return
    */
    @JsonIgnore
    public NotificationReceive getEntity(){
        NotificationReceive notificationReceive = new NotificationReceive();
        BeanUtils.copyProperties(this, notificationReceive);
        return notificationReceive;
    }


}
