package com.github.tanxinzheng.module.logger.controller;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.module.logger.model.LogModel;
import com.github.tanxinzheng.module.logger.model.ActionLogQuery;
import com.github.tanxinzheng.module.logger.service.LoggerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tanxinzheng on 17/8/5.
 */
@Api(tags = {"操作日志"})
@RestController
@RequestMapping(value = "/action_log")
public class ActionLogController {

    @Resource
    LoggerService loggerService;

    /**
     * 查询操作日志
     * @param actionLogQuery
     * @return
     */
    @ApiOperation(value = "查询操作日志")
    @GetMapping
    public Page<LogModel> getActionLog(ActionLogQuery actionLogQuery){
        return loggerService.getActionLogPage(actionLogQuery);
    }
}
