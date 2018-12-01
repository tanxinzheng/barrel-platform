package com.github.tanxinzheng.module.authorization.mapper;

import com.github.tanxinzheng.module.authorization.model.PermissionModel;
import com.github.tanxinzheng.module.authorization.model.UserPermissionQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@Mapper
public interface UserPermissionMapper {


    /**
     * 查询用户权限
     * @param userPermissionQuery
     * @return
     */
    List<PermissionModel> getUserPermissions(UserPermissionQuery userPermissionQuery);

}
