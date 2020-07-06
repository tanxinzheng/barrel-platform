package com.github.tanxinzheng.module.system.logger.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.module.system.logger.domain.dto.ActionLogDTO;
import com.github.tanxinzheng.module.system.logger.domain.entity.ActionLogDO;
import com.github.tanxinzheng.module.system.logger.domain.vo.ActionLogVO;
import com.github.tanxinzheng.module.system.logger.service.ActionLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-7 0:00:43
 */
@Slf4j
@Api(tags = "操作日志接口")
@RestController
@RequestMapping(value = "/action-logs")
public class ActionLogController {

    @Resource
    ActionLogService actionLogService;

    /**
     * 分页查询操作日志集合
     * @param queryParams
     * @return
     */
    @ApiOperation(value = "分页查询操作日志")
    @GetMapping
    public IPage<ActionLogVO> findPage(QueryParams<ActionLogDO> queryParams){
        IPage<ActionLogDTO> page = actionLogService.findPage(queryParams.getPage(), queryParams.getQueryWrapper());
        return BeanCopierUtils.copy(page, ActionLogVO.class);
    }

    /**
     * 主键查询操作日志
     * @param   id  主键
     * @return  ActionLogResponse   操作日志领域对象
     */
    @ApiOperation(value = "主键查询操作日志")
    @GetMapping(value = "/{id}")
    public ActionLogVO findById(@PathVariable(value = "id") String id){
        ActionLogDTO actionLogDTO = actionLogService.findById(id);
        return BeanCopierUtils.copy(actionLogDTO, ActionLogVO.class);
    }


}
