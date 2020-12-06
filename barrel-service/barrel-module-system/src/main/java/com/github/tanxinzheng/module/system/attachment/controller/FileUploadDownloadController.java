package com.github.tanxinzheng.module.system.attachment.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.web.annotation.LoginUser;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.module.system.attachment.domain.dto.AttachmentDTO;
import com.github.tanxinzheng.module.system.attachment.domain.dto.FileUploadRequestDTO;
import com.github.tanxinzheng.module.system.attachment.domain.entity.AttachmentDO;
import com.github.tanxinzheng.module.system.attachment.domain.vo.AttachmentVO;
import com.github.tanxinzheng.module.system.attachment.service.AttachmentService;
import com.github.tanxinzheng.module.system.fss.model.FileStorageResult;
import com.github.tanxinzheng.module.system.fss.service.StorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by tanxinzheng on 16/10/16.
 */
@Api(tags = {"文件管理"})
@RestController
@RequestMapping(value = "/file")
@Slf4j
public class FileUploadDownloadController {

    @Resource
    AttachmentService attachmentService;

    @Resource
    StorageService fileStoreService;

    /**
     * 文件上传，默认上传至临时目录，临时目录缓存1个小时，一个小时没有转到其他目录则做自动删除
     * @param loginUser
     * @param multipartFile
     * @param relationId
     * @param relationType
     * @param owner
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/upload")
    public Result<AttachmentVO> upload(@LoginUser CurrentLoginUser loginUser,
                               @RequestParam(value = "multipartFile", required = true) MultipartFile multipartFile,
                               @RequestParam(value = "relationId", required = true) String relationId,
                               @RequestParam(value = "relationType", required = true) String relationType,
                               @RequestParam(value = "owner", required = true) String owner) throws IOException {
        AttachmentDTO attachmentDTO = AttachmentDTO.builder()
                .owner(owner)
                .relationId(relationId)
                .attachmentGroup(relationType)
                .uploadBy(loginUser.getId())
                .multipartFile(multipartFile)
                .build();
        attachmentDTO.setUploadBy(loginUser.getId());
        attachmentDTO.setCreatedBy(loginUser.getId());
        attachmentDTO = attachmentService.createAttachment(attachmentDTO);
        return Result.success(BeanCopierUtils.copy(attachmentDTO, AttachmentVO.class));
    }

    /**
     * 文件下载
     * @return
     * @throws IOException
     * @throws BusinessException
     */
    @ApiOperation(value = "下载文件")
    @RequestMapping(value = "/download")
    public void download(@RequestParam("fileKey") String fileKey) throws IOException {
        AttachmentDTO attachmentDTO = attachmentService.findByFileKey(fileKey);
        FileStorageResult result = fileStoreService.getFile(attachmentDTO.getAttachmentPath());
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDispositionFormData("attachment", URLEncoder.encode(
//                attachmentDTO.getOriginName(), "UTF-8"));
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        byte[] bytes = IOUtils.toByteArray(result.getInputStream());
//        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(attachmentDTO.getOriginName());
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type", type);
        // 设置编码
        String hehe = new String(attachmentDTO.getOriginName().getBytes("utf-8"), "iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
        // 发送给客户端的数据
        OutputStream outputStream = response.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(result.getInputStream());
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }


    /**
     * 文件批量打包下载
     * @param fileZipName
     * @param fileKeys
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "批量文件zip打包下载")
    @RequestMapping(value = "/download/zip")
    public void downloadZip(@RequestParam(value = "fileName", required = false) String fileZipName,
                            @RequestParam(value = "fileKeys", required = true) List<String> fileKeys,
                            HttpServletResponse response) throws IOException {
        if(StringUtils.isBlank(fileZipName)){
            fileZipName = "批量下载_" + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
        }
        LambdaQueryWrapper<AttachmentDO> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.in(AttachmentDO::getAttachmentKey, fileKeys);
        List<AttachmentDTO> attachmentDTOList = attachmentService.findList(lambdaQuery);
        if(CollectionUtils.isEmpty(attachmentDTOList)){
            return;
        }
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setCharacterEncoding("UTF-8");
        if(!fileZipName.endsWith(".zip")){
            fileZipName = fileZipName + ".zip";
        }
        fileZipName = new String(fileZipName.getBytes("UTF-8"), "ISO-8859-1");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileZipName);
        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        for (AttachmentDTO attachmentDTO : attachmentDTOList) {
            zipFile(attachmentDTO.getAttachmentPath() +
                            File.separator + attachmentDTO.getAttachmentKey(),
                    attachmentDTO.getOriginName(), zipOutputStream);
        }
        zipOutputStream.close();
        response.getOutputStream().close();
    }

    private void zipFile(String key, String filename, ZipOutputStream outputStream){
        try {
            FileStorageResult result = fileStoreService.getFile(key);
            if(result.isSuccess()){
                ZipEntry zipEntry = new ZipEntry(filename);
                outputStream.putNextEntry(zipEntry);
                org.apache.commons.io.IOUtils.copy(result.getInputStream(), outputStream);
                outputStream.closeEntry();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 下载临时文件
     * @param filename
     * @param request
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "下载临时文件")
    @PostMapping(value = "/download/temps")
    public ResponseEntity downloadTempFile(@RequestParam("file") String filename,
                                   HttpServletRequest request) throws IOException {
        String realFilename = URLDecoder.decode(filename, "UTF-8");
        String downloadsPath = request.getServletContext().getRealPath("/WEB-INF/temps");
        File file = new File(downloadsPath, realFilename);
        if(!file.exists()) {
            throw new IllegalArgumentException("您要下载的文件的不存在");
        }
        String name = realFilename.substring(17, realFilename.length());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(name, "UTF-8"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        if(bytes != null){
            FileUtils.deleteQuietly(file);
        }
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
    }

}
