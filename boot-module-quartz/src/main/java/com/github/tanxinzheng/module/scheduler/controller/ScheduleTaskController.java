package com.github.tanxinzheng.module.scheduler.controller;

import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.module.scheduler.QuartzManager;
import com.github.tanxinzheng.module.scheduler.model.ScheduleTaskModel;
import com.github.tanxinzheng.module.scheduler.model.ScheduleTaskQuery;
import com.github.tanxinzheng.module.scheduler.service.ScheduleTaskService;
import io.swagger.annotations.ApiOperation;
import com.github.tanxinzheng.framework.logger.ActionLog;
import com.github.pagehelper.Page;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.MessageFormat;

/**
 * Created by tanxinzheng on 17/8/9.
 */
@RestController
@RequestMapping(value = "/schedule/task")
public class ScheduleTaskController {


    @Autowired
    ScheduleTaskService scheduleTaskService;
    
    @Autowired
    QuartzManager quartzManager;

    /**
     * 新增定时任务
     * @param scheduleTaskModel
     * @return
     */
    @ApiOperation(value = "新增定时任务")
    @ActionLog(actionName = "新增定时任务")
    @RequestMapping(method = RequestMethod.POST)
    public ScheduleTaskModel addScheduleTask(@RequestBody @Valid ScheduleTaskModel scheduleTaskModel) {
        try {
            quartzManager.addJob(
                    scheduleTaskModel.getJobName(),
                    scheduleTaskModel.getJobGroup(),
                    scheduleTaskModel.getTriggerName(),
                    scheduleTaskModel.getTriggerGroup(),
                    Class.forName(scheduleTaskModel.getJobClassName()),
                    scheduleTaskModel.getCronExpression(),
                    scheduleTaskModel.getDescription());
            return scheduleTaskModel;
        } catch (ClassNotFoundException e) {
            throw new BusinessException(MessageFormat.format("未找到对应的类，类名：{0}", scheduleTaskModel.getJobClassName()));
        }
    }

    /**
     * 查询定时任务
     * @param scheduleJobQuery
     * @return
     */
    @ApiOperation(value = "查询定时任务")
    @ActionLog(actionName = "查询定时任务")
    @RequestMapping(method = RequestMethod.GET)
    public Page<ScheduleTaskModel> getScheduleTaskList(ScheduleTaskQuery scheduleJobQuery) {
        return scheduleTaskService.getScheduleTaskPages(scheduleJobQuery);
    }

    /**
     * 修改定时任务
     * @param jobName
     * @return
     */
    @ApiOperation(value = "修改定时任务")
    @ActionLog(actionName = "修改定时任务")
    @RequestMapping(value = "/{jobName}", method = RequestMethod.PUT)
    public void updateScheduleTask(@PathVariable(value = "jobName") String jobName,
                                   @RequestParam(value = "action") Integer action,
                                   @RequestParam(value = "triggerState", required = false) String triggerState,
                                   @RequestParam(value = "cronExpression", required = false) String cronExpression) {
        switch (action){
            case 1://启动
                quartzManager.resumeJob(jobName);
                break;
            case 2://暂停
                quartzManager.pauseJob(jobName);
                break;
            case 4://立即执行
                quartzManager.triggerJob(jobName);
                break;
            case 5://更新时间
                quartzManager.updateCronExpress(jobName, cronExpression);
                break;
            case 9://删除
                quartzManager.deleteJob(jobName);
                break;
        }
    }

    /**
     * 删除定时任务
     * @param jobName
     * @return
     */
    @ApiOperation(value = "删除定时任务")
    @ActionLog(actionName = "删除定时任务")
    @RequestMapping(value = "/{jobName}", method = RequestMethod.DELETE)
    public void deleteScheduleTask(@PathVariable(value = "jobName") String jobName){
        quartzManager.deleteJob(jobName);
    }


}
