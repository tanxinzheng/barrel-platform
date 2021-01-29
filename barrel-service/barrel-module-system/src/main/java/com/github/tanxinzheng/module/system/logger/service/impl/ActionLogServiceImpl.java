package com.github.tanxinzheng.module.system.logger.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.dictionary.domain.entity.DictionaryDO;
import com.github.tanxinzheng.module.system.logger.domain.dto.ActionLogDTO;
import com.github.tanxinzheng.module.system.logger.domain.entity.ActionLogDO;
import com.github.tanxinzheng.module.system.logger.mapper.ActionLogMapper;
import com.github.tanxinzheng.module.system.logger.service.ActionLogService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-7 10:42:23
 */
@Slf4j
@Service
public class ActionLogServiceImpl extends ServiceImpl<ActionLogMapper, ActionLogDO> implements ActionLogService {

    @Resource
    ActionLogMapper actionLogMapper;

    /**
     * 新增操作日志
     *
     * @param actionLogDTO
     * @return ActionLogResponse
     */
    @Transactional
    @Override
    public ActionLogDTO createActionLog(ActionLogDTO actionLogDTO) {
        AssertValid.notNull(actionLogDTO, "actionLogDTO参数不能为空");
        ActionLogDO actionLog = BeanCopierUtils.copy(actionLogDTO, ActionLogDO.class);
        boolean isOk = save(actionLog);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(actionLog, ActionLogDTO.class);
    }


    /**
     * 更新操作日志
     *
     * @param actionLogDTO
     * @return ActionLogResponse
     */
    @Transactional
    @Override
    public boolean updateActionLog(ActionLogDTO actionLogDTO) {
        AssertValid.notNull(actionLogDTO, "actionLogDTO不能为空");
        ActionLogDO actionLogDO = BeanCopierUtils.copy(actionLogDTO, ActionLogDO.class);
        return updateById(actionLogDO);
    }

    /**
     * 批量新增操作日志
     * @param actionLogs
     * @return
     */
    @Transactional
    @Override
    public List<ActionLogDTO> createActionLogs(List<ActionLogDTO> actionLogs) {
        AssertValid.notEmpty(actionLogs, "actionLogs参数不能为空");
        List<ActionLogDO> actionLogDOList = BeanCopierUtils.copy(actionLogs, ActionLogDO.class);
        boolean isOK = saveBatch(actionLogDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = actionLogDOList.stream().map(ActionLogDO::getId).collect(Collectors.toList());
        List<ActionLogDO> data = actionLogMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, ActionLogDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return ActionLogResponse
     */
    @Override
    public ActionLogDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        ActionLogDO actionLog = getById(id);
        return BeanCopierUtils.copy(actionLog, ActionLogDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<ActionLogDTO>
    */
    @Override
    public List<ActionLogDTO> findList(QueryWrapper<ActionLogDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), ActionLogDTO.class);
    }
    /**
     * 查询操作日志领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<ActionLogDTO> findPage(IPage<ActionLogDO> page, QueryWrapper<ActionLogDO> queryWrapper) {
        queryWrapper.orderByDesc("action_date");
        IPage<ActionLogDO> data = (Page<ActionLogDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, ActionLogDTO.class);
    }

    /**
     * 批量删除操作日志
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deleteActionLog(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除操作日志
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteActionLog(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }
}
