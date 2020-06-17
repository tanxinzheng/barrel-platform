package com.github.tanxinzheng.module.authorization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.tanxinzheng.framework.model.BaseModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

//import org.jeecgframework.poi.excel.annotation.Excel;
//import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
//@ExcelTarget(value = "GroupPermissionModel")
@Data
public class GroupPermissionModel extends BaseModel implements Serializable {

    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /** 组表ID */
    //@Excel(name = "组表ID")
    @NotBlank(message = "组表ID为必填项")
    @Length(max = 32, message = "组表ID字符长度限制[0,32]")
    private String groupId;
    /** 权限表ID */
    //@Excel(name = "权限表ID")
    @NotBlank(message = "权限表ID为必填项")
    @Length(max = 32, message = "权限表ID字符长度限制[0,32]")
    private String permissionId;

    /**
    * Get GroupPermission Entity Object
    * @return
    */
    @JsonIgnore
    public GroupPermission getEntity(){
        GroupPermission groupPermission = new GroupPermission();
        BeanUtils.copyProperties(this, groupPermission);
        return groupPermission;
    }


}
