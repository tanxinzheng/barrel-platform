package com.github.tanxinzheng.module.scheduler.mapper;

import com.github.tanxinzheng.module.scheduler.model.ScheduleTaskQuery;
import com.github.tanxinzheng.module.scheduler.model.ScheduleTaskModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
 * @version 1.0.0
 */
@Mapper
public interface ScheduleTaskMapper {


    List<ScheduleTaskModel> selectModel(ScheduleTaskQuery scheduleJobQuery);

    void updateCronExpression(ScheduleTaskModel scheduleTaskModel);
}
