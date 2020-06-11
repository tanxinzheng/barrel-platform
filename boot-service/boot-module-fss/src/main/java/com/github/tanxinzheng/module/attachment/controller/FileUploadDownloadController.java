package com.github.tanxinzheng.module.attachment.controller;

import com.aliyun.oss.common.utils.IOUtils;
import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.web.annotation.LoginUser;
import com.github.tanxinzheng.framework.web.web.model.CurrentLoginUser;
import com.github.tanxinzheng.module.attachment.model.AttachmentModel;
import com.github.tanxinzheng.module.attachment.model.AttachmentQuery;
import com.github.tanxinzheng.module.attachment.service.AttachmentService;
import com.github.tanxinzheng.module.fss.model.FileStorageResult;
import com.github.tanxinzheng.module.fss.service.FileStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
@Controller
@RequestMapping(value = "/file")
@Slf4j
public class FileUploadDownloadController {

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    FileStoreService fileStoreService;

    /**
     * 文件上传
     * @param file
     * @throws IOException
     */
    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/upload")
    public AttachmentModel upload(@LoginUser CurrentLoginUser loginUser,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        return attachmentService.createAttachment(loginUser.getId(), file);
    }

    /**
     * 文件下载
     * @return
     * @throws IOException
     * @throws BusinessException
     */
    @ApiOperation(value = "下载文件")
    @RequestMapping(value = "/download")
    public ResponseEntity download(@RequestParam("fileKey") String fileKey) throws IOException {
        AttachmentModel attachmentModel = attachmentService.getOneAttachmentModelCache(fileKey);
        String key = attachmentModel.getAttachmentPath() + File.separator + attachmentModel.getAttachmentKey();
        FileStorageResult result = fileStoreService.getFile(key);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(
                attachmentModel.getOriginName(), "UTF-8"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] bytes = IOUtils.readStreamAsByteArray(result.getInputStream());
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
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
                            @RequestParam("fileKeys") String[] fileKeys,
                            HttpServletResponse response) throws IOException {
        if(StringUtils.isBlank(fileZipName)){
            fileZipName = "批量下载_" + DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
        }
        AttachmentQuery attachmentQuery = new AttachmentQuery();
        attachmentQuery.setAttachmentKeys(fileKeys);
        List<AttachmentModel> attachmentModelList = attachmentService.getAttachmentModelList(attachmentQuery);
        if(CollectionUtils.isEmpty(attachmentModelList)){
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
        for (AttachmentModel attachmentModel : attachmentModelList) {
            zipFile(attachmentModel.getAttachmentPath() + File.separator + attachmentModel.getAttachmentKey(),
                    attachmentModel.getOriginName(), zipOutputStream);
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
    @RequestMapping(value = "/download/temps")
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
