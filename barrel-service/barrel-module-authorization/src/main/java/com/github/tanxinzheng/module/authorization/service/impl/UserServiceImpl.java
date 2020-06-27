package com.github.tanxinzheng.module.authorization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.authorization.domain.dto.UserDTO;
import com.github.tanxinzheng.module.authorization.domain.entity.UserDO;
import com.github.tanxinzheng.module.authorization.mapper.UserMapper;
import com.github.tanxinzheng.module.authorization.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 新增用户
     *
     * @param userDTO
     * @return UserResponse
     */
    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        AssertValid.notNull(userDTO, "userDTO参数不能为空");
        UserDO user = userDTO.toDO(UserDO.class);
        boolean isOk = save(user);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(user, UserDTO.class);
    }


    /**
     * 更新用户
     *
     * @param userDTO
     * @return UserResponse
     */
    @Transactional
    @Override
    public boolean updateUser(UserDTO userDTO) {
        AssertValid.notNull(userDTO, "userDTO不能为空");
        UserDO userDO = BeanCopierUtils.copy(userDTO, UserDO.class);
        return updateById(userDO);
    }

    /**
     * 批量新增用户
     * @param users
     * @return
     */
    @Transactional
    @Override
    public List<UserDTO> createUsers(List<UserDTO> users) {
        AssertValid.notEmpty(users, "users参数不能为空");
        List<UserDO> userDOList = BeanCopierUtils.copy(users, UserDO.class);
        boolean isOK = saveBatch(userDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = userDOList.stream().map(UserDO::getId).collect(Collectors.toList());
        List<UserDO> data = userMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, UserDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return UserResponse
     */
    @Override
    public UserDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        UserDO user = getById(id);
        return BeanCopierUtils.copy(user, UserDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<UserDTO>
    */
    @Override
    public List<UserDTO> findList(QueryWrapper<UserDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), UserDTO.class);
    }
    /**
     * 查询用户领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<UserDTO> findPage(IPage<UserDO> page, QueryWrapper<UserDO> queryWrapper) {
        IPage<UserDO> data = (Page<UserDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, UserDTO.class);
    }

    /**
     * 批量删除用户
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deleteUser(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除用户
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteUser(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }
}
