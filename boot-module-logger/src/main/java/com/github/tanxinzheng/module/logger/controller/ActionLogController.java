package com.github.tanxinzheng.module.logger.controller;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.module.logger.LogModel;
import com.github.tanxinzheng.module.logger.model.ActionLogQuery;
import com.github.tanxinzheng.module.logger.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanxinzheng on 17/8/5.
 */
@RestController
@RequestMapping(value = "/action_log")
public class ActionLogController {

    @Autowired
    LoggerService loggerService;

    /**
     * 查询操作日志
     * @param actionLogQuery
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<LogModel> getActionLog(ActionLogQuery actionLogQuery){
        return loggerService.getActionLogPage(actionLogQuery);
    }
}
