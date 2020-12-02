package com.github.tanxinzheng.module.system.attachment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.framework.web.annotation.LoginUser;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.module.system.attachment.domain.dto.AttachmentDTO;
import com.github.tanxinzheng.module.system.attachment.domain.entity.AttachmentDO;
import com.github.tanxinzheng.module.system.attachment.domain.vo.AttachmentVO;
import com.github.tanxinzheng.module.system.attachment.service.AttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 附件接口
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 16:47:03
 */
@Slf4j
@Api(tags = "附件接口")
@RestController
@RequestMapping(value = "/attachment")
public class AttachmentController {

    @Resource
    AttachmentService attachmentService;

    /**
     * 分页查询附件集合
     * @param queryParams
     * @return
     */
    @ApiOperation(value = "分页查询附件")
    @GetMapping
    public IPage<AttachmentVO> findPage(QueryParams<AttachmentDO> queryParams){
        IPage<AttachmentDTO> page = attachmentService.findPage(queryParams.getPage(), queryParams.getQueryWrapper());
        return BeanCopierUtils.copy(page, AttachmentVO.class);
    }

    /**
     * 主键查询附件
     * @param   id  主键
     * @return  AttachmentResponse   附件领域对象
     */
    @ApiOperation(value = "主键查询附件")
    @GetMapping(value = "/{id}")
    public AttachmentVO findById(@PathVariable(value = "id") String id){
        AttachmentDTO attachmentDTO = attachmentService.findById(id);
        return BeanCopierUtils.copy(attachmentDTO, AttachmentVO.class);
    }

    /**
     * 主键查询附件
     * @param   fileKey  主键
     * @return  AttachmentResponse   附件领域对象
     */
    @ApiOperation(value = "主键查询附件")
    @GetMapping(value = "/fileKey/{fileKey}")
    public AttachmentVO findByFileKey(@PathVariable(value = "fileKey") String fileKey){
        AttachmentDTO attachmentDTO = attachmentService.findByFileKey(fileKey);
        return BeanCopierUtils.copy(attachmentDTO, AttachmentVO.class);
    }

    /**
     * 新增附件
     * @param currentLoginUser
     * @param attachmentDTO
     * @return
     */
    @ApiOperation(value = "新增附件")
    @PostMapping
    public AttachmentVO create(@LoginUser CurrentLoginUser currentLoginUser,
                               @RequestBody @Valid AttachmentDTO attachmentDTO) {
        AssertValid.isTrue(!attachmentDTO.getMultipartFile().isEmpty(), "附件文件不能为空");
        attachmentDTO.setUploadBy(currentLoginUser.getId());
        attachmentDTO = attachmentService.createAttachment(attachmentDTO);
        return BeanCopierUtils.copy(attachmentDTO, AttachmentVO.class);
    }

    /**
     * 更新附件
     * @param id    主键
     * @param attachmentDTO  更新对象参数
     * @return  AttachmentResponse   附件领域对象
     */
    @ApiOperation(value = "更新附件")
    @PutMapping(value = "/{id}")
    public boolean update(@LoginUser CurrentLoginUser loginUser,
                          @PathVariable(value = "id") String id,
                          @RequestBody @Valid AttachmentDTO attachmentDTO){
        if(StringUtils.isNotBlank(id)){
            attachmentDTO.setId(id);
        }
        attachmentDTO.setUpdatedBy(loginUser.getId());
        attachmentDTO.setUpdatedTime(LocalDateTime.now());
        return attachmentService.updateAttachment(attachmentDTO);
    }

    /**
     *  删除附件
     * @param id    主键
     */
    @ApiOperation(value = "删除单个附件")
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(value = "id") String id){
        return attachmentService.deleteAttachment(id);
    }

    /**
     *  删除附件
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除附件")
    @DeleteMapping
    public boolean batchDelete(@RequestBody List<String> ids){
        AssertValid.notEmpty(ids, "数组参数不能为空");
        return attachmentService.deleteAttachment(ids);
    }


}
