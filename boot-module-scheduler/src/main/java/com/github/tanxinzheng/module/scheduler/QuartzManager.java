package com.github.tanxinzheng.module.scheduler;

import com.github.tanxinzheng.framework.exception.BusinessException;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by tanxinzheng on 17/8/8.
 */
@Component
public class QuartzManager {

    private static Logger logger = LoggerFactory.getLogger(QuartzManager.class);

    private static final String  JOB_GROUP_NAME = "DEFAULT";
    private static final String  TRIGGER_GROUP_NAME = "DEFAULT";

    @Autowired(required = false)
    SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 添加定时任务
     * 一个job可以有多个触发器，如一个job包含多个触发操作组成一个多事件的任务
     */
    public void addJob(String jobName,
                       String jobGroupName,
                       String triggerName,
                       String triggerGroupName,
                       Class cls,
                       String cron,
                       String description) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.start();
            JobKey jobKey = new JobKey(jobName, jobGroupName);
            if(scheduler.checkExists(jobKey)){
                throw new SchedulerException("The jobName and jobGroup already exists.");
            }
            TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
            if(scheduler.checkExists(triggerKey)){
                throw new SchedulerException("The triggerName and triggerGroupName already exists.");
            }

            JobDetail jobDetail = newJob(cls)
                    .withIdentity(jobName, jobGroupName)
                    .withDescription(description)
                    .build();
            CronTrigger cronTrigger = newTrigger()
                    .withIdentity(triggerName, triggerGroupName)
                    .withSchedule(cronSchedule(cron)).build();
//            CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
//            cronTriggerFactoryBean.setCronExpression(cron);
//            cronTriggerFactoryBean.setDescription(description);
//            cronTriggerFactoryBean.setJobDetail(jobDetail);
//            cronTriggerFactoryBean.setTimeZone(TimeZone.getDefault());
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (SchedulerException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 删除定时任务
     */
    public boolean deleteJob(String jobName,String groupName){
        boolean result = false;
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = new JobKey(jobName,groupName);
            if(scheduler.checkExists(jobKey)){
                result = scheduler.deleteJob(jobKey);
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 删除Job
     * @param jobName
     */
    public  void deleteJob(String jobName){
        deleteJob(jobName,JOB_GROUP_NAME);
    }

    /**
     * 停止任务
     */
    public void pauseJob(String jobName,String groupName){
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = new JobKey(jobName,groupName);
            if(scheduler.checkExists(jobKey)){
                scheduler.pauseJob(jobKey);
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 停止任务
     * @param jobName
     */
    public void pauseJob(String jobName){
        pauseJob(jobName,JOB_GROUP_NAME);
    }

    /**
     * 再次启动Job
     * @param jobName
     * @param groupName
     */
    public  void resumeJob(String jobName,String groupName){
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = new JobKey(jobName,groupName);
            if(scheduler.checkExists(jobKey)){
                scheduler.resumeJob(jobKey);
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 重启Job
     * @param jobName
     */
    public void resumeJob(String jobName){
        resumeJob(jobName,JOB_GROUP_NAME);
    }

    /**
     * 立即触发Job
     * @param jobName
     * @param jobGroupName
     */
    public void triggerJob(String jobName, String jobGroupName){
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = new JobKey(jobName, jobGroupName);
            if(scheduler.checkExists(jobKey)){
                scheduler.triggerJob(jobKey);
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 立即触发Job
     * @param jobName
     */
    public void triggerJob(String jobName){
        triggerJob(jobName, JOB_GROUP_NAME);
    }

    /**
     * 更新cronExpress
     * @param jobName
     * @param jobGroupName
     * @param cronExpression
     */
    public void updateCronExpress(String jobName, String jobGroupName, String cronExpression){
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();
            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            throw new BusinessException("修改Cron表达式失败，错误信息：" + e.getMessage());
        }
    }

    /**
     * 更新cronExpress
     * @param jobName
     * @param cronExpression
     */
    public void updateCronExpress(String jobName, String cronExpression){
        updateCronExpress(jobName, JOB_GROUP_NAME, cronExpression);
    }
}
