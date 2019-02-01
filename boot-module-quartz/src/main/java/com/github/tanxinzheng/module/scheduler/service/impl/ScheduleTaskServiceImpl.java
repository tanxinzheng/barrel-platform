package com.github.tanxinzheng.module.scheduler.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.tanxinzheng.module.scheduler.mapper.ScheduleTaskMapper;
import com.github.tanxinzheng.module.scheduler.model.ScheduleTaskModel;
import com.github.tanxinzheng.module.scheduler.model.ScheduleTaskQuery;
import com.github.tanxinzheng.module.scheduler.service.ScheduleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tanxinzheng on 17/8/9.
 */
@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {

    @Autowired
    ScheduleTaskMapper scheduleTaskMapper;

    /**
     * 查询调度任务
     * @param scheduleTaskQuery
     * @return
     */
    @Override
    public Page<ScheduleTaskModel> getScheduleTaskPages(ScheduleTaskQuery scheduleTaskQuery) {
        Page<ScheduleTaskModel> page = PageHelper.startPage(scheduleTaskQuery);
        scheduleTaskMapper.selectModel(scheduleTaskQuery);
        return page;
    }

    @Override
    public void updateScheduleTask(ScheduleTaskModel scheduleTaskModel) {

    }
}
