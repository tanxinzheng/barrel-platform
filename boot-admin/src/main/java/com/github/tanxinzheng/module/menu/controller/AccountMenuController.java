package com.github.tanxinzheng.module.menu.controller;

import com.github.tanxinzheng.framework.logger.ActionLog;
import com.github.tanxinzheng.framework.model.TreeModel;
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
@RestController
@Api(tags = "当前用户菜单接口")
@RequestMapping(value = "/account/menu")
public class AccountMenuController {

    @Autowired
    MenuService menuService;
    /**
     * 查询树形结构菜单
     * @return
     */
    @ApiOperation(value = "查询树形菜单")
    @ActionLog(actionName = "查询树形菜单信息")
    @GetMapping
    public List<TreeModel> getAccountMenu(MenuQuery query){
        // TODO 添加当前登录人ID过滤条件
        return menuService.getTreeMenu(query);
    }

}
