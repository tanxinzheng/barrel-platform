package com.github.tanxinzheng.module.system.attachment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.attachment.domain.dto.AttachmentDTO;
import com.github.tanxinzheng.module.system.attachment.domain.entity.AttachmentDO;
import com.github.tanxinzheng.module.system.attachment.mapper.AttachmentMapper;
import com.github.tanxinzheng.module.system.attachment.service.AttachmentService;
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
 * @Date   2020-7-6 16:47:03
 */
@Slf4j
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, AttachmentDO> implements AttachmentService {

    @Resource
    AttachmentMapper attachmentMapper;

    /**
     * 新增附件
     *
     * @param attachmentDTO
     * @return AttachmentResponse
     */
    @Transactional
    @Override
    public AttachmentDTO createAttachment(AttachmentDTO attachmentDTO) {
        AssertValid.notNull(attachmentDTO, "attachmentDTO参数不能为空");
        AttachmentDO attachment = attachmentDTO.toDO(AttachmentDO.class);
        boolean isOk = save(attachment);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(attachment, AttachmentDTO.class);
    }


    /**
     * 更新附件
     *
     * @param attachmentDTO
     * @return AttachmentResponse
     */
    @Transactional
    @Override
    public boolean updateAttachment(AttachmentDTO attachmentDTO) {
        AssertValid.notNull(attachmentDTO, "attachmentDTO不能为空");
        AttachmentDO attachmentDO = BeanCopierUtils.copy(attachmentDTO, AttachmentDO.class);
        return updateById(attachmentDO);
    }

    /**
     * 批量新增附件
     * @param attachments
     * @return
     */
    @Transactional
    @Override
    public List<AttachmentDTO> createAttachments(List<AttachmentDTO> attachments) {
        AssertValid.notEmpty(attachments, "attachments参数不能为空");
        List<AttachmentDO> attachmentDOList = BeanCopierUtils.copy(attachments, AttachmentDO.class);
        boolean isOK = saveBatch(attachmentDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = attachmentDOList.stream().map(AttachmentDO::getId).collect(Collectors.toList());
        List<AttachmentDO> data = attachmentMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, AttachmentDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return AttachmentResponse
     */
    @Override
    public AttachmentDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        AttachmentDO attachment = getById(id);
        return BeanCopierUtils.copy(attachment, AttachmentDTO.class);
    }

    /**
     * 文件Key查询对象
     *
     * @param fileKey
     * @return AttachmentDTO
     */
    @Override
    public AttachmentDTO findByFileKey(String fileKey) {
        AssertValid.notBlank(fileKey, "fileKey参数不能为空");
        LambdaQueryWrapper<AttachmentDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AttachmentDO::getAttachmentKey, fileKey);
        AttachmentDO attachmentDO = getOne(lambdaQueryWrapper);
        return BeanCopierUtils.copy(attachmentDO, AttachmentDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<AttachmentDTO>
    */
    @Override
    public List<AttachmentDTO> findList(LambdaQueryWrapper<AttachmentDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), AttachmentDTO.class);
    }
    /**
     * 查询附件领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<AttachmentDTO> findPage(IPage<AttachmentDO> page, QueryWrapper<AttachmentDO> queryWrapper) {
        IPage<AttachmentDO> data = (Page<AttachmentDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, AttachmentDTO.class);
    }

    /**
     * 批量删除附件
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deleteAttachment(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除附件
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteAttachment(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }
}
