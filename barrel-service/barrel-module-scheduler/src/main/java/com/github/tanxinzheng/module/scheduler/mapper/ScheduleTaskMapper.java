package com.github.tanxinzheng.module.scheduler.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.scheduler.model.ScheduleTaskModel;
import com.github.tanxinzheng.module.scheduler.model.ScheduleTaskQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
 * @version 1.0.0
 */
@Mapper
public interface ScheduleTaskMapper extends BaseMapper {

    IPage<ScheduleTaskModel> selectScheduleTask(@Param(value = "ew") IPage page,
                                               @Param(value = "query") ScheduleTaskQuery scheduleJobQuery);

    void updateCronExpression(ScheduleTaskModel scheduleTaskModel);
}
