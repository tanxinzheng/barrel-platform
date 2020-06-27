package com.github.tanxinzheng.module.authorization.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.authorization.domain.dto.RolePermissionRelationDTO;
import com.github.tanxinzheng.module.authorization.domain.entity.RolePermissionRelationDO;
import com.github.tanxinzheng.module.authorization.domain.vo.RolePermissionRelationVO;
import com.github.tanxinzheng.module.authorization.service.RolePermissionRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "角色权限关系接口")
@RestController
@RequestMapping(value = "/role-permission")
public class RolePermissionRelationController {

    @Resource
    RolePermissionRelationService rolePermissionRelationService;

    /**
     * 分页查询角色权限关系集合
     * @param queryParams
     * @return
     */
    @ApiOperation(value = "分页查询角色权限关系")
    @GetMapping
    public IPage<RolePermissionRelationVO> findPage(QueryParams<RolePermissionRelationDO> queryParams){
        IPage<RolePermissionRelationDTO> page = rolePermissionRelationService.findPage(queryParams.getPage(), queryParams.getQueryWrapper());
        return BeanCopierUtils.copy(page, RolePermissionRelationVO.class);
    }

    /**
     * 主键查询角色权限关系
     * @param   id  主键
     * @return  RolePermissionRelationResponse   角色权限关系领域对象
     */
    @ApiOperation(value = "主键查询角色权限关系")
    @GetMapping(value = "/{id}")
    public RolePermissionRelationVO findById(@PathVariable(value = "id") String id){
        RolePermissionRelationDTO rolePermissionRelationDTO = rolePermissionRelationService.findById(id);
        return BeanCopierUtils.copy(rolePermissionRelationDTO, RolePermissionRelationVO.class);
    }

    /**
     * 新增角色权限关系
     * @param rolePermissionRelationDTO
     * @return
     */
    @ApiOperation(value = "新增角色权限关系")
    @PostMapping
    public RolePermissionRelationVO create(@RequestBody @Valid RolePermissionRelationDTO rolePermissionRelationDTO) {
        rolePermissionRelationDTO = rolePermissionRelationService.createRolePermissionRelation(rolePermissionRelationDTO);
        return BeanCopierUtils.copy(rolePermissionRelationDTO, RolePermissionRelationVO.class);
    }

    /**
     * 更新角色权限关系
     * @param id    主键
     * @param rolePermissionRelationDTO  更新对象参数
     * @return  RolePermissionRelationResponse   角色权限关系领域对象
     */
    @ApiOperation(value = "更新角色权限关系")
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable(value = "id") String id,
                              @RequestBody @Valid RolePermissionRelationDTO rolePermissionRelationDTO){
        if(StringUtils.isNotBlank(id)){
            rolePermissionRelationDTO.setId(id);
        }
        return rolePermissionRelationService.updateRolePermissionRelation(rolePermissionRelationDTO);
    }

    /**
     *  删除角色权限关系
     * @param id    主键
     */
    @ApiOperation(value = "删除单个角色权限关系")
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(value = "id") String id){
        return rolePermissionRelationService.deleteRolePermissionRelation(id);
    }

    /**
     *  删除角色权限关系
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除角色权限关系")
    @DeleteMapping
    public boolean batchDelete(@RequestBody List<String> ids){
        AssertValid.notEmpty(ids, "数组参数不能为空");
        return rolePermissionRelationService.deleteRolePermissionRelation(ids);
    }


}
