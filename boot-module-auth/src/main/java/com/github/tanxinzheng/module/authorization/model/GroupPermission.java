package com.github.tanxinzheng.module.authorization.model;

import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@Data
public class GroupPermission extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 组表ID */
    private String groupId;
    /** 权限表ID */
    private String permissionId;


}
