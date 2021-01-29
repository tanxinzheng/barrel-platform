package com.github.tanxinzheng.module.system.authorization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.authorization.domain.dto.RoleDTO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.RoleDO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.RolePermissionRelationDO;
import com.github.tanxinzheng.module.system.authorization.mapper.RoleMapper;
import com.github.tanxinzheng.module.system.authorization.service.RoleService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {

    @Resource
    RoleMapper roleMapper;

    /**
     * 新增角色
     *
     * @param roleDTO
     * @return RoleResponse
     */
    @Transactional
    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        AssertValid.notNull(roleDTO, "roleDTO参数不能为空");
        RoleDO role = BeanCopierUtils.copy(roleDTO, RoleDO.class);
        boolean isOk = save(role);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(role, RoleDTO.class);
    }


    /**
     * 更新角色
     *
     * @param roleDTO
     * @return RoleResponse
     */
    @Transactional
    @Override
    public boolean updateRole(RoleDTO roleDTO) {
        AssertValid.notNull(roleDTO, "roleDTO不能为空");
        RoleDO roleDO = BeanCopierUtils.copy(roleDTO, RoleDO.class);
        return updateById(roleDO);
    }

    /**
     * 批量新增角色
     * @param roles
     * @return
     */
    @Transactional
    @Override
    public List<RoleDTO> createRoles(List<RoleDTO> roles) {
        AssertValid.notEmpty(roles, "roles参数不能为空");
        List<RoleDO> roleDOList = BeanCopierUtils.copy(roles, RoleDO.class);
        boolean isOK = saveBatch(roleDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = roleDOList.stream().map(RoleDO::getId).collect(Collectors.toList());
        List<RoleDO> data = roleMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, RoleDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return RoleResponse
     */
    @Override
    public RoleDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        RoleDO role = getById(id);
        return BeanCopierUtils.copy(role, RoleDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<RoleDTO>
    */
    @Override
    public List<RoleDTO> findList(QueryWrapper<RoleDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), RoleDTO.class);
    }
    /**
     * 查询角色领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<RoleDTO> findPage(IPage<RoleDO> page, QueryWrapper<RoleDO> queryWrapper) {
        IPage<RoleDO> data = (Page<RoleDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, RoleDTO.class);
    }

    /**
     * 批量删除角色
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deleteRole(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除角色
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteRole(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }
}
