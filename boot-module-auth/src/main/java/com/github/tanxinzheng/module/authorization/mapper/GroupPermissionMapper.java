package com.github.tanxinzheng.module.authorization.mapper;

import com.github.tanxinzheng.module.authorization.model.GroupPermission;
import com.github.tanxinzheng.module.authorization.model.GroupPermissionModel;
import com.github.tanxinzheng.module.authorization.model.GroupPermissionQuery;
import com.github.tanxinzheng.module.authorization.model.PermissionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@Mapper
public interface GroupPermissionMapper {

    List<GroupPermission> select(GroupPermissionQuery groupPermissionQuery);

    List<GroupPermissionModel> selectModel(GroupPermissionQuery groupPermissionQuery);

    GroupPermission selectByPrimaryKey(String primaryKey);

    GroupPermissionModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(GroupPermission record);

    int updateSelective(GroupPermission record);

    int updateSelectiveByQuery(@Param("record") GroupPermission record, @Param("query") GroupPermissionQuery example);

    List<PermissionModel> selectGroupPermissions(GroupPermissionQuery groupPermissionQuery);

    long countGroupPermissions(@Param("groupIds") List<String> groupIds, @Param("permissionIds") List<String> permissionIds);

    int deleteGroupPermissions(@Param("groupId") String groupIds, @Param("permissionIds") List<String> permissionIds);

}
