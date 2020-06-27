package com.github.tanxinzheng.module.authorization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.framework.utils.UUIDGenerator;
import com.github.tanxinzheng.module.authorization.domain.dto.PermissionDTO;
import com.github.tanxinzheng.module.authorization.domain.entity.PermissionDO;
import com.github.tanxinzheng.module.authorization.mapper.PermissionMapper;
import com.github.tanxinzheng.module.authorization.service.PermissionService;
import com.google.common.collect.Lists;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * @Description 角色接口
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Slf4j
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionDO> implements PermissionService {

    @Resource
    PermissionMapper permissionMapper;

    /**
     * 新增权限资源
     *
     * @param permissionDTO
     * @return PermissionResponse
     */
    @Transactional
    @Override
    public PermissionDTO createPermission(PermissionDTO permissionDTO) {
        AssertValid.notNull(permissionDTO, "permissionDTO参数不能为空");
        PermissionDO permission = permissionDTO.toDO(PermissionDO.class);
        boolean isOk = save(permission);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(permission, PermissionDTO.class);
    }


    /**
     * 更新权限资源
     *
     * @param permissionDTO
     * @return PermissionResponse
     */
    @Transactional
    @Override
    public boolean updatePermission(PermissionDTO permissionDTO) {
        AssertValid.notNull(permissionDTO, "permissionDTO不能为空");
        PermissionDO permissionDO = BeanCopierUtils.copy(permissionDTO, PermissionDO.class);
        return updateById(permissionDO);
    }

    /**
     * 批量新增权限资源
     * @param permissions
     * @return
     */
    @Transactional
    @Override
    public List<PermissionDTO> createPermissions(List<PermissionDTO> permissions) {
        AssertValid.notEmpty(permissions, "permissions参数不能为空");
        List<PermissionDO> permissionDOList = BeanCopierUtils.copy(permissions, PermissionDO.class);
        boolean isOK = saveBatch(permissionDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = permissionDOList.stream().map(PermissionDO::getId).collect(Collectors.toList());
        List<PermissionDO> data = permissionMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, PermissionDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return PermissionResponse
     */
    @Override
    public PermissionDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        PermissionDO permission = getById(id);
        return BeanCopierUtils.copy(permission, PermissionDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<PermissionDTO>
    */
    @Override
    public List<PermissionDTO> findList(QueryWrapper<PermissionDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), PermissionDTO.class);
    }
    /**
     * 查询权限资源领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<PermissionDTO> findPage(IPage<PermissionDO> page, QueryWrapper<PermissionDO> queryWrapper) {
        IPage<PermissionDO> data = (Page<PermissionDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, PermissionDTO.class);
    }

    /**
     * 批量删除权限资源
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deletePermission(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除权限资源
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deletePermission(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }


    @Resource
    private DocumentationCache documentationCache;

    @Resource
    private ServiceModelToSwagger2Mapper mapper;

    @Transactional
    @Override
    public void autoInitPermissions(String swaggerGroup, String updatedBy) {
        String groupName = Optional.ofNullable(swaggerGroup).orElse(Docket.DEFAULT_GROUP_NAME);
        Documentation documentation = documentationCache.documentationByGroup(groupName);
        List<PermissionDO> newList = Lists.newArrayList();
        if (documentation == null) {
            return;
        }
        Swagger swagger = mapper.mapDocumentation(documentation);
        Map<String, Path> map = swagger.getPaths();
        for (Map.Entry<String, Path> stringPathEntry : map.entrySet()) {
            Path path = stringPathEntry.getValue();
            Map<HttpMethod, Operation> operationMap = path.getOperationMap();
            for (HttpMethod httpMethod : operationMap.keySet()) {
                PermissionDO permission = new PermissionDO();
                permission.setId(UUIDGenerator.getInstance().getUUID());
                permission.setPermissionUrl(stringPathEntry.getKey());
                permission.setPermissionAction(httpMethod.name().toUpperCase());
                permission.setDescription(operationMap.get(httpMethod).getSummary());
                permission.setPermissionKey(httpMethod.name().toUpperCase() + ":" + stringPathEntry.getKey());
                permission.setActive(Boolean.TRUE);
                permission.setCreatedUserId(updatedBy);
                permission.setUpdatedUserId(updatedBy);
                newList.add(permission);
            }
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        List<PermissionDO> oldList = baseMapper.selectList(queryWrapper);
        Map<String, PermissionDO> oldMap = oldList.stream().collect(Collectors.toMap(PermissionDO::getPermissionKey, Function.identity()));
        Map<String, PermissionDO> newMap = newList.stream().collect(Collectors.toMap(PermissionDO::getPermissionKey, Function.identity()));
        List<PermissionDO> oldUpdateList = Lists.newArrayList();
        List<PermissionDO> newInsertList = Lists.newArrayList();
        newMap.forEach((key, newPermission) -> {
            PermissionDO oldPermission = oldMap.get(key);
            Optional<PermissionDO> model = Optional.of(oldPermission);
            if(!model.isPresent()){
                // 历史权限资源不存在
                newInsertList.add(newPermission);
            }else if(!newPermission.getDescription().equals(oldPermission.getDescription())
                    || !newPermission.getPermissionAction().equals(oldPermission.getPermissionAction())
                    || !newPermission.getPermissionUrl().equals(oldPermission.getPermissionUrl())){
                // 历史权限资源已存在
                oldPermission.setPermissionUrl(newPermission.getPermissionUrl());
                oldPermission.setDescription(newPermission.getDescription());
                oldPermission.setPermissionAction(newPermission.getPermissionAction());
                oldPermission.setUpdatedTime(LocalDateTime.now());
                oldPermission.setUpdatedUserId(newPermission.getUpdatedUserId());
                oldUpdateList.add(oldPermission);
            }
        });
        //
        List<String> oldDeleteList = Lists.newArrayList();
        oldMap.forEach((key, oldPermission) -> {
            PermissionDO newPermission = newMap.get(key);
            if(newPermission == null){
                oldDeleteList.add(oldPermission.getId());
            }
        });
        if(CollectionUtils.isNotEmpty(newInsertList)){
            saveBatch(newInsertList);
        }
        if(CollectionUtils.isNotEmpty(oldDeleteList)){
            removeByIds(oldDeleteList);
        }
        if(CollectionUtils.isNotEmpty(oldUpdateList)){
            updateBatchById(oldUpdateList);
        }
    }
}
