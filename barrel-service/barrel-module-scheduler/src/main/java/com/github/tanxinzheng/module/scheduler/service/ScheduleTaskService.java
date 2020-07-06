package com.github.tanxinzheng.module.scheduler.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.scheduler.model.ScheduleTaskModel;
import com.github.tanxinzheng.module.scheduler.model.ScheduleTaskQuery;

/**
 * Created by tanxinzheng on 17/8/9.
 */
public interface ScheduleTaskService {

    /**
     * 查询调度任务
     * @param scheduleJobQuery
     * @return
     */
    IPage<ScheduleTaskModel> getScheduleTaskPages(ScheduleTaskQuery scheduleJobQuery);

    /**
     * 修改定时任务
     * @param scheduleTaskModel
     */
    void updateScheduleTask(ScheduleTaskModel scheduleTaskModel);
}
