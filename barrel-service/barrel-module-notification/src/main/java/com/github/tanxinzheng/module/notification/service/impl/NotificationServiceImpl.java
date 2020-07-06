package com.github.tanxinzheng.module.notification.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.notification.domain.dto.NotificationDTO;
import com.github.tanxinzheng.module.notification.domain.entity.NotificationDO;
import com.github.tanxinzheng.module.notification.mapper.NotificationMapper;
import com.github.tanxinzheng.module.notification.service.NotificationService;
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
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, NotificationDO> implements NotificationService {

    @Resource
    NotificationMapper notificationMapper;

    /**
     * 新增通知
     *
     * @param notificationDTO
     * @return NotificationResponse
     */
    @Transactional
    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        AssertValid.notNull(notificationDTO, "notificationDTO参数不能为空");
        NotificationDO notification = notificationDTO.toDO(NotificationDO.class);
        boolean isOk = save(notification);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(notification, NotificationDTO.class);
    }


    /**
     * 更新通知
     *
     * @param notificationDTO
     * @return NotificationResponse
     */
    @Transactional
    @Override
    public boolean updateNotification(NotificationDTO notificationDTO) {
        AssertValid.notNull(notificationDTO, "notificationDTO不能为空");
        NotificationDO notificationDO = BeanCopierUtils.copy(notificationDTO, NotificationDO.class);
        return updateById(notificationDO);
    }

    /**
     * 批量新增通知
     * @param notifications
     * @return
     */
    @Transactional
    @Override
    public List<NotificationDTO> createNotifications(List<NotificationDTO> notifications) {
        AssertValid.notEmpty(notifications, "notifications参数不能为空");
        List<NotificationDO> notificationDOList = BeanCopierUtils.copy(notifications, NotificationDO.class);
        boolean isOK = saveBatch(notificationDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = notificationDOList.stream().map(NotificationDO::getId).collect(Collectors.toList());
        List<NotificationDO> data = notificationMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, NotificationDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return NotificationResponse
     */
    @Override
    public NotificationDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        NotificationDO notification = getById(id);
        return BeanCopierUtils.copy(notification, NotificationDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<NotificationDTO>
    */
    @Override
    public List<NotificationDTO> findList(QueryWrapper<NotificationDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), NotificationDTO.class);
    }
    /**
     * 查询通知领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<NotificationDTO> findPage(IPage<NotificationDO> page, QueryWrapper<NotificationDO> queryWrapper) {
        IPage<NotificationDO> data = (Page<NotificationDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, NotificationDTO.class);
    }

    /**
     * 批量删除通知
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deleteNotification(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除通知
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteNotification(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }
}
