package com.github.tanxinzheng.module.attachment.controller;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.logger.ActionLog;
import com.github.tanxinzheng.module.attachment.model.AttachmentModel;
import com.github.tanxinzheng.module.attachment.model.AttachmentQuery;
import com.github.tanxinzheng.module.attachment.service.AttachmentService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    /**
     * 附件列表
     * @param   attachmentQuery    附件查询参数对象
     * @return  Page<AttachmentModel> 附件领域分页对象
     */
    @ApiOperation(value = "查询附件列表")
    @GetMapping
    public Page<AttachmentModel> getAttachmentList(AttachmentQuery attachmentQuery){
        return attachmentService.getAttachmentModelPage(attachmentQuery);
    }

    /**
     * 查询单个附件
     * @param   id  主键
     * @return  AttachmentModel   附件领域对象
     */
    @ApiOperation(value = "查询附件")
    @GetMapping(value = "/{id}")
    public AttachmentModel getAttachmentById(@PathVariable(value = "id") String id){
        return attachmentService.getOneAttachmentModel(id);
    }

    /**
     * 新增附件
     * @param   attachmentModel  新增对象参数
     * @return  AttachmentModel   附件领域对象
     */
    @ApiOperation(value = "新增附件")
    @ActionLog(actionName = "新增附件")
    @PostMapping
    public AttachmentModel createAttachment(@RequestBody @Valid AttachmentModel attachmentModel) {
        return attachmentService.createAttachment(attachmentModel);
    }

    /**
     * 更新附件
     * @param id    主键
     * @param attachmentModel  更新对象参数
     * @return  AttachmentModel   附件领域对象
     */
    @ApiOperation(value = "更新附件")
    @ActionLog(actionName = "更新附件")
    @PutMapping(value = "/{id}")
    public AttachmentModel updateAttachment(@PathVariable(value = "id") String id,
                           @RequestBody @Valid AttachmentModel attachmentModel){
        if(StringUtils.isNotBlank(id)){
            attachmentModel.setId(id);
        }
        attachmentService.updateAttachment(attachmentModel);
        return attachmentService.getOneAttachmentModel(id);
    }

    /**
     *  删除附件
     * @param id    主键
     */
    @ApiOperation(value = "删除单个附件")
    @ActionLog(actionName = "删除单个附件")
    @DeleteMapping(value = "/{id}")
    public void deleteAttachment(@PathVariable(value = "id") String id){
        attachmentService.deleteAttachment(id);
    }

    /**
     *  删除附件
     * @param attachmentQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除附件")
    @ActionLog(actionName = "批量删除附件")
    @DeleteMapping
    public void deleteAttachments(@RequestBody AttachmentQuery attachmentQuery){
        attachmentService.deleteAttachment(attachmentQuery.getIds());
    }


}
