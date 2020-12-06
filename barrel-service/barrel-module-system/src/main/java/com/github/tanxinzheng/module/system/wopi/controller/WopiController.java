package com.github.tanxinzheng.module.system.wopi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.attachment.domain.dto.AttachmentDTO;
import com.github.tanxinzheng.module.system.attachment.service.AttachmentService;
import com.github.tanxinzheng.module.system.fss.model.FileStorageResult;
import com.github.tanxinzheng.module.system.fss.service.StorageService;
import com.github.tanxinzheng.module.system.wopi.domain.vo.WopiFileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

@Slf4j
@Api(tags = "Wopi服务")
@RestController
@RequestMapping(value = "/wopi")
public class WopiController {

    @Resource
    AttachmentService attachmentService;
    @Resource
    StorageService storageService;



    /**
     * 获取文件流
     * @param name
     * @param response
     */
    @ApiOperation(value = "查询文件流")
    @GetMapping("/files/{name}/contents")
    public void getFile(@PathVariable(name = "name", required = true) String name, HttpServletResponse response) {
        log.info("start office online service [get] file contents info, file name: {}", name);
        AttachmentDTO attachmentDTO = attachmentService.findByFileKey(name);
        FileStorageResult fileStorageResult = storageService.getFile(attachmentDTO.getAttachmentPath());
        InputStream fis = fileStorageResult.getInputStream();
        OutputStream toClient = null;
        try {
            String filename = attachmentDTO.getOriginName();
            // 以流的形式下载文件
            fis = new BufferedInputStream(fis);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" +
                    new String(filename.getBytes("utf-8"), "ISO-8859-1"));
            response.addHeader("Content-Length", "" + fileStorageResult.getFileSize());
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            log.info("end office online service [get] file contents info, file name: {}", name);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
        } finally {
            try {
                fis.close();
                toClient.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存更新文件
     * @param name
     * @param content
     */
    @ApiOperation(value = "修改文件")
    @PostMapping("/files/{name}/contents")
    public void postFile(@PathVariable(name = "name", required = true) String name, @RequestBody byte[] content) {
        log.info("start office online service [update] file contents info, file name: {}", name);
        // 文件的路径
//        String path = filePath + name;
//        File file = new File(path);
//        FileOutputStream fop = null;
//        try {
//            if (!file.exists()) {
//                file.createNewFile();//构建文件
//            }
//            fop = new FileOutputStream(file);
//            fop.write(content);
//            fop.flush();
//            // TODO 这里可以做文件流缓存优化
//            log.info("end office online service [update] file contents info, file name: {}", name);
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//            e.printStackTrace();
//        } finally {
//            try {
//                fop.close();
//            } catch (IOException e) {
//                log.error(e.getMessage(), e);
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * 获取文件信息
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @ApiOperation(value = "获取文件信息")
    @GetMapping("/files/{name}")
    public void getFileInfo(@PathVariable(value = "name", required = true) String name,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        String uri = request.getRequestURI();
        WopiFileVO info = WopiFileVO.builder().build();
        try {
            AttachmentDTO attachmentDTO = attachmentService.findByFileKey(name);
            AssertValid.notNull(attachmentDTO, "未找到匹配查询条件的文件");
            // 获取文件名, 防止中文文件名乱码
            FileStorageResult fileStorageResult = storageService.getFile(attachmentDTO.getAttachmentPath());
            if (fileStorageResult.isSuccess()) {
                InputStream fis = fileStorageResult.getInputStream();
                // 取得文件名
                info.setBaseFileName(attachmentDTO.getOriginName());
                info.setSize(fileStorageResult.getFileSize());
                info.setOwnerId("admin");
                info.setVersion(fileStorageResult.getLastModified());
                info.setSha256(getHash256(fis));
                info.setAllowExternalMarketplace(true);
                info.setUserCanWrite(true);
                info.setWebEditingDisabled(false);
                info.setSupportsUpdate(true);
                info.setSupportsLocks(true);
            }
            ObjectMapper mapper = new ObjectMapper();
            String Json =  mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(Json);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /**
     * 获取文件的SHA-256值
     * @param inputStream
     * @return
     */
    public static String getHash256(InputStream inputStream) {
        String value = "";
        // 获取hash值
        try {
            byte[] buffer = new byte[1024];
            int numRead;
            //如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
            MessageDigest complete = MessageDigest.getInstance("SHA-256");
            do {
                //从文件读到buffer
                numRead = inputStream.read(buffer);
                if (numRead > 0) {
                    //用读到的字节进行MD5的计算，第二个参数是偏移量
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);
            inputStream.close();
            value = new String(Base64.encodeBase64(complete.digest()));
        } catch (NoSuchAlgorithmException | IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return value;
    }

}

