package com.github.tanxinzheng.module.logger.mapper;

import com.github.tanxinzheng.module.logger.LogModel;
import com.github.tanxinzheng.module.logger.model.ActionLogQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Jeng on 16/3/20.
 */
@Mapper
public interface ActionLogMapper {

    void insertActionLog(LogModel logModel);

    List<LogModel> getActionLogs(ActionLogQuery actionLogQuery);
}
