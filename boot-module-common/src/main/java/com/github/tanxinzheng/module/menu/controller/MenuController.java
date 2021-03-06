package com.github.tanxinzheng.module.menu.controller;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.model.TreeModel;
import com.github.tanxinzheng.module.menu.model.MenuModel;
import com.github.tanxinzheng.module.menu.model.MenuQuery;
import com.github.tanxinzheng.module.menu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2019-1-11 22:32:46
 * @version 1.0.0
 */
@RestController
@Api(tags = "菜单管理")
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    /**
     * 菜单配置列表
     * @param   menuQuery    菜单配置查询参数对象
     * @return  Page<MenuModel> 菜单配置领域分页对象
     */
    @ApiOperation(value = "查询菜单配置列表")
    @GetMapping
    public Page<MenuModel> getMenuList(MenuQuery menuQuery){
        return menuService.getMenuModelPage(menuQuery);
    }

    /**
     * 查询单个菜单配置
     * @param   id  主键
     * @return  MenuModel   菜单配置领域对象
     */
    @ApiOperation(value = "查询菜单配置")
    @GetMapping(value = "/{id}")
    public MenuModel getMenuById(@PathVariable(value = "id") String id){
        return menuService.getOneMenuModel(id);
    }

    /**
     * 新增菜单配置
     * @param   menuModel  新增对象参数
     * @return  MenuModel   菜单配置领域对象
     */
    @ApiOperation(value = "新增菜单配置")
    @PostMapping
    public MenuModel createMenu(@RequestBody @Valid MenuModel menuModel) {
        return menuService.createMenu(menuModel);
    }

    /**
     * 更新菜单配置
     * @param id    主键
     * @param menuModel  更新对象参数
     * @return  MenuModel   菜单配置领域对象
     */
    @ApiOperation(value = "更新菜单配置")
    @PutMapping(value = "/{id}")
    public MenuModel updateMenu(@PathVariable(value = "id") String id,
                           @RequestBody @Valid MenuModel menuModel){
        if(StringUtils.isNotBlank(id)){
            menuModel.setId(id);
        }
        menuService.updateMenu(menuModel);
        return menuService.getOneMenuModel(id);
    }

    /**
     *  删除菜单配置
     * @param id    主键
     */
    @ApiOperation(value = "删除单个菜单配置")
    @DeleteMapping(value = "/{id}")
    public void deleteMenu(@PathVariable(value = "id") String id){
        menuService.deleteMenu(id);
    }

    /**
     *  删除菜单配置
     * @param menuQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除菜单配置")
    @DeleteMapping
    public void deleteMenus(MenuQuery menuQuery){
        menuService.deleteMenu(menuQuery.getIds());
    }


    /**
     * 查询树形结构菜单
     * @return
     */
    @ApiOperation(value = "查询树形菜单")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public List<TreeModel> getTreeMenu(MenuQuery query){
        return menuService.getTreeMenu(query);
    }

}
