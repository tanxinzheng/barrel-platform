package com.github.tanxinzheng.module.notification.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.notification.domain.dto.NotificationReceiveDTO;
import com.github.tanxinzheng.module.notification.domain.entity.NotificationReceiveDO;
import com.github.tanxinzheng.module.notification.mapper.NotificationReceiveMapper;
import com.github.tanxinzheng.module.notification.service.NotificationReceiveService;
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
public class NotificationReceiveServiceImpl extends ServiceImpl<NotificationReceiveMapper, NotificationReceiveDO> implements NotificationReceiveService {

    @Resource
    NotificationReceiveMapper notificationReceiveMapper;

    /**
     * 新增通知接收
     *
     * @param notificationReceiveDTO
     * @return NotificationReceiveResponse
     */
    @Transactional
    @Override
    public NotificationReceiveDTO createNotificationReceive(NotificationReceiveDTO notificationReceiveDTO) {
        AssertValid.notNull(notificationReceiveDTO, "notificationReceiveDTO参数不能为空");
        NotificationReceiveDO notificationReceive = notificationReceiveDTO.toDO(NotificationReceiveDO.class);
        boolean isOk = save(notificationReceive);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(notificationReceive, NotificationReceiveDTO.class);
    }


    /**
     * 更新通知接收
     *
     * @param notificationReceiveDTO
     * @return NotificationReceiveResponse
     */
    @Transactional
    @Override
    public boolean updateNotificationReceive(NotificationReceiveDTO notificationReceiveDTO) {
        AssertValid.notNull(notificationReceiveDTO, "notificationReceiveDTO不能为空");
        NotificationReceiveDO notificationReceiveDO = BeanCopierUtils.copy(notificationReceiveDTO, NotificationReceiveDO.class);
        return updateById(notificationReceiveDO);
    }

    /**
     * 批量新增通知接收
     * @param notificationReceives
     * @return
     */
    @Transactional
    @Override
    public List<NotificationReceiveDTO> createNotificationReceives(List<NotificationReceiveDTO> notificationReceives) {
        AssertValid.notEmpty(notificationReceives, "notificationReceives参数不能为空");
        List<NotificationReceiveDO> notificationReceiveDOList = BeanCopierUtils.copy(notificationReceives, NotificationReceiveDO.class);
        boolean isOK = saveBatch(notificationReceiveDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = notificationReceiveDOList.stream().map(NotificationReceiveDO::getId).collect(Collectors.toList());
        List<NotificationReceiveDO> data = notificationReceiveMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, NotificationReceiveDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return NotificationReceiveResponse
     */
    @Override
    public NotificationReceiveDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        NotificationReceiveDO notificationReceive = getById(id);
        return BeanCopierUtils.copy(notificationReceive, NotificationReceiveDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<NotificationReceiveDTO>
    */
    @Override
    public List<NotificationReceiveDTO> findList(QueryWrapper<NotificationReceiveDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), NotificationReceiveDTO.class);
    }
    /**
     * 查询通知接收领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<NotificationReceiveDTO> findPage(IPage<NotificationReceiveDO> page, QueryWrapper<NotificationReceiveDO> queryWrapper) {
        IPage<NotificationReceiveDO> data = (Page<NotificationReceiveDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, NotificationReceiveDTO.class);
    }

    /**
     * 批量删除通知接收
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deleteNotificationReceive(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除通知接收
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteNotificationReceive(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }
}
