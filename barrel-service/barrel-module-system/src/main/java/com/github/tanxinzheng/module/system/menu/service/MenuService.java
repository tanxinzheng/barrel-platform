package com.github.tanxinzheng.module.system.menu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.tanxinzheng.module.system.menu.domain.dto.MenuDTO;
import com.github.tanxinzheng.module.system.menu.domain.entity.MenuDO;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2021-1-28 11:48:38
 */
public interface MenuService extends IService<MenuDO> {

    /**
     * 新增菜单
     * @param  menuCreate
     * @return MenuDTO
     */
    public MenuDTO createMenu(MenuDTO menuCreate);

    /**
     * 批量新增菜单
     * @param menu
     * @return List<Menu>
     */
    List<MenuDTO> createMenus(List<MenuDTO> menu);

    /**
     * 更新菜单
     * @param   menuUpdate
     * @return  boolean
     */
    public boolean updateMenu(MenuDTO menuUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  MenuDTO
     */
    public MenuDTO findById(String id);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<MenuDTO>
    */
    public List<MenuDTO> findList(QueryWrapper<MenuDO> queryWrapper);

    /**
     * 查询菜单领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    public IPage<MenuDTO> findPage(IPage<MenuDO> page, QueryWrapper<MenuDO> queryWrapper);

    /**
     * 批量删除菜单
     * @param  ids
     * @return boolean
     */
    public boolean deleteMenu(List<String> ids);

    /**
     * 删除菜单
     * @param  id
     * @return boolean
     */
    public boolean deleteMenu(String id);

}
