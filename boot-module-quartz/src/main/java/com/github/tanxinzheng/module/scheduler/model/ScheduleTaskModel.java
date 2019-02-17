package com.github.tanxinzheng.module.scheduler.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by tanxinzheng on 17/8/9.
 */
@Data
public class ScheduleTaskModel implements Serializable {

    @NotBlank(message = "jobName不能为空")
    private String jobName;
    @NotBlank(message = "jobClassName不能为空")
    private String jobClassName;
    @NotBlank(message = "jobGroup不能为空")
    private String jobGroup;
    @NotBlank(message = "描述不能为空")
    private String description;
    @NotBlank(message = "triggerGroup不能为空")
    private String triggerGroup;
    @NotBlank(message = "triggerName不能为空")
    private String triggerName;
    private String startTime;
    private String endTime;
    private String triggerState;
    private String prevFireTime;
    private String nextFireTime;
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;

}
