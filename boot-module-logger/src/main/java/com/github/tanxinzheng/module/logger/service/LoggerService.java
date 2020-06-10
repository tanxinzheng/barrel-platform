package com.github.tanxinzheng.module.logger.service;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.mybatis.page.PageInterceptor;
import com.github.tanxinzheng.module.logger.model.LogModel;
import com.github.tanxinzheng.module.logger.mapper.ActionLogMapper;
import com.github.tanxinzheng.module.logger.model.ActionLogQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Jeng on 16/6/13.
 */
@Service
public class LoggerService {

    @Resource
    ActionLogMapper actionLogMapper;

    public void setLogInfo(LogModel logInfo) {
        actionLogMapper.insertActionLog(logInfo);
    }

    /**
     * 查询操作记录
     * @param actionLogQuery
     * @return
     */
    public Page<LogModel> getActionLogPage(ActionLogQuery actionLogQuery){
        PageInterceptor.startPage(actionLogQuery);
        actionLogMapper.getActionLogs(actionLogQuery);
        return PageInterceptor.endPage();
    }

}
