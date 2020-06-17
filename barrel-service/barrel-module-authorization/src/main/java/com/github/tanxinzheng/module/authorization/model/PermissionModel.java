
package com.github.tanxinzheng.module.authorization.model;

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
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
//@ExcelTarget(value = "PermissionModel")
@Data
public class PermissionModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;

    private String groupCode;

    private String groupId;

    private String permissionKey;
    /** 权限URL */
    //@Excel(name = "权限URL")
    @NotBlank(message = "权限URL为必填项")
    @Length(max = 100, message = "权限URL字符长度限制[0,100]")
    private String permissionUrl;
    /** 权限ACTION */
    //@Excel(name = "权限ACTION")
    @NotBlank(message = "权限ACTION为必填项")
    @Length(max = 100, message = "权限ACTION字符长度限制[0,100]")
    private String permissionAction;
    /** 权限描述 */
    //@Excel(name = "权限描述")
    @NotBlank(message = "权限描述为必填项")
    @Length(max = 200, message = "权限描述字符长度限制[0,200]")
    private String description;
    /** 激活 */
    //@Excel(name = "激活")
    @NotBlank(message = "激活为必填项")
    @Length(max = 1, message = "激活字符长度限制[0,1]")
    private Boolean active;
    /** 创建人 */
    //@Excel(name = "创建人")
    @NotBlank(message = "创建人为必填项")
    @Length(max = 32, message = "创建人字符长度限制[0,32]")
    private String createdUserId;
    /** 创建时间 */
    //@Excel(name = "创建时间")
    @NotNull(message = "创建时间为必填项")
    private Date createdTime;
    /** 更新人 */
    //@Excel(name = "更新人")
    @NotBlank(message = "更新人为必填项")
    @Length(max = 32, message = "更新人字符长度限制[0,32]")
    private String updatedUserId;
    /** 更新时间 */
    //@Excel(name = "更新时间")
    @NotNull(message = "更新时间为必填项")
    private Date updatedTime;

    /**
    * Get Permission Entity Object
    * @return
    */
    @JsonIgnore
    public Permission getEntity(){
        Permission permission = new Permission();
        BeanUtils.copyProperties(this, permission);
        return permission;
    }


}
