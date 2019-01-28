package com.github.tanxinzheng.module.authorization.service.impl;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.core.model.SelectIndex;
import com.github.tanxinzheng.framework.core.model.SelectOptionModel;
import com.github.tanxinzheng.framework.core.model.SelectOptionQuery;
import com.github.tanxinzheng.framework.core.service.SelectService;
import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.mybatis.page.PageInterceptor;
import com.github.tanxinzheng.framework.utils.PasswordHelper;
import com.github.tanxinzheng.framework.utils.UUIDGenerator;
import com.github.tanxinzheng.framework.web.json.DictionaryIndex;
import com.github.tanxinzheng.module.authorization.mapper.UserMapper;
import com.github.tanxinzheng.module.authorization.model.User;
import com.github.tanxinzheng.module.authorization.model.UserModel;
import com.github.tanxinzheng.module.authorization.model.UserQuery;
import com.github.tanxinzheng.module.authorization.service.UserService;
import com.github.tanxinzheng.module.dictionary.web.AccountInterpreterService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author  tanxinzheng
 * @date    2017-6-16 22:59:53
 * @version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService, AccountInterpreterService, SelectService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    /**
     * 新增数据字典
     *
     * @param userModel 新增数据字典对象参数
     * @return UserModel    数据字典领域对象
     */
    @Override
    @Transactional
    public UserModel createUser(UserModel userModel) {
        String password = UUIDGenerator.getInstance().getUUID(6);
        String salt = UUIDGenerator.getInstance().getUUID(32);
        String realPassword = PasswordHelper.encryptPassword(password, salt);
        userModel.setPassword(realPassword);
        userModel.setSalt(salt);
        User user = createUser(userModel.getEntity());
        if(user != null){
            return getOneUserModel(user.getId());
        }
        return null;
    }

    /**
     * 新增数据字典实体对象
     *
     * @param user 新增数据字典实体对象参数
     * @return User 数据字典实体对象
     */
    @Override
    @Transactional
    public User createUser(User user) {
        user.setCreatedTime(new Date());
        user.setDisable(false);
        userMapper.insertSelective(user);
        return user;
    }

    /**
    * 批量新增数据字典
    *
    * @param userModels 新增数据字典对象集合参数
    * @return List<UserModel>    数据字典领域对象集合
    */
    @Override
    @Transactional
    public List<UserModel> createUsers(List<UserModel> userModels) {
        List<UserModel> userModelList = null;
        for (UserModel userModel : userModels) {
            userModel = createUser(userModel);
            if(userModel != null){
                if(userModelList == null){
                    userModelList = new ArrayList<>();
                }
                userModelList.add(userModel);
            }
        }
        return userModelList;
    }

    /**
    * 更新数据字典
    *
    * @param userModel 更新数据字典对象参数
    * @param userQuery 过滤数据字典对象参数
    */
    @Override
    @Transactional
    public void updateUser(UserModel userModel, UserQuery userQuery) {
        userMapper.updateSelectiveByQuery(userModel.getEntity(), userQuery);
    }

    /**
     * 更新数据字典
     *
     * @param userModel 更新数据字典对象参数
     */
    @Override
    @Transactional
    public void updateUser(UserModel userModel) {
        updateUser(userModel.getEntity());
    }

    /**
     * 更新数据字典实体对象
     *
     * @param user 新增数据字典实体对象参数
     * @return User 数据字典实体对象
     */
    @Override
    @Transactional
    public void updateUser(User user) {
        userMapper.updateSelective(user);
    }

    /**
     * 删除数据字典
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteUser(String[] ids) {
        userMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除数据字典
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteUser(String id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询数据字典领域分页对象（带参数条件）
     *
     * @param userQuery 查询参数
     * @return Page<UserModel>   数据字典参数对象
     */
    @Override
    public Page<UserModel> getUserModelPage(UserQuery userQuery) {
        PageInterceptor.startPage(userQuery);
        userMapper.selectModel(userQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询数据字典领域集合对象（带参数条件）
     *
     * @param userQuery 查询参数对象
     * @return List<UserModel> 数据字典领域集合对象
     */
    @Override
    public List<UserModel> getUserModelList(UserQuery userQuery) {
        return userMapper.selectModel(userQuery);
    }

    /**
     * 查询数据字典实体对象
     *
     * @param id 主键
     * @return User 数据字典实体对象
     */
    @Override
    public User getOneUser(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据username查询用户
     *
     * @param username
     * @return
     */
    @Override
    public UserModel getOneUserModelByUsername(String username) {
        return userMapper.selectModelByUsername(username);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return UserModel 数据字典领域对象
     */
    @Override
    public UserModel getOneUserModel(String id) {
        return userMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param userQuery 数据字典查询参数对象
     * @return UserModel 数据字典领域对象
     */
    @Override
    public UserModel getOneUserModel(UserQuery userQuery) {
        List<UserModel> userModelList = userMapper.selectModel(userQuery);
        if(CollectionUtils.isEmpty(userModelList)){
            return null;
        }
        if(userModelList.size() > 1){
            throw new BusinessException();
        }
        return userModelList.get(0);
    }

    /**
     * 翻译
     *
     * @param dictionaryCode 字典代码
     * @return
     */
    @Cacheable(cacheNames = DictionaryIndex.DICTIONARY_CACHE_NAME_KEY)
    @Override
    public Map<String, Object> translateAccount(String dictionaryCode) {
        UserQuery userQuery = new UserQuery();
        List<UserModel> userList = getUserModelList(userQuery);
        if(CollectionUtils.isNotEmpty(userList)){
            return userList.stream().collect(Collectors.toMap(UserModel::getId, userModel -> userModel));
        }
        return Maps.newHashMap();
    }

    /**
     * 查询option数据
     *
     * @param selectOptionQuery
     * @return
     */
    @Override
    public List<SelectOptionModel> selectOptionModels(SelectOptionQuery selectOptionQuery) {
        UserQuery userQuery = new UserQuery();
        List<UserModel> userModelList = getUserModelList(userQuery);
        if(CollectionUtils.isEmpty(userModelList)){
            return Lists.newArrayList();
        }
        int i = 0;
        List<SelectOptionModel> selectOptionModelList = Lists.newArrayList();
        for (UserModel userModel : userModelList) {
            SelectOptionModel selectOptionModel = new SelectOptionModel(
                    DictionaryIndex.USER_ID,
                    "用户",
                    userModel.getId(),
                    userModel.getNickname(),
                    i++
            );
            selectOptionModelList.add(selectOptionModel);
        }
        return selectOptionModelList;
    }

    /**
     * 获取select index
     *
     * @return
     */
    @Override
    public SelectIndex getSelectIndex() {
        return SelectIndex.USER;
    }

}
