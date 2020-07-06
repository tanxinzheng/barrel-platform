package com.github.tanxinzheng.module.system.authorization.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.authorization.domain.dto.RoleDTO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.RoleDO;
import com.github.tanxinzheng.module.system.authorization.domain.vo.PermissionVO;
import com.github.tanxinzheng.module.system.authorization.domain.vo.RolePermissionQuery;
import com.github.tanxinzheng.module.system.authorization.domain.vo.RoleVO;
import com.github.tanxinzheng.module.system.authorization.service.RolePermissionRelationService;
import com.github.tanxinzheng.module.system.authorization.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Slf4j
@Api(tags = "角色接口")
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Resource
    RoleService roleService;
    
    @Resource
    RolePermissionRelationService rolePermissionRelationService;

    /**
     * 分页查询角色集合
     * @param queryParams
     * @return
     */
    @ApiOperation(value = "分页查询角色")
    @GetMapping
    public IPage<RoleVO> findPage(QueryParams<RoleDO> queryParams){
        IPage<RoleDTO> page = roleService.findPage(queryParams.getPage(), queryParams.getQueryWrapper());
        return BeanCopierUtils.copy(page, RoleVO.class);
    }

    /**
     * 主键查询角色
     * @param   id  主键
     * @return  RoleResponse   角色领域对象
     */
    @ApiOperation(value = "主键查询角色")
    @GetMapping(value = "/{id}")
    public RoleVO findById(@PathVariable(value = "id") String id){
        RoleDTO roleDTO = roleService.findById(id);
        return BeanCopierUtils.copy(roleDTO, RoleVO.class);
    }

    /**
     * 新增角色
     * @param roleDTO
     * @return
     */
    @ApiOperation(value = "新增角色")
    @PostMapping
    public RoleVO create(@RequestBody @Valid RoleDTO roleDTO) {
        roleDTO = roleService.createRole(roleDTO);
        return BeanCopierUtils.copy(roleDTO, RoleVO.class);
    }

    /**
     * 更新角色
     * @param id    主键
     * @param roleDTO  更新对象参数
     * @return  RoleResponse   角色领域对象
     */
    @ApiOperation(value = "更新角色")
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable(value = "id") String id,
                              @RequestBody @Valid RoleDTO roleDTO){
        if(StringUtils.isNotBlank(id)){
            roleDTO.setId(id);
        }
        return roleService.updateRole(roleDTO);
    }

    /**
     *  删除角色
     * @param id    主键
     */
    @ApiOperation(value = "删除单个角色")
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(value = "id") String id){
        return roleService.deleteRole(id);
    }

    /**
     *  删除角色
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除角色")
    @DeleteMapping
    public boolean batchDelete(@RequestBody List<String> ids){
        AssertValid.notEmpty(ids, "数组参数不能为空");
        return roleService.deleteRole(ids);
    }


    /**
     * 批量新增角色所属权限
     * @param roleId   角色主键
     * @param permissionIds     权限主键集
     */
    @ApiOperation(value = "添加角色权限")
    @PostMapping(value = "/{roleId}/permissions")
    public boolean createGroupPermission(
            @PathVariable(value = "roleId") String roleId,
            @RequestBody List<String> permissionIds){
        return rolePermissionRelationService.relatePermission(roleId, permissionIds, true);
    }

    /**
     * 批量移除角色权限
     * @param roleId   角色主键
     * @param permissionIds     权限主键集
     */
    @ApiOperation(value = "移除角色的权限")
    @DeleteMapping(value = "/{roleId}/permissions")
    public boolean deleteRolePermission(
            @PathVariable(value = "roleId") String roleId,
            @RequestBody List<String> permissionIds){
        AssertValid.notEmpty(permissionIds, "权限数组参数不能为空");
        return rolePermissionRelationService.relatePermission(roleId, permissionIds, false);
    }

    /**
     * 查询角色已、未绑定权限
     * @param roleId
     * @param rolePermissionQuery
     * @return
     */
    @ApiOperation(value = "查询角色已、未绑定权限")
    @GetMapping(value = "/{roleId}/permissions")
    public IPage<PermissionVO> findRolePermission(@ApiParam(value = "角色主键") @PathVariable(value = "roleId") String roleId,
                                                  @RequestBody RolePermissionQuery rolePermissionQuery){
        return BeanCopierUtils.copy(rolePermissionRelationService.findRolePermission(rolePermissionQuery.getPageNum(),
                rolePermissionQuery.getPageSize(),
                rolePermissionQuery.getRoleId(),
                rolePermissionQuery.getIsRelate()), PermissionVO.class);
    }


}
