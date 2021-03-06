package com.github.tanxinzheng.module.authorization.controller;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.module.authorization.model.GroupPermissionModel;
import com.github.tanxinzheng.module.authorization.model.GroupPermissionQuery;
import com.github.tanxinzheng.module.authorization.service.GroupPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@Api(tags = {"权限管理"})
@RestController
@RequestMapping(value = "/group/permission")
public class GroupPermissionController {

    @Autowired
    GroupPermissionService groupPermissionService;

    /**
     * 组权限列表
     * @param   groupPermissionQuery    组权限查询参数对象
     * @return  Page<GroupPermissionModel> 组权限领域分页对象
     */
    @ApiOperation(value = "查询组权限列表")
    @GetMapping
    public Page<GroupPermissionModel> getGroupPermissionList(GroupPermissionQuery groupPermissionQuery){
        return groupPermissionService.getGroupPermissionModelPage(groupPermissionQuery);
    }

    /**
     * 查询单个组权限
     * @param   id  主键
     * @return  GroupPermissionModel   组权限领域对象
     */
    @ApiOperation(value = "查询组权限")
    @GetMapping(value = "/{id}")
    public GroupPermissionModel getGroupPermissionById(@PathVariable(value = "id") String id){
        return groupPermissionService.getOneGroupPermissionModel(id);
    }

    /**
     * 新增组权限
     * @param   groupPermissionModel  新增对象参数
     * @return  GroupPermissionModel   组权限领域对象
     */
    @ApiOperation(value = "新增组权限")
    @PostMapping
    public GroupPermissionModel createGroupPermission(@RequestBody @Valid GroupPermissionModel groupPermissionModel) {
        return groupPermissionService.createGroupPermission(groupPermissionModel);
    }

    /**
     * 更新组权限
     * @param id    主键
     * @param groupPermissionModel  更新对象参数
     * @return  GroupPermissionModel   组权限领域对象
     */
    @ApiOperation(value = "更新组权限")
    @PutMapping(value = "/{id}")
    public GroupPermissionModel updateGroupPermission(@PathVariable(value = "id") String id,
                           @RequestBody @Valid GroupPermissionModel groupPermissionModel){
        if(StringUtils.isNotBlank(id)){
            groupPermissionModel.setId(id);
        }
        groupPermissionService.updateGroupPermission(groupPermissionModel);
        return groupPermissionService.getOneGroupPermissionModel(id);
    }

    /**
     *  删除组权限
     * @param id    主键
     */
    @ApiOperation(value = "删除单个组权限")
    @DeleteMapping(value = "/{id}")
    public void deleteGroupPermission(@PathVariable(value = "id") String id){
        groupPermissionService.deleteGroupPermission(id);
    }

    /**
     *  删除组权限
     * @param groupPermissionQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除组权限")
    @DeleteMapping
    public void deleteGroupPermissions(GroupPermissionQuery groupPermissionQuery){
        Assert.notNull(groupPermissionQuery.getGroupId(), "groupId must be not null");
        Assert.notEmpty(groupPermissionQuery.getPermissionIds(), "permissionIds must be not empty");
        groupPermissionService.deleteGroupPermissions(groupPermissionQuery.getGroupId(), groupPermissionQuery.getPermissionIds());
    }


}
