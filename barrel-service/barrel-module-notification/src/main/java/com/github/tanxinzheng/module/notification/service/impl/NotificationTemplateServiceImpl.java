package com.github.tanxinzheng.module.notification.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.notification.domain.dto.NotificationTemplateDTO;
import com.github.tanxinzheng.module.notification.domain.entity.NotificationTemplateDO;
import com.github.tanxinzheng.module.notification.mapper.NotificationTemplateMapper;
import com.github.tanxinzheng.module.notification.service.NotificationTemplateService;
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
 * @Date   2020-7-6 23:27:09
 */
@Slf4j
@Service
public class NotificationTemplateServiceImpl extends ServiceImpl<NotificationTemplateMapper, NotificationTemplateDO> implements NotificationTemplateService {

    @Resource
    NotificationTemplateMapper notificationTemplateMapper;

    /**
     * 新增通知模板
     *
     * @param notificationTemplateDTO
     * @return NotificationTemplateResponse
     */
    @Transactional
    @Override
    public NotificationTemplateDTO createNotificationTemplate(NotificationTemplateDTO notificationTemplateDTO) {
        AssertValid.notNull(notificationTemplateDTO, "notificationTemplateDTO参数不能为空");
        NotificationTemplateDO notificationTemplate = notificationTemplateDTO.toDO(NotificationTemplateDO.class);
        boolean isOk = save(notificationTemplate);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(notificationTemplate, NotificationTemplateDTO.class);
    }


    /**
     * 更新通知模板
     *
     * @param notificationTemplateDTO
     * @return NotificationTemplateResponse
     */
    @Transactional
    @Override
    public boolean updateNotificationTemplate(NotificationTemplateDTO notificationTemplateDTO) {
        AssertValid.notNull(notificationTemplateDTO, "notificationTemplateDTO不能为空");
        NotificationTemplateDO notificationTemplateDO = BeanCopierUtils.copy(notificationTemplateDTO, NotificationTemplateDO.class);
        return updateById(notificationTemplateDO);
    }

    /**
     * 批量新增通知模板
     * @param notificationTemplates
     * @return
     */
    @Transactional
    @Override
    public List<NotificationTemplateDTO> createNotificationTemplates(List<NotificationTemplateDTO> notificationTemplates) {
        AssertValid.notEmpty(notificationTemplates, "notificationTemplates参数不能为空");
        List<NotificationTemplateDO> notificationTemplateDOList = BeanCopierUtils.copy(notificationTemplates, NotificationTemplateDO.class);
        boolean isOK = saveBatch(notificationTemplateDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = notificationTemplateDOList.stream().map(NotificationTemplateDO::getId).collect(Collectors.toList());
        List<NotificationTemplateDO> data = notificationTemplateMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, NotificationTemplateDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return NotificationTemplateResponse
     */
    @Override
    public NotificationTemplateDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        NotificationTemplateDO notificationTemplate = getById(id);
        return BeanCopierUtils.copy(notificationTemplate, NotificationTemplateDTO.class);
    }

    /**
     * 根据code查询对象
     *
     * @param templateCode
     * @return
     */
    @Override
    public NotificationTemplateDTO findByCode(String templateCode) {
        LambdaQueryWrapper<NotificationTemplateDO> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(NotificationTemplateDO::getTemplateCode, templateCode);
        NotificationTemplateDO notificationDO = getOne(lambdaQueryWrapper);
        return BeanCopierUtils.copy(notificationDO, NotificationTemplateDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<NotificationTemplateDTO>
    */
    @Override
    public List<NotificationTemplateDTO> findList(QueryWrapper<NotificationTemplateDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), NotificationTemplateDTO.class);
    }
    /**
     * 查询通知模板领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<NotificationTemplateDTO> findPage(IPage<NotificationTemplateDO> page, QueryWrapper<NotificationTemplateDO> queryWrapper) {
        IPage<NotificationTemplateDO> data = (Page<NotificationTemplateDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, NotificationTemplateDTO.class);
    }

    /**
     * 批量删除通知模板
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deleteNotificationTemplate(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除通知模板
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteNotificationTemplate(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }
}
