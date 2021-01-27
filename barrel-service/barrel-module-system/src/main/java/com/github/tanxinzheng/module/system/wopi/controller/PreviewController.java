package com.github.tanxinzheng.module.system.wopi.controller;

import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.attachment.domain.dto.AttachmentDTO;
import com.github.tanxinzheng.module.system.attachment.service.AttachmentService;
import com.github.tanxinzheng.module.system.fss.model.FileStorageResult;
import com.github.tanxinzheng.module.system.fss.service.StorageService;
import com.github.tanxinzheng.module.system.wopi.constant.OfficeOnlineProperties;
import com.github.tanxinzheng.module.system.wopi.constant.WopiProtocol;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.MessageFormat;

/*
 * @Description 文件预览接口
 * @Author tanxinzheng
 * @Date 2020/12/6
 */
@Slf4j
@Api(tags = "文件预览接口")
@RequestMapping(value = "/preview")
@Controller
public class PreviewController {

    @Resource
    OfficeOnlineProperties officeOnlineProperties;

    @Resource
    AttachmentService attachmentService;

    @Resource
    StorageService storageService;

    /**
     * 跳转至预览服务
     * @param fileKey
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "文件预览跳转")
    @GetMapping(value = "/{fileKey}")
    public void redirectPreviewUrl(@PathVariable(value = "fileKey", required = true) String fileKey,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        AttachmentDTO attachmentDTO = attachmentService.findByFileKey(fileKey);
        AssertValid.notNull(attachmentDTO, "未找到匹配查询条件的文件");
        WopiProtocol wopiProtocol = WopiProtocol.toProtocol(attachmentDTO.getAttachmentSuffix());
        AssertValid.notNull(wopiProtocol, "未找到匹配文件后缀的预览协议");
        String previewUrl;
        if(!wopiProtocol.isWopiProtocol()){
            previewUrl = MessageFormat.format("/preview/image/{0}", fileKey);
        }else {
            previewUrl = wopiProtocol.toPreviewUrl(officeOnlineProperties.getPreviewServer(), officeOnlineProperties.getWopiServer(), fileKey);
        }
        log.info("preview file key: {}, preview url: {}", fileKey, previewUrl);
        response.sendRedirect(previewUrl);
    }

    /**
     * 图片预览
     * @param fileKey
     * @param request
     * @param response
     * @param path
     */
    @ApiOperation(value = "图片预览")
    @GetMapping("/image/{fileKey}")
    public void getUserLogo(@PathVariable(value = "fileKey") String fileKey,
                            HttpServletRequest request,
                            HttpServletResponse response, String path) {
        response.setContentType("image/jpeg"); // 设置返回内容格式
        AttachmentDTO attachmentDTO = attachmentService.findByFileKey(fileKey);
        AssertValid.notNull(attachmentDTO, "未找到匹配条件的文件");
        InputStream in;
        try {
            FileStorageResult fileStorageResult = storageService.getFile(attachmentDTO.getAttachmentPath());
            AssertValid.notNull(attachmentDTO, "文件流不存在或已被删除");
            in = fileStorageResult.getInputStream();
            OutputStream os = response.getOutputStream(); // 创建输出流
            byte[] b = new byte[1024];
            while (in.read(b) != -1) {
                os.write(b);
            }
            in.close();
            os.flush();
            os.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
