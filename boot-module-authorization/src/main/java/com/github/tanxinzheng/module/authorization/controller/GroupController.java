package com.github.tanxinzheng.module.authorization.controller;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.logger.ActionLog;
import com.github.tanxinzheng.module.authorization.model.*;
import com.github.tanxinzheng.module.authorization.service.GroupPermissionService;
import com.github.tanxinzheng.module.authorization.service.GroupService;
import com.github.tanxinzheng.module.authorization.service.UserGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */
@Api(value = "group", description = "用户组管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    GroupPermissionService groupPermissionService;

    @Autowired
    UserGroupService userGroupService;

    /**
     * 用户组列表
     * @param   groupQuery    用户组查询参数对象
     * @return  Page<GroupModel> 用户组领域分页对象
     */
    @ApiOperation(value = "查询用户组列表", notes = "获取商品信息(用于数据同步)", httpMethod = "POST", produces=MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public Page<GroupModel> getGroupList(GroupQuery groupQuery){
        return groupService.getGroupModelPage(groupQuery);
    }

    /**
     * 查询单个用户组
     * @param   id  主键
     * @return  GroupModel   用户组领域对象
     */
    @ApiOperation(value = "查询用户组")
    @GetMapping(value = "/{id}")
    public GroupModel getGroupById(@PathVariable(value = "id") String id){
        return groupService.getOneGroupModel(id);
    }

    /**
     * 新增用户组
     * @param   groupModel  新增对象参数
     * @return  GroupModel   用户组领域对象
     */
    @ApiOperation(value = "新增用户组")
    @ActionLog(actionName = "新增用户组")
    //@PreAuthorize(value = "hasAnyAuthority('GROUP:CREATE')")
    @PostMapping
    public GroupModel createGroup(@RequestBody @Valid GroupModel groupModel) {
        groupModel.setGroupType(GroupModel.GROUP_TYPE_CUSTOM);
        return groupService.createGroup(groupModel);
    }

    /**
     * 更新用户组
     * @param id    主键
     * @param groupModel  更新对象参数
     * @return  GroupModel   用户组领域对象
     */
    @ApiOperation(value = "更新用户组")
    @ActionLog(actionName = "更新用户组")
    @PutMapping(value = "/{id}")
    public GroupModel updateGroup(@PathVariable(value = "id") String id,
                           @RequestBody @Valid GroupModel groupModel){
        if(StringUtils.isNotBlank(id)){
            groupModel.setId(id);
        }
        groupService.updateGroup(groupModel);
        return groupService.getOneGroupModel(id);
    }

    /**
     *  删除用户组
     * @param id    主键
     */
    @ApiOperation(value = "删除单个用户组")
    @ActionLog(actionName = "删除单个用户组")
    @DeleteMapping(value = "/{id}")
    public void deleteGroup(@PathVariable(value = "id") String id){
        groupService.deleteGroup(id);
    }

    /**
     *  删除用户组
     * @param groupQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除用户组")
    @ActionLog(actionName = "批量删除用户组")
    @DeleteMapping
    public void deleteGroups(@RequestBody GroupQuery groupQuery){
        groupService.deleteGroup(groupQuery.getIds());
    }

    /**
     * 批量新增用户组所属权限
     * @param groupId   组主键
     * @param permissionIds     权限主键集
     * @return List<GroupPermission>    组权限对象集
     */
    @ApiOperation(value = "绑定组权限")
    @ActionLog(actionName = "绑定组权限")
    @RequestMapping(value = "/{groupId}/permissions", method = RequestMethod.POST)
    public void createGroupPermission(
            @PathVariable(value = "groupId") String groupId,
            @RequestBody String[] permissionIds){
        groupPermissionService.createGroupPermissions(groupId, permissionIds);
    }

    /**
     * 批量新增用户组所属权限
     * @param groupId   组主键
     * @param permissionIds     权限主键集
     * @return List<GroupPermission>    组权限对象集
     */
    @ApiOperation(value = "移除用户组权限")
    @ActionLog(actionName = "移除用户组权限")
    @RequestMapping(value = "/{groupId}/permissions", method = RequestMethod.DELETE)
    public void deleteGroupPermission(
            @PathVariable(value = "groupId") String groupId,
            @RequestBody String[] permissionIds){
        groupPermissionService.deleteGroupPermissions(groupId, permissionIds);
    }

    /**
     * 查询用户组所属权限
     * @param groupId
     * @param groupPermissionQuery
     * @return
     */
    @ApiOperation(value = "查询用户组所属权限")
    @RequestMapping(value = "/{groupId}/permissions", method = RequestMethod.GET)
    public List<PermissionModel> findPermissionByGroup(@PathVariable(value = "groupId") String groupId,
                                                       GroupPermissionQuery groupPermissionQuery){
        groupPermissionQuery.setGroupId(groupId);
        return groupPermissionService.getGroupPermissions(groupPermissionQuery);
    }

    /**
     * 查询用户组所属权限
     * @param groupId
     * @param userGroupQuery
     * @return
     */
    @ApiOperation(value = "查询用户组已绑定用户")
    @RequestMapping(value = "/{groupId}/users", method = RequestMethod.GET)
    public List<UserGroupModel> findUserByGroup(@PathVariable(value = "groupId") String groupId,
                                                UserGroupQuery userGroupQuery){
        userGroupQuery.setGroupId(groupId);
        return userGroupService.getUserGroupModelList(userGroupQuery);
    }

    /**
     * 用户组批量绑定用户
     * @param groupId
     * @param userIds
     * @return
     */
    @ApiOperation(value = "用户组批量绑定用户")
    @RequestMapping(value = "/{groupId}/users", method = RequestMethod.POST)
    public void bindUser2Group(@PathVariable(value = "groupId") String groupId,
                               @RequestBody @NotEmpty String[] userIds){
        userGroupService.bindUsers2Group(groupId, userIds);
    }

    /**
     * 用户组批量解绑用户
     * @param groupId
     * @param relationIds
     */
    @ApiOperation(value = "用户组解绑用户")
    @RequestMapping(value = "/{groupId}/users", method = RequestMethod.DELETE)
    public void unbindUser2Group(@PathVariable(value = "groupId") String groupId,
                               @RequestBody @NotEmpty String[] relationIds){
        userGroupService.deleteUserGroup(relationIds);
    }

    /**
     * 查询用户组未绑定用户
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/{groupId}/users/unbind", method = RequestMethod.GET)
    public List<UserModel> findPermissionByGroup(@PathVariable(value = "groupId") String groupId){
        return userGroupService.getUnbindUsers(groupId);
    }
}
