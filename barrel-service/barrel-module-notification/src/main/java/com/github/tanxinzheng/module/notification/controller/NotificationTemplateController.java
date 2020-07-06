package com.github.tanxinzheng.module.notification.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.notification.domain.dto.NotificationTemplateDTO;
import com.github.tanxinzheng.module.notification.domain.entity.NotificationTemplateDO;
import com.github.tanxinzheng.module.notification.domain.vo.NotificationTemplateVO;
import com.github.tanxinzheng.module.notification.service.NotificationTemplateService;
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
 * @Date   2020-7-6 23:27:09
 */
@Slf4j
@Api(tags = "通知模板接口")
@RestController
@RequestMapping(value = "/notification_template")
public class NotificationTemplateController {

    @Resource
    NotificationTemplateService notificationTemplateService;

    /**
     * 分页查询通知模板集合
     * @param queryParams
     * @return
     */
    @ApiOperation(value = "分页查询通知模板")
    @GetMapping
    public IPage<NotificationTemplateVO> findPage(QueryParams<NotificationTemplateDO> queryParams){
        IPage<NotificationTemplateDTO> page = notificationTemplateService.findPage(queryParams.getPage(), queryParams.getQueryWrapper());
        return BeanCopierUtils.copy(page, NotificationTemplateVO.class);
    }

    /**
     * 主键查询通知模板
     * @param   id  主键
     * @return  NotificationTemplateResponse   通知模板领域对象
     */
    @ApiOperation(value = "主键查询通知模板")
    @GetMapping(value = "/{id}")
    public NotificationTemplateVO findById(@PathVariable(value = "id") String id){
        NotificationTemplateDTO notificationTemplateDTO = notificationTemplateService.findById(id);
        return BeanCopierUtils.copy(notificationTemplateDTO, NotificationTemplateVO.class);
    }

    /**
     * 新增通知模板
     * @param notificationTemplateDTO
     * @return
     */
    @ApiOperation(value = "新增通知模板")
    @PostMapping
    public NotificationTemplateVO create(@RequestBody @Valid NotificationTemplateDTO notificationTemplateDTO) {
        notificationTemplateDTO = notificationTemplateService.createNotificationTemplate(notificationTemplateDTO);
        return BeanCopierUtils.copy(notificationTemplateDTO, NotificationTemplateVO.class);
    }

    /**
     * 更新通知模板
     * @param id    主键
     * @param notificationTemplateDTO  更新对象参数
     * @return  NotificationTemplateResponse   通知模板领域对象
     */
    @ApiOperation(value = "更新通知模板")
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable(value = "id") String id,
                              @RequestBody @Valid NotificationTemplateDTO notificationTemplateDTO){
        if(StringUtils.isNotBlank(id)){
            notificationTemplateDTO.setId(id);
        }
        return notificationTemplateService.updateNotificationTemplate(notificationTemplateDTO);
    }

    /**
     *  删除通知模板
     * @param id    主键
     */
    @ApiOperation(value = "删除单个通知模板")
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(value = "id") String id){
        return notificationTemplateService.deleteNotificationTemplate(id);
    }

    /**
     *  删除通知模板
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除通知模板")
    @DeleteMapping
    public boolean batchDelete(@RequestBody List<String> ids){
        AssertValid.notEmpty(ids, "数组参数不能为空");
        return notificationTemplateService.deleteNotificationTemplate(ids);
    }


}
