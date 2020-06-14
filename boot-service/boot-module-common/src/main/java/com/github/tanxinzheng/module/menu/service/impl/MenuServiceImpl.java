package com.github.tanxinzheng.module.menu.service.impl;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.model.TreeNode;
import com.github.tanxinzheng.framework.mybatis.page.PageInterceptor;
import com.github.tanxinzheng.framework.utils.TreeUtils;
import com.github.tanxinzheng.module.menu.mapper.MenuMapper;
import com.github.tanxinzheng.module.menu.model.Menu;
import com.github.tanxinzheng.module.menu.model.MenuModel;
import com.github.tanxinzheng.module.menu.model.MenuQuery;
import com.github.tanxinzheng.module.menu.service.MenuService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2019-1-11 22:32:46
 * @version 1.0.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    /**
     * 新增菜单配置
     *
     * @param menuModel 新增菜单配置对象参数
     * @return MenuModel    菜单配置领域对象
     */
    @Override
    @Transactional
    public MenuModel createMenu(MenuModel menuModel) {
        Menu menu = createMenu(menuModel.getEntity());
        if(menu != null){
            return getOneMenuModel(menu.getId());
        }
        return null;
    }

    /**
     * 新增菜单配置实体对象
     *
     * @param menu 新增菜单配置实体对象参数
     * @return Menu 菜单配置实体对象
     */
    @Override
    @Transactional
    public Menu createMenu(Menu menu) {
        menuMapper.insertSelective(menu);
        return menu;
    }

    /**
    * 批量新增菜单配置
    *
    * @param menuModels 新增菜单配置对象集合参数
    * @return List<MenuModel>    菜单配置领域对象集合
    */
    @Override
    @Transactional
    public List<MenuModel> createMenus(List<MenuModel> menuModels) {
        List<MenuModel> menuModelList = null;
        for (MenuModel menuModel : menuModels) {
            menuModel = createMenu(menuModel);
            if(menuModel != null){
                if(menuModelList == null){
                    menuModelList = new ArrayList<>();
                }
                menuModelList.add(menuModel);
            }
        }
        return menuModelList;
    }

    /**
    * 更新菜单配置
    *
    * @param menuModel 更新菜单配置对象参数
    * @param menuQuery 过滤菜单配置对象参数
    */
    @Override
    @Transactional
    public void updateMenu(MenuModel menuModel, MenuQuery menuQuery) {
        menuMapper.updateSelectiveByQuery(menuModel.getEntity(), menuQuery);
    }

    /**
     * 更新菜单配置
     *
     * @param menuModel 更新菜单配置对象参数
     */
    @Override
    @Transactional
    public void updateMenu(MenuModel menuModel) {
        updateMenu(menuModel.getEntity());
    }

    /**
     * 更新菜单配置实体对象
     *
     * @param menu 新增菜单配置实体对象参数
     * @return Menu 菜单配置实体对象
     */
    @Override
    @Transactional
    public void updateMenu(Menu menu) {
        menuMapper.updateSelective(menu);
    }

    /**
     * 删除菜单配置
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteMenu(String[] ids) {
        menuMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除菜单配置
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteMenu(String id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询菜单配置领域分页对象（带参数条件）
     *
     * @param menuQuery 查询参数
     * @return Page<MenuModel>   菜单配置参数对象
     */
    @Override
    public Page<MenuModel> getMenuModelPage(MenuQuery menuQuery) {
        PageInterceptor.startPage(menuQuery);
        menuMapper.selectModel(menuQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询菜单配置领域集合对象（带参数条件）
     *
     * @param menuQuery 查询参数对象
     * @return List<MenuModel> 菜单配置领域集合对象
     */
    @Override
    public List<MenuModel> getMenuModelList(MenuQuery menuQuery) {
        return menuMapper.selectModel(menuQuery);
    }

    /**
     * 查询菜单配置实体对象
     *
     * @param id 主键
     * @return Menu 菜单配置实体对象
     */
    @Override
    public Menu getOneMenu(String id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return MenuModel 菜单配置领域对象
     */
    @Override
    public MenuModel getOneMenuModel(String id) {
        return menuMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param menuQuery 菜单配置查询参数对象
     * @return MenuModel 菜单配置领域对象
     */
    @Override
    public MenuModel getOneMenuModel(MenuQuery menuQuery) {
        List<MenuModel> menuModelList = menuMapper.selectModel(menuQuery);
        if(CollectionUtils.isEmpty(menuModelList)){
            return null;
        }
        if(menuModelList.size() > 1){
            throw new BusinessException();
        }
        return menuModelList.get(0);
    }

    @Override
    public List<TreeNode> getTreeMenu(MenuQuery menuQuery) {
        List<MenuModel> menuModelList = menuMapper.selectModel(menuQuery);
        if(CollectionUtils.isEmpty(menuModelList)){
            return null;
        }
        List<TreeNode> treeNodeList = Lists.newArrayList();
        menuModelList.stream().forEach(menuModel -> {
            TreeNode<MenuModel> treeNode = new TreeNode(menuModel.getId(), menuModel.getTitle(), menuModel.getParentId());
            treeNode.setValue(menuModel);
            treeNodeList.add(treeNode);
        });
        return TreeUtils.buildByRecursive(treeNodeList);
    }
}
