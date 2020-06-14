package com.github.tanxinzheng.module.authorization.model;

import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */
@Data
public class Group extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 用户组类型 */
    private String groupType;
    /** 用户组代码 */
    private String groupCode;
    /** 用户组名称 */
    private String groupName;
    /** 用户组描述 */
    private String description;
    /** 激活 */
    private Boolean active;


}
