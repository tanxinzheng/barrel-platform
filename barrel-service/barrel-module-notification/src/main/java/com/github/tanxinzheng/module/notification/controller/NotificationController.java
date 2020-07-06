package com.github.tanxinzheng.module.notification.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.notification.domain.dto.NotificationDTO;
import com.github.tanxinzheng.module.notification.domain.entity.NotificationDO;
import com.github.tanxinzheng.module.notification.domain.vo.NotificationVO;
import com.github.tanxinzheng.module.notification.service.NotificationService;
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
@Api(tags = "通知接口")
@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    @Resource
    NotificationService notificationService;

    /**
     * 分页查询通知集合
     * @param queryParams
     * @return
     */
    @ApiOperation(value = "分页查询通知")
    @GetMapping
    public IPage<NotificationVO> findPage(QueryParams<NotificationDO> queryParams){
        IPage<NotificationDTO> page = notificationService.findPage(queryParams.getPage(), queryParams.getQueryWrapper());
        return BeanCopierUtils.copy(page, NotificationVO.class);
    }

    /**
     * 主键查询通知
     * @param   id  主键
     * @return  NotificationResponse   通知领域对象
     */
    @ApiOperation(value = "主键查询通知")
    @GetMapping(value = "/{id}")
    public NotificationVO findById(@PathVariable(value = "id") String id){
        NotificationDTO notificationDTO = notificationService.findById(id);
        return BeanCopierUtils.copy(notificationDTO, NotificationVO.class);
    }

    /**
     * 新增通知
     * @param notificationDTO
     * @return
     */
    @ApiOperation(value = "新增通知")
    @PostMapping
    public NotificationVO create(@RequestBody @Valid NotificationDTO notificationDTO) {
        notificationDTO = notificationService.createNotification(notificationDTO);
        return BeanCopierUtils.copy(notificationDTO, NotificationVO.class);
    }

    /**
     * 更新通知
     * @param id    主键
     * @param notificationDTO  更新对象参数
     * @return  NotificationResponse   通知领域对象
     */
    @ApiOperation(value = "更新通知")
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable(value = "id") String id,
                              @RequestBody @Valid NotificationDTO notificationDTO){
        if(StringUtils.isNotBlank(id)){
            notificationDTO.setId(id);
        }
        return notificationService.updateNotification(notificationDTO);
    }

    /**
     *  删除通知
     * @param id    主键
     */
    @ApiOperation(value = "删除单个通知")
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(value = "id") String id){
        return notificationService.deleteNotification(id);
    }

    /**
     *  删除通知
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除通知")
    @DeleteMapping
    public boolean batchDelete(@RequestBody List<String> ids){
        AssertValid.notEmpty(ids, "数组参数不能为空");
        return notificationService.deleteNotification(ids);
    }


}
