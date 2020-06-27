package com.github.tanxinzheng.module.authorization.feign;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.auth.feign.IUserClient;
import com.github.tanxinzheng.module.authorization.domain.dto.RoleDTO;
import com.github.tanxinzheng.module.authorization.domain.entity.UserDO;
import com.github.tanxinzheng.module.authorization.mapper.UserMapper;
import com.github.tanxinzheng.module.authorization.service.UserRoleRelationService;
import com.github.tanxinzheng.module.authorization.service.UserService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Date 2020/6/27
 */
@ApiIgnore
@RestController
public class UserClient implements IUserClient {

    @Resource
    UserService userService;
    @Resource
    UserMapper userMapper;
    @Resource
    UserRoleRelationService userRoleRelationService;
    /**
     * 查询用户
     *
     * @param username
     * @return
     */
    @Override
    public Result<AuthUser> getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserDO::getUsername, username)
                .or()
                .eq(UserDO::getEmail, username)
                .or()
                .eq(UserDO::getPhoneNumber, username);
        UserDO userDO = userMapper.selectOne(lambdaQueryWrapper);
        AssertValid.notNull(userDO, "该用户名未注册");
        AssertValid.isTrue(userDO.getDisable(), "该用户已被禁用，若要启用，请联系管理员。");
        AuthUser authUser = BeanCopierUtils.copy(userDO, AuthUser.class);
        return Result.success(authUser);
    }

    /**
     * 查询用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public Result<List<String>> getRoles(String userId) {
        List<RoleDTO> list = userRoleRelationService.findUserRole(userId, true);
        List<String> roles = Lists.newArrayList();
        if(CollectionUtils.isEmpty(list)){
            return Result.success(roles);
        }
        list.forEach(roleDTO -> {
            roles.add(roleDTO.getRoleCode());
        });
        return Result.success(roles);
    }
}
