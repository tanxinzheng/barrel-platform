package com.github.tanxinzheng.module.authorization.mapper;

import com.github.tanxinzheng.module.authorization.model.Group;
import com.github.tanxinzheng.module.authorization.model.GroupModel;
import com.github.tanxinzheng.module.authorization.model.GroupQuery;
import com.github.tanxinzheng.module.authorization.model.UserGroupQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */
@Mapper
public interface GroupMapper {

    List<Group> select(GroupQuery groupQuery);

    List<GroupModel> selectModel(GroupQuery groupQuery);

    Group selectByPrimaryKey(String primaryKey);

    GroupModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(Group record);

    int updateSelective(Group record);

    int updateSelectiveByQuery(@Param("record") Group record, @Param("query") GroupQuery example);

    GroupModel selectUserGroup(UserGroupQuery userGroupQuery);
}
