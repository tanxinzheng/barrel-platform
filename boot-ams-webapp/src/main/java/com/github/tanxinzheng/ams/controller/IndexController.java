package com.github.tanxinzheng.ams.controller;

import com.github.tanxinzheng.ams.module.appInfo.model.AppInfoModel;
import com.github.tanxinzheng.ams.module.appInfo.service.AppInfoService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by tanxinzheng on 2018/9/10.
 */
@Controller
public class IndexController {

    @Autowired
    AppInfoService appInfoService;


    @RequestMapping("/")
    public String detail2(Model model) {
        List<AppInfoModel> appInfoModelList = Lists.newArrayList();

//        AppInfoModel appInfoModel = new AppInfoModel();
//        appInfoModel.setAppName("用户管理系统");
//        appInfoModel.setDescription("用于管理所有用户信息，可查询用户信息，添加用户，用户授权");
//        appInfoModel.setAppCode("UMS");
//        appInfoModel.setUrl("http://localhost:12018");
//        appInfoModelList.add(appInfoModel);
//
//        AppInfoModel appInfoModel2 = new AppInfoModel();
//        appInfoModel2.setName("统一认证系统");
//        appInfoModel2.setDescription("统一用户认证中心，用于单点登录");
//        appInfoModel2.setCode("CAS");
//        appInfoModel2.setUrl("http://localhost:12018");
//        appInfoModelList.add(appInfoModel2);
//
//        AppInfoModel appInfoModel3 = new AppInfoModel();
//        appInfoModel3.setName("应用管理中心");
//        appInfoModel3.setDescription("用于管理所有应用信息，可查询应用信息，注册应用");
//        appInfoModel3.setCode("AMC");
//        appInfoModel3.setUrl("http://localhost:12018");
//        appInfoModelList.add(appInfoModel3);
//
//        AppInfoModel appInfoModel4 = new AppInfoModel();
//        appInfoModel4.setName("配置管理中心");
//        appInfoModel4.setDescription("用于管理应用配置信息");
//        appInfoModel4.setCode("PMC");
//        appInfoModel4.setUrl("http://localhost:12018");
//        appInfoModelList.add(appInfoModel4);

        model.addAttribute("appList", appInfoModelList);
        return "home";
    }

}
