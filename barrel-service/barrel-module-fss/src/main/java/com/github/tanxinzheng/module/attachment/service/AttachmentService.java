package com.github.tanxinzheng.module.attachment.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.attachment.domain.dto.AttachmentDTO;
import com.github.tanxinzheng.module.attachment.domain.entity.AttachmentDO;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 16:47:03
 */
public interface AttachmentService {

    /**
     * 新增附件
     * @param  attachmentCreate
     * @return AttachmentDTO
     */
    public AttachmentDTO createAttachment(AttachmentDTO attachmentCreate);

    /**
     * 批量新增附件
     * @param attachment
     * @return List<Attachment>
     */
    List<AttachmentDTO> createAttachments(List<AttachmentDTO> attachment);

    /**
     * 更新附件
     * @param   attachmentUpdate
     * @return  boolean
     */
    public boolean updateAttachment(AttachmentDTO attachmentUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  AttachmentDTO
     */
    public AttachmentDTO findById(String id);


    /**
     * 文件Key查询对象
     * @param   fileKey
     * @return  AttachmentDTO
     */
    public AttachmentDTO findByFileKey(String fileKey);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<AttachmentDTO>
    */
    public List<AttachmentDTO> findList(LambdaQueryWrapper<AttachmentDO> queryWrapper);

    /**
     * 查询附件领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    public IPage<AttachmentDTO> findPage(IPage<AttachmentDO> page, QueryWrapper<AttachmentDO> queryWrapper);

    /**
     * 批量删除附件
     * @param  ids
     * @return boolean
     */
    public boolean deleteAttachment(List<String> ids);

    /**
     * 删除附件
     * @param  id
     * @return boolean
     */
    public boolean deleteAttachment(String id);

}
