package com.github.tanxinzheng.module.scheduler.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.tanxinzheng.module.scheduler.mapper.ScheduleTaskMapper;
import com.github.tanxinzheng.module.scheduler.model.ScheduleTaskModel;
import com.github.tanxinzheng.module.scheduler.model.ScheduleTaskQuery;
import com.github.tanxinzheng.module.scheduler.service.ScheduleTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by tanxinzheng on 17/8/9.
 */
@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {

    @Resource
    ScheduleTaskMapper scheduleTaskMapper;

    /**
     * 查询调度任务
     * @param scheduleTaskQuery
     * @return
     */
    @Override
    public IPage<ScheduleTaskModel> getScheduleTaskPages(ScheduleTaskQuery scheduleTaskQuery) {
        IPage<ScheduleTaskModel> scheduleTaskModelIpage = new Page<>(scheduleTaskQuery.getPageNum(), scheduleTaskQuery.getPageSize());
        return scheduleTaskMapper.selectScheduleTask(scheduleTaskModelIpage, scheduleTaskQuery);
    }

    @Override
    public void updateScheduleTask(ScheduleTaskModel scheduleTaskModel) {

    }
}
