package com.github.tanxinzheng.test.controller;

import com.github.tanxinzheng.framework.web.annotation.LoginUser;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/permission")
public class PermissionTestController {

    /**
     * 查询当前用户简要信息
     * @param loginUser
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询当前用户资料信息")
    public CurrentLoginUser accountSetting(@LoginUser CurrentLoginUser loginUser){
        return loginUser;
    }
}
