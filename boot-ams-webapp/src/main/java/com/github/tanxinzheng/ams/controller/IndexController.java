package com.github.tanxinzheng.ams.controller;

import com.github.tanxinzheng.ams.module.appInfo.model.AppInfoModel;
import com.github.tanxinzheng.ams.module.appInfo.model.AppInfoQuery;
import com.github.tanxinzheng.ams.module.appInfo.service.AppInfoService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by tanxinzheng on 2018/9/10.
 */
@Controller
public class IndexController {

    @Autowired
    AppInfoService appInfoService;


    @RequestMapping("/")
    public String detail2(@RequestParam(value = "keyword", required = false) String keyword,
                          Model model) {
        AppInfoQuery appInfoQuery = new AppInfoQuery();
        appInfoQuery.setKeyword(keyword);
        List<AppInfoModel> appInfoModelList = appInfoService.getAppInfoModelList(appInfoQuery);
        model.addAttribute("keyword", keyword);
        model.addAttribute("appList", appInfoModelList);
        return "home";
    }

}
