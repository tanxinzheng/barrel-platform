package com.github.tanxinzheng.module.scheduler.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by tanxinzheng on 17/8/9.
 */
@Data
public class ScheduleTaskModel implements Serializable {

    private String jobName;
    private String jobClassName;
    private String jobGroup;
    private String description;
    @NotNull(message = "triggerGroup不能为空")
    private String triggerGroup;
    @NotNull(message = "triggerName不能为空")
    private String triggerName;
    private String startTime;
    private String endTime;
    private TriggerState triggerState;
    private String prevFireTime;
    private String nextFireTime;
    private String cronExpression;

}
