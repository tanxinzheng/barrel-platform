package com.github.tanxinzheng.module.authorization.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by tanxinzheng on 2018/10/22.
 */
@Data
public class PermissionCreate implements Serializable {

    @NotBlank(message = "权限组为必填项")
    @Length(max = 100, message = "权限组字符长度限制[0,100]")
    private String permissionGroup;
    /** 权限URL */
    @NotBlank(message = "权限URL为必填项")
    @Length(max = 100, message = "权限URL字符长度限制[0,100]")
    private String permissionUrl;
    /** 权限描述 */
    @NotBlank(message = "权限描述为必填项")
    @Length(max = 200, message = "权限描述字符长度限制[0,200]")
    private String description;
    /** 激活 */
    @NotNull(message = "启用状态为必填项")
    private Boolean active;
}
