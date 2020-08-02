package com.github.tanxinzheng.module.system.logger.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.system.logger.domain.dto.ActionLogDTO;
import com.github.tanxinzheng.module.system.logger.domain.entity.ActionLogDO;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-7 10:42:23
 */
public interface ActionLogService {

    /**
     * 新增操作日志
     * @param  actionLogCreate
     * @return ActionLogDTO
     */
    public ActionLogDTO createActionLog(ActionLogDTO actionLogCreate);

    /**
     * 批量新增操作日志
     * @param actionLog
     * @return List<ActionLog>
     */
    List<ActionLogDTO> createActionLogs(List<ActionLogDTO> actionLog);

    /**
     * 更新操作日志
     * @param   actionLogUpdate
     * @return  boolean
     */
    public boolean updateActionLog(ActionLogDTO actionLogUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  ActionLogDTO
     */
    public ActionLogDTO findById(String id);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<ActionLogDTO>
    */
    public List<ActionLogDTO> findList(QueryWrapper<ActionLogDO> queryWrapper);

    /**
     * 查询操作日志领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    public IPage<ActionLogDTO> findPage(IPage<ActionLogDO> page, QueryWrapper<ActionLogDO> queryWrapper);

    /**
     * 批量删除操作日志
     * @param  ids
     * @return boolean
     */
    public boolean deleteActionLog(List<String> ids);

    /**
     * 删除操作日志
     * @param  id
     * @return boolean
     */
    public boolean deleteActionLog(String id);

}
