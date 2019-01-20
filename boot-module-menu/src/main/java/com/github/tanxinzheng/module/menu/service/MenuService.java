package com.github.tanxinzheng.module.menu.service;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.model.TreeModel;
import com.github.tanxinzheng.module.menu.model.MenuQuery;
import com.github.tanxinzheng.module.menu.model.MenuModel;
import com.github.tanxinzheng.module.menu.model.Menu;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2019-1-11 22:32:46
 * @version 1.0.0
 */
public interface MenuService {

    /**
     * 新增菜单配置
     * @param  menuModel   新增菜单配置对象参数
     * @return  MenuModel    菜单配置领域对象
     */
    public MenuModel createMenu(MenuModel menuModel);

    /**
     * 新增菜单配置实体对象
     * @param   menu 新增菜单配置实体对象参数
     * @return  Menu 菜单配置实体对象
     */
    public Menu createMenu(Menu menu);

    /**
    * 批量新增菜单配置
    * @param menuModels     新增菜单配置对象集合参数
    * @return List<MenuModel>    菜单配置领域对象集合
    */
    List<MenuModel> createMenus(List<MenuModel> menuModels);

    /**
    * 更新菜单配置
    *
    * @param menuModel 更新菜单配置对象参数
    * @param menuQuery 过滤菜单配置对象参数
    */
    public void updateMenu(MenuModel menuModel, MenuQuery menuQuery);

    /**
     * 更新菜单配置
     * @param menuModel    更新菜单配置对象参数
     */
    public void updateMenu(MenuModel menuModel);

    /**
     * 更新菜单配置实体对象
     * @param   menu 新增菜单配置实体对象参数
     * @return  Menu 菜单配置实体对象
     */
    public void updateMenu(Menu menu);

    /**
     * 批量删除菜单配置
     * @param ids   主键数组
     */
    public void deleteMenu(String[] ids);

    /**
     * 删除菜单配置
     * @param id   主键
     */
    public void deleteMenu(String id);

    /**
     * 查询菜单配置领域分页对象（带参数条件）
     * @param menuQuery 查询参数
     * @return Page<MenuModel>   菜单配置参数对象
     */
    public Page<MenuModel> getMenuModelPage(MenuQuery menuQuery);

    /**
     * 查询菜单配置领域集合对象（带参数条件）
     * @param menuQuery 查询参数对象
     * @return List<MenuModel> 菜单配置领域集合对象
     */
    public List<MenuModel> getMenuModelList(MenuQuery menuQuery);

    /**
     * 查询菜单配置实体对象
     * @param id 主键
     * @return Menu 菜单配置实体对象
     */
    public Menu getOneMenu(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return MenuModel 菜单配置领域对象
     */
    public MenuModel getOneMenuModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param menuQuery 菜单配置查询参数对象
     * @return MenuModel 菜单配置领域对象
     */
    public MenuModel getOneMenuModel(MenuQuery menuQuery);

    /**
     * 查询树形菜单
     * @param menuQuery
     * @return
     */
    public List<TreeModel> getTreeMenu(MenuQuery menuQuery);
}
