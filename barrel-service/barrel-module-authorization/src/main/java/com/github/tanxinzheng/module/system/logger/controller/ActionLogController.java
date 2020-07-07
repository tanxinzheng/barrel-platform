package com.github.tanxinzheng.module.system.logger.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.logger.domain.dto.ActionLogDTO;
import com.github.tanxinzheng.module.system.logger.domain.entity.ActionLogDO;
import com.github.tanxinzheng.module.system.logger.domain.vo.ActionLogVO;
import com.github.tanxinzheng.module.system.logger.service.ActionLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-7 10:42:23
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

    /**
     * 新增操作日志
     * @param actionLogDTO
     * @return
     */
    @ApiOperation(value = "新增操作日志")
    @PostMapping
    public ActionLogVO create(@RequestBody @Valid ActionLogDTO actionLogDTO) {
        actionLogDTO = actionLogService.createActionLog(actionLogDTO);
        return BeanCopierUtils.copy(actionLogDTO, ActionLogVO.class);
    }

    /**
     * 更新操作日志
     * @param id    主键
     * @param actionLogDTO  更新对象参数
     * @return  ActionLogResponse   操作日志领域对象
     */
    @ApiOperation(value = "更新操作日志")
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable(value = "id") String id,
                              @RequestBody @Valid ActionLogDTO actionLogDTO){
        if(StringUtils.isNotBlank(id)){
            actionLogDTO.setId(id);
        }
        return actionLogService.updateActionLog(actionLogDTO);
    }

    /**
     *  删除操作日志
     * @param id    主键
     */
    @ApiOperation(value = "删除单个操作日志")
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(value = "id") String id){
        return actionLogService.deleteActionLog(id);
    }

    /**
     *  删除操作日志
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除操作日志")
    @DeleteMapping
    public boolean batchDelete(@RequestBody List<String> ids){
        AssertValid.notEmpty(ids, "数组参数不能为空");
        return actionLogService.deleteActionLog(ids);
    }


}
