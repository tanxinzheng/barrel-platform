package com.github.tanxinzheng.module.system.authorization.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.authorization.domain.dto.UserDTO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.UserDO;
import com.github.tanxinzheng.module.system.authorization.domain.vo.RoleVO;
import com.github.tanxinzheng.module.system.authorization.domain.vo.UserVO;
import com.github.tanxinzheng.module.system.authorization.service.UserRoleRelationService;
import com.github.tanxinzheng.module.system.authorization.service.UserService;
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
@Api(tags = "用户接口")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    UserRoleRelationService userRoleRelationService;

    /**
     * 分页查询用户集合
     * @param queryParams
     * @return
     */
    @ApiOperation(value = "分页查询用户")
    @GetMapping
    public IPage<UserVO> findPage(QueryParams<UserDO> queryParams){
        IPage<UserDTO> page = userService.findPage(queryParams.getPage(), queryParams.getQueryWrapper());
        return BeanCopierUtils.copy(page, UserVO.class);
    }

    /**
     * 主键查询用户
     * @param   id  主键
     * @return  UserResponse   用户领域对象
     */
    @ApiOperation(value = "主键查询用户")
    @GetMapping(value = "/{id}")
    public UserVO findById(@PathVariable(value = "id") String id){
        UserDTO userDTO = userService.findById(id);
        return BeanCopierUtils.copy(userDTO, UserVO.class);
    }

    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    @ApiOperation(value = "新增用户")
    @PostMapping
    public UserVO create(@RequestBody @Valid UserDTO userDTO) {
        userDTO = userService.createUser(userDTO);
        return BeanCopierUtils.copy(userDTO, UserVO.class);
    }

    /**
     * 更新用户
     * @param id    主键
     * @param userDTO  更新对象参数
     * @return  UserResponse   用户领域对象
     */
    @ApiOperation(value = "更新用户")
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable(value = "id") String id,
                              @RequestBody @Valid UserDTO userDTO){
        if(StringUtils.isNotBlank(id)){
            userDTO.setId(id);
        }
        return userService.updateUser(userDTO);
    }

    /**
     *  删除用户
     * @param id    主键
     */
    @ApiOperation(value = "删除单个用户")
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(value = "id") String id){
        return userService.deleteUser(id);
    }

    /**
     *  删除用户
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除用户")
    @DeleteMapping
    public boolean batchDelete(@RequestBody List<String> ids){
        AssertValid.notEmpty(ids, "数组参数不能为空");
        return userService.deleteUser(ids);
    }



    /**
     * 批量新增角色所属权限
     * @param userId   用户主键
     * @param roleIds  角色主键集
     */
    @ApiOperation(value = "添加角色权限")
    @PostMapping(value = "/{userId}/roles")
    public boolean createGroupPermission(
            @PathVariable(value = "userId", required = true) String userId,
            @RequestBody List<String> roleIds){
        return userRoleRelationService.relateRoles(userId, roleIds, true);
    }

    /**
     * 批量移除角色权限
     * @param roleId   角色主键
     * @param permissionIds     权限主键集
     */
    @ApiOperation(value = "移除角色的权限")
    @DeleteMapping(value = "/{userId}/roles")
    public boolean deleteRolePermission(
            @PathVariable(value = "userId", required = true) String userId,
            @RequestBody List<String> roleIds){
        AssertValid.notEmpty(roleIds, "角色数组参数不能为空");
        return userRoleRelationService.relateRoles(userId, roleIds, false);
    }

    /**
     * 查询角色已、未绑定权限
     * @param userId
     * @param isRelate
     * @return
     */
    @ApiOperation(value = "查询角色已、未绑定权限")
    @GetMapping(value = "/{userId}/roles")
    public List<RoleVO> findRolePermission(@PathVariable(value = "userId", required = true) String userId,
                                           @RequestParam(value = "isRelate") Boolean isRelate){
        return BeanCopierUtils.copy(userRoleRelationService.findUserRole(
                userId,
                isRelate), RoleVO.class);
    }


}
