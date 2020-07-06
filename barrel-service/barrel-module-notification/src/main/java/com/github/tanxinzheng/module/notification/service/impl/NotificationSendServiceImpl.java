package com.github.tanxinzheng.module.notification.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.notification.domain.dto.NotificationSendDTO;
import com.github.tanxinzheng.module.notification.domain.entity.NotificationSendDO;
import com.github.tanxinzheng.module.notification.mapper.NotificationSendMapper;
import com.github.tanxinzheng.module.notification.service.NotificationSendService;
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
public class NotificationSendServiceImpl extends ServiceImpl<NotificationSendMapper, NotificationSendDO> implements NotificationSendService {

    @Resource
    NotificationSendMapper notificationSendMapper;

    /**
     * 新增通知发送
     *
     * @param notificationSendDTO
     * @return NotificationSendResponse
     */
    @Transactional
    @Override
    public NotificationSendDTO createNotificationSend(NotificationSendDTO notificationSendDTO) {
        AssertValid.notNull(notificationSendDTO, "notificationSendDTO参数不能为空");
        NotificationSendDO notificationSend = notificationSendDTO.toDO(NotificationSendDO.class);
        boolean isOk = save(notificationSend);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(notificationSend, NotificationSendDTO.class);
    }


    /**
     * 更新通知发送
     *
     * @param notificationSendDTO
     * @return NotificationSendResponse
     */
    @Transactional
    @Override
    public boolean updateNotificationSend(NotificationSendDTO notificationSendDTO) {
        AssertValid.notNull(notificationSendDTO, "notificationSendDTO不能为空");
        NotificationSendDO notificationSendDO = BeanCopierUtils.copy(notificationSendDTO, NotificationSendDO.class);
        return updateById(notificationSendDO);
    }

    /**
     * 批量新增通知发送
     * @param notificationSends
     * @return
     */
    @Transactional
    @Override
    public List<NotificationSendDTO> createNotificationSends(List<NotificationSendDTO> notificationSends) {
        AssertValid.notEmpty(notificationSends, "notificationSends参数不能为空");
        List<NotificationSendDO> notificationSendDOList = BeanCopierUtils.copy(notificationSends, NotificationSendDO.class);
        boolean isOK = saveBatch(notificationSendDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = notificationSendDOList.stream().map(NotificationSendDO::getId).collect(Collectors.toList());
        List<NotificationSendDO> data = notificationSendMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, NotificationSendDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return NotificationSendResponse
     */
    @Override
    public NotificationSendDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        NotificationSendDO notificationSend = getById(id);
        return BeanCopierUtils.copy(notificationSend, NotificationSendDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<NotificationSendDTO>
    */
    @Override
    public List<NotificationSendDTO> findList(QueryWrapper<NotificationSendDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), NotificationSendDTO.class);
    }
    /**
     * 查询通知发送领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<NotificationSendDTO> findPage(IPage<NotificationSendDO> page, QueryWrapper<NotificationSendDO> queryWrapper) {
        IPage<NotificationSendDO> data = (Page<NotificationSendDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, NotificationSendDTO.class);
    }

    /**
     * 批量删除通知发送
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deleteNotificationSend(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除通知发送
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteNotificationSend(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }
}
