package com.github.tanxinzheng.module.authorization.model;

import com.github.tanxinzheng.framework.model.BaseQuery;
import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@Data
public class UserGroupQuery extends BaseQuery implements Serializable {

    private String keyword;
    private String id;
    private String[] ids;
    private String[] excludeIds;
    private String username;
    private String userId;
    private String groupId;
    private String[] groupIds;
    // true：已绑定的用户组，false：未绑定的用户组
    private Boolean hasBindGroup;

}
