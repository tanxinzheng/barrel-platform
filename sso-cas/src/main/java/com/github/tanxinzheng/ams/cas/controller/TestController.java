package com.github.tanxinzheng.ams.cas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanxinzheng on 2018/8/19.
 */
@RestController
public class TestController {


    @GetMapping(value = "/test")
    public String test(){
        return "test";
    }
}
