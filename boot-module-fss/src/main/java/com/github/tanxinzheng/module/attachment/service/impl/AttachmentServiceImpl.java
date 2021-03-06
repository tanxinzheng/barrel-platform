package com.github.tanxinzheng.module.attachment.service.impl;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.mybatis.page.PageInterceptor;
import com.github.tanxinzheng.framework.utils.UUIDGenerator;
import com.github.tanxinzheng.framework.web.json.DictionaryIndex;
import com.github.tanxinzheng.module.attachment.mapper.AttachmentMapper;
import com.github.tanxinzheng.module.attachment.model.Attachment;
import com.github.tanxinzheng.module.attachment.model.AttachmentModel;
import com.github.tanxinzheng.module.attachment.model.AttachmentQuery;
import com.github.tanxinzheng.module.attachment.service.AttachmentService;
import com.github.tanxinzheng.module.fss.model.FileStorageInfo;
import com.github.tanxinzheng.module.fss.model.FileStorageResult;
import com.github.tanxinzheng.module.fss.service.FileStoreService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
 * @version 1.0.0
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private static final String DEFAULT_GROUP = "DEFAULT";

    @Autowired
    AttachmentMapper attachmentMapper;

    @Autowired
    FileStoreService fileStoreService;

    /**
     * 新增附件
     *
     * @param attachmentModel 新增附件对象参数
     * @return AttachmentModel    附件领域对象
     */
    @Override
    @Transactional
    public AttachmentModel createAttachment(AttachmentModel attachmentModel) {
        Attachment attachment = createAttachment(attachmentModel.getEntity());
        if(attachment != null){
            return getOneAttachmentModel(attachment.getId());
        }
        return null;
    }

    @Override
    @Transactional
    public AttachmentModel createAttachment(String userId, MultipartFile multipartFile) {
        if(multipartFile.isEmpty()){
            return null;
        }
        String fileName = UUIDGenerator.getInstance().getUUID();
        FileStorageInfo fileStorageInfo = new FileStorageInfo(multipartFile);
        FileStorageResult result = fileStoreService.newFile(fileStorageInfo);
        if(!result.isSuccess()){
            throw new BusinessException("文件上传失败");
        }
        AttachmentModel attachmentModel = new AttachmentModel();
        attachmentModel.setAttachmentKey(fileName);
        attachmentModel.setAttachmentSize(multipartFile.getSize());
        attachmentModel.setUploadTime(new Date());
        attachmentModel.setAttachmentGroup(DEFAULT_GROUP);
        attachmentModel.setAttachmentPath(result.getStoragePath());
        attachmentModel.setUploadUserId(userId);
        attachmentModel.setOriginName(multipartFile.getOriginalFilename());
        attachmentModel.setAttachmentSuffix(multipartFile.getContentType());
        attachmentModel = createAttachment(attachmentModel);
        return attachmentModel;
    }

    /**
     * 新增附件实体对象
     *
     * @param attachment 新增附件实体对象参数
     * @return Attachment 附件实体对象
     */
    @Override
    @Transactional
    public Attachment createAttachment(Attachment attachment) {
        attachmentMapper.insertSelective(attachment);
        return attachment;
    }

    /**
    * 批量新增附件
    *
    * @param attachmentModels 新增附件对象集合参数
    * @return List<AttachmentModel>    附件领域对象集合
    */
    @Override
    @Transactional
    public List<AttachmentModel> createAttachments(List<AttachmentModel> attachmentModels) {
        List<AttachmentModel> attachmentModelList = null;
        for (AttachmentModel attachmentModel : attachmentModels) {
            attachmentModel = createAttachment(attachmentModel);
            if(attachmentModel != null){
                if(attachmentModelList == null){
                    attachmentModelList = new ArrayList<>();
                }
                attachmentModelList.add(attachmentModel);
            }
        }
        return attachmentModelList;
    }

    /**
    * 更新附件
    *
    * @param attachmentModel 更新附件对象参数
    * @param attachmentQuery 过滤附件对象参数
    */
    @Override
    @Transactional
    public void updateAttachment(AttachmentModel attachmentModel, AttachmentQuery attachmentQuery) {
        attachmentMapper.updateSelectiveByQuery(attachmentModel.getEntity(), attachmentQuery);
    }

    /**
     * 更新附件
     *
     * @param attachmentModel 更新附件对象参数
     */
    @Override
    @Transactional
    public void updateAttachment(AttachmentModel attachmentModel) {
        updateAttachment(attachmentModel.getEntity());
    }

    /**
     * 更新附件实体对象
     *
     * @param attachment 新增附件实体对象参数
     * @return Attachment 附件实体对象
     */
    @Override
    @Transactional
    public void updateAttachment(Attachment attachment) {
        attachmentMapper.updateSelective(attachment);
    }

    /**
     * 删除附件
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteAttachment(String[] ids) {
        if(ArrayUtils.isEmpty(ids)) {
            return;
        }
        for (String id : ids) {
            deleteAttachment(id);
        }
    }

    /**
    * 删除附件
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteAttachment(String id) {
        Attachment attachment = getOneAttachment(id);
        if(attachment == null){
            return;
        }
        String fileKey = attachment.getAttachmentPath() + File.pathSeparator + attachment.getAttachmentKey();
        fileStoreService.deleteFile(fileKey);
        attachmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void deleteAttachmentByKey(String attachmentKey) {
        AttachmentModel attachmentModel = getOneAttachmentModelCache(attachmentKey);
        if(attachmentModel == null){
            return;
        }
        String fileKey = attachmentModel.getAttachmentPath() + File.separator + attachmentModel.getAttachmentKey();
        fileStoreService.deleteFile(fileKey);
        attachmentMapper.deleteByPrimaryKey(attachmentModel.getId());
    }

    /**
     * 查询附件领域分页对象（带参数条件）
     *
     * @param attachmentQuery 查询参数
     * @return Page<AttachmentModel>   附件参数对象
     */
    @Override
    public Page<AttachmentModel> getAttachmentModelPage(AttachmentQuery attachmentQuery) {
        PageInterceptor.startPage(attachmentQuery);
        attachmentMapper.selectModel(attachmentQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询附件领域集合对象（带参数条件）
     *
     * @param attachmentQuery 查询参数对象
     * @return List<AttachmentModel> 附件领域集合对象
     */
    @Override
    public List<AttachmentModel> getAttachmentModelList(AttachmentQuery attachmentQuery) {
        return attachmentMapper.selectModel(attachmentQuery);
    }

    /**
     * 查询附件实体对象
     *
     * @param id 主键
     * @return Attachment 附件实体对象
     */
    @Override
    public Attachment getOneAttachment(String id) {
        return attachmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return AttachmentModel 附件领域对象
     */
    @Override
    public AttachmentModel getOneAttachmentModel(String id) {
        return attachmentMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param attachmentQuery 附件查询参数对象
     * @return AttachmentModel 附件领域对象
     */
    @Override
    public AttachmentModel getOneAttachmentModel(AttachmentQuery attachmentQuery) {
        List<AttachmentModel> attachmentModelList = attachmentMapper.selectModel(attachmentQuery);
        if(CollectionUtils.isEmpty(attachmentModelList)){
            return null;
        }
        if(attachmentModelList.size() > 1){
            throw new BusinessException("查询异常，查询唯一性资源时匹配到多个资源");
        }
        return attachmentModelList.get(0);
    }

    /**
     * 缓存查询附件实体对象
     *
     * @param attachmentKey 文件Key
     * @return Attachment 附件实体对象
     */
    @Cacheable(cacheNames = DictionaryIndex.DICTIONARY_CACHE_NAME_KEY)
    @Override
    public AttachmentModel getOneAttachmentModelCache(String attachmentKey) {
        AttachmentQuery attachmentQuery = new AttachmentQuery();
        attachmentQuery.setAttachmentKey(attachmentKey);
        return getOneAttachmentModel(attachmentQuery);
    }
}
