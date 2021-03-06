package com.github.tanxinzheng.module.authorization.service.impl;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.core.model.SelectIndex;
import com.github.tanxinzheng.framework.core.model.SelectOptionModel;
import com.github.tanxinzheng.framework.core.model.SelectOptionQuery;
import com.github.tanxinzheng.framework.core.service.SelectService;
import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.mybatis.page.PageInterceptor;
import com.github.tanxinzheng.module.authorization.mapper.GroupMapper;
import com.github.tanxinzheng.module.authorization.mapper.UserGroupMapper;
import com.github.tanxinzheng.module.authorization.model.*;
import com.github.tanxinzheng.module.authorization.service.GroupService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */
@Service
public class GroupServiceImpl implements GroupService, SelectService {

    @Autowired
    GroupMapper groupMapper;
    @Autowired
    UserGroupMapper userGroupMapper;

    /**
     * 新增用户组
     *
     * @param groupModel 新增用户组对象参数
     * @return GroupModel    用户组领域对象
     */
    @Override
    @Transactional
    public GroupModel createGroup(GroupModel groupModel) {
        Group group = createGroup(groupModel.getEntity());
        if(group != null){
            return getOneGroupModel(group.getId());
        }
        return null;
    }

    /**
     * 新增用户组实体对象
     *
     * @param group 新增用户组实体对象参数
     * @return Group 用户组实体对象
     */
    @Override
    @Transactional
    public Group createGroup(Group group) {
        group.setGroupCode(group.getGroupCode().toUpperCase());
        groupMapper.insertSelective(group);
        return group;
    }

    /**
    * 批量新增用户组
    *
    * @param groupModels 新增用户组对象集合参数
    * @return List<GroupModel>    用户组领域对象集合
    */
    @Override
    @Transactional
    public List<GroupModel> createGroups(List<GroupModel> groupModels) {
        List<GroupModel> groupModelList = null;
        for (GroupModel groupModel : groupModels) {
            groupModel = createGroup(groupModel);
            if(groupModel != null){
                if(groupModelList == null){
                    groupModelList = new ArrayList<>();
                }
                groupModelList.add(groupModel);
            }
        }
        return groupModelList;
    }

    /**
    * 更新用户组
    *
    * @param groupModel 更新用户组对象参数
    * @param groupQuery 过滤用户组对象参数
    */
    @Override
    @Transactional
    public void updateGroup(GroupModel groupModel, GroupQuery groupQuery) {
        groupMapper.updateSelectiveByQuery(groupModel.getEntity(), groupQuery);
    }

    /**
     * 更新用户组
     *
     * @param groupModel 更新用户组对象参数
     */
    @Override
    @Transactional
    public void updateGroup(GroupModel groupModel) {
        updateGroup(groupModel.getEntity());
    }

    /**
     * 更新用户组实体对象
     *
     * @param group 新增用户组实体对象参数
     * @return Group 用户组实体对象
     */
    @Override
    @Transactional
    public void updateGroup(Group group) {
        groupMapper.updateSelective(group);
    }

    /**
     * 删除用户组
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteGroup(String[] ids) {
        if(ArrayUtils.isEmpty(ids)){
            return;
        }
        GroupQuery groupQuery = new GroupQuery();
        groupQuery.setIds(ids);
        groupQuery.setGroupType(GroupModel.GROUP_TYPE_SYSTEM);
        List<Group> list = groupMapper.select(groupQuery);
        if(CollectionUtils.isNotEmpty(list)){
            throw new BusinessException("禁止删除系统用户组");
        }
        groupMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除用户组
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteGroup(String id) {
        UserGroupQuery userGroupQuery = new UserGroupQuery();
        userGroupQuery.setGroupId(id);
        List<UserGroup> list = userGroupMapper.select(userGroupQuery);
        if(CollectionUtils.isNotEmpty(list)){
            throw new BusinessException("该用户组下已绑定用户，请移除用户后重新操作。");
        }
        GroupQuery groupQuery = new GroupQuery();
        groupQuery.setGroupType(GroupModel.GROUP_TYPE_SYSTEM);
        groupQuery.setId(id);
        List<Group> groups = groupMapper.select(groupQuery);
        if(CollectionUtils.isNotEmpty(groups)){
            throw new BusinessException("该用户组为系统用户组，无法删除。");
        }
        groupMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询用户组领域分页对象（带参数条件）
     *
     * @param groupQuery 查询参数
     * @return Page<GroupModel>   用户组参数对象
     */
    @Override
    public Page<GroupModel> getGroupModelPage(GroupQuery groupQuery) {
        PageInterceptor.startPage(groupQuery);
        groupMapper.selectModel(groupQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询用户组领域集合对象（带参数条件）
     *
     * @param groupQuery 查询参数对象
     * @return List<GroupModel> 用户组领域集合对象
     */
    @Override
    public List<GroupModel> getGroupModelList(GroupQuery groupQuery) {
        return groupMapper.selectModel(groupQuery);
    }

    /**
     * 查询用户组
     *
     * @param groupQuery
     * @return
     */
    @Override
    public Page<GroupModel> getUserGroups(GroupQuery groupQuery) {
        return null;
    }

    /**
     * 查询用户组实体对象
     *
     * @param id 主键
     * @return Group 用户组实体对象
     */
    @Override
    public Group getOneGroup(String id) {
        return groupMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return GroupModel 用户组领域对象
     */
    @Override
    public GroupModel getOneGroupModel(String id) {
        return groupMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param groupQuery 用户组查询参数对象
     * @return GroupModel 用户组领域对象
     */
    @Override
    public GroupModel getOneGroupModel(GroupQuery groupQuery) {
        List<GroupModel> groupModelList = groupMapper.selectModel(groupQuery);
        if(CollectionUtils.isEmpty(groupModelList)){
            return null;
        }
        if(groupModelList.size() > 1){
            throw new BusinessException();
        }
        return groupModelList.get(0);
    }

    /**
     * 查询option数据
     *
     * @param selectOptionQuery
     * @return
     */
    @Override
    public List<SelectOptionModel> selectOptionModels(SelectOptionQuery selectOptionQuery) {
        GroupQuery groupQuery = new GroupQuery();
        groupQuery.setActive(Boolean.TRUE);
        groupQuery.setKeyword(selectOptionQuery.getKeyword());
        List<GroupModel> groupModelList = groupMapper.selectModel(groupQuery);
        if(CollectionUtils.isEmpty(groupModelList)){
            return Lists.newArrayList();
        }
        List<SelectOptionModel> selectOptionModelList = Lists.newArrayList();
        int i = 0;
        for (GroupModel groupModel : groupModelList) {
            SelectOptionModel selectOptionModel = new SelectOptionModel();
            selectOptionModel.setCode(groupModel.getGroupCode());
            selectOptionModel.setName(groupModel.getGroupName());
            selectOptionModel.setSort(i++);
            selectOptionModelList.add(selectOptionModel);
        }
        return selectOptionModelList;
    }

    /**
     * 获取select index
     *
     * @return
     */
    @Override
    public SelectIndex getSelectIndex() {
        return SelectIndex.GROUP;
    }
}
