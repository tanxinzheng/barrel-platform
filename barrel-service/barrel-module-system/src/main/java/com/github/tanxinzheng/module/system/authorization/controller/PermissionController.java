package com.github.tanxinzheng.module.system.authorization.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.authorization.domain.dto.PermissionDTO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.PermissionDO;
import com.github.tanxinzheng.module.system.authorization.domain.vo.PermissionVO;
import com.github.tanxinzheng.module.system.authorization.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Slf4j
@Api(tags = "权限资源接口")
@RestController
@RequestMapping(value = "/permission")
public class PermissionController {

    @Resource
    PermissionService permissionService;

    /**
     * 分页查询权限资源集合
     * @param queryParams
     * @return
     */
    @ApiOperation(value = "分页查询权限资源")
    @GetMapping
    public IPage<PermissionVO> findPage(QueryParams<PermissionDO> queryParams){
        IPage<PermissionDTO> page = permissionService.findPage(queryParams.getPage(), queryParams.getQueryWrapper());
        return BeanCopierUtils.copy(page, PermissionVO.class);
    }

    /**
     * 主键查询权限资源
     * @param   id  主键
     * @return  PermissionResponse   权限资源领域对象
     */
    @ApiOperation(value = "主键查询权限资源")
    @GetMapping(value = "/{id}")
    public PermissionVO findById(@PathVariable(value = "id") String id){
        PermissionDTO permissionDTO = permissionService.findById(id);
        return BeanCopierUtils.copy(permissionDTO, PermissionVO.class);
    }

    /**
     * 新增权限资源
     * @param permissionDTO
     * @return
     */
    @ApiOperation(value = "新增权限资源")
    @PostMapping
    public PermissionVO create(@RequestBody @Valid PermissionDTO permissionDTO) {
        permissionDTO = permissionService.createPermission(permissionDTO);
        return BeanCopierUtils.copy(permissionDTO, PermissionVO.class);
    }

    /**
     * 更新权限资源
     * @param id    主键
     * @param permissionDTO  更新对象参数
     * @return  PermissionResponse   权限资源领域对象
     */
    @ApiOperation(value = "更新权限资源")
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable(value = "id") String id,
                              @RequestBody @Valid PermissionDTO permissionDTO){
        if(StringUtils.isNotBlank(id)){
            permissionDTO.setId(id);
        }
        return permissionService.updatePermission(permissionDTO);
    }

    /**
     *  删除权限资源
     * @param id    主键
     */
    @ApiOperation(value = "删除单个权限资源")
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(value = "id") String id){
        return permissionService.deletePermission(id);
    }

    /**
     *  删除权限资源
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除权限资源")
    @DeleteMapping
    public boolean batchDelete(@RequestBody List<String> ids){
        AssertValid.notEmpty(ids, "数组参数不能为空");
        return permissionService.deletePermission(ids);
    }

    /**
     * 获取所有RequestMappingInfo
     * @param request
     * @return
     */
    @ApiOperation(value = "获取所有未纳入权限控制资源")
    @PostMapping(value = "/sync")
    public Result syncAll(@RequestParam(value = "group", required = false) String swaggerGroup) {
        Map<String, Integer> result = permissionService.autoInitPermissions(swaggerGroup, "ROBOT");
        return Result.success(result);
    }

}
