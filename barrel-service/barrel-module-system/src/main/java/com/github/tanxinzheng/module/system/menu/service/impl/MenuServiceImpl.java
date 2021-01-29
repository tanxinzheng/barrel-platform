package com.github.tanxinzheng.module.system.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.google.common.collect.Lists;
import com.github.tanxinzheng.module.system.menu.domain.dto.MenuDTO;
import com.github.tanxinzheng.module.system.menu.domain.entity.MenuDO;
import com.github.tanxinzheng.module.system.menu.mapper.MenuMapper;
import com.github.tanxinzheng.module.system.menu.service.MenuService;
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
 * @Date   2021-1-28 11:48:38
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuDO> implements MenuService {

    @Resource
    MenuMapper menuMapper;

    /**
     * 新增菜单
     *
     * @param menuDTO
     * @return MenuResponse
     */
    @Transactional
    @Override
    public MenuDTO createMenu(MenuDTO menuDTO) {
        AssertValid.notNull(menuDTO, "menuDTO参数不能为空");
        MenuDO menu = BeanCopierUtils.copy(menuDTO, MenuDO.class);

        boolean isOk = save(menu);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(menu, MenuDTO.class);
    }


    /**
     * 更新菜单
     *
     * @param menuDTO
     * @return MenuResponse
     */
    @Transactional
    @Override
    public boolean updateMenu(MenuDTO menuDTO) {
        AssertValid.notNull(menuDTO, "menuDTO不能为空");
        MenuDO menuDO = BeanCopierUtils.copy(menuDTO, MenuDO.class);
        return updateById(menuDO);
    }

    /**
     * 批量新增菜单
     * @param menus
     * @return
     */
    @Transactional
    @Override
    public List<MenuDTO> createMenus(List<MenuDTO> menus) {
        AssertValid.notEmpty(menus, "menus参数不能为空");
        List<MenuDO> menuDOList = BeanCopierUtils.copy(menus, MenuDO.class);
        boolean isOK = saveBatch(menuDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = menuDOList.stream().map(MenuDO::getId).collect(Collectors.toList());
        List<MenuDO> data = menuMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, MenuDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return MenuResponse
     */
    @Override
    public MenuDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        MenuDO menu = getById(id);
        return BeanCopierUtils.copy(menu, MenuDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<MenuDTO>
    */
    @Override
    public List<MenuDTO> findList(QueryWrapper<MenuDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), MenuDTO.class);
    }
    /**
     * 查询菜单领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<MenuDTO> findPage(IPage<MenuDO> page, QueryWrapper<MenuDO> queryWrapper) {
        IPage<MenuDO> data = (Page<MenuDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, MenuDTO.class);
    }

    /**
     * 批量删除菜单
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deleteMenu(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除菜单
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteMenu(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }
}
