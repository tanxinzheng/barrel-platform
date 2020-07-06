package com.github.tanxinzheng.module.system.authorization.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.system.authorization.domain.dto.UserDTO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.UserDO;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
public interface UserService {

    /**
     * 新增用户
     * @param  userCreate
     * @return UserDTO
     */
    public UserDTO createUser(UserDTO userCreate);

    /**
     * 批量新增用户
     * @param user
     * @return List<User>
     */
    List<UserDTO> createUsers(List<UserDTO> user);

    /**
     * 更新用户
     * @param   userUpdate
     * @return  boolean
     */
    public boolean updateUser(UserDTO userUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  UserDTO
     */
    public UserDTO findById(String id);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<UserDTO>
    */
    public List<UserDTO> findList(QueryWrapper<UserDO> queryWrapper);

    /**
     * 查询用户领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    public IPage<UserDTO> findPage(IPage<UserDO> page, QueryWrapper<UserDO> queryWrapper);

    /**
     * 批量删除用户
     * @param  ids
     * @return boolean
     */
    public boolean deleteUser(List<String> ids);

    /**
     * 删除用户
     * @param  id
     * @return boolean
     */
    public boolean deleteUser(String id);

}
