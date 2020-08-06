package com.github.tanxinzheng.module.account.access.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Api(tags = {"首页跳转"},hidden = true)
@Controller
public class IndexController {

    /**
     * 首页跳转
     * @throws IOException
     */
    @ApiOperation(value = "首页跳转", tags = {"首页"})
    @GetMapping(value = "/")
    public String redirect() {
        return "redirect:/index.html";
    }
}
