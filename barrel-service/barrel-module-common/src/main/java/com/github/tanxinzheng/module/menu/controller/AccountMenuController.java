package com.github.tanxinzheng.module.menu.controller;

import com.github.tanxinzheng.framework.model.TreeNode;
import com.github.tanxinzheng.module.menu.model.MenuQuery;
import com.github.tanxinzheng.module.menu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2019-1-11 22:32:46
 * @version 1.0.0
 */
@Api(tags = {"个人中心"})
@RestController
@RequestMapping(value = "/account/menu")
public class AccountMenuController {

    @Autowired
    MenuService menuService;
    /**
     * 查询树形结构菜单
     * @return
     */
    @ApiOperation(value = "我的菜单")
    @GetMapping
    public List<TreeNode> getAccountMenu(MenuQuery query){
        // TODO 添加当前登录人ID过滤条件
        return menuService.getTreeMenu(query);
    }

}
