package com.github.tanxinzheng.module.fss.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tanxinzheng on 2018/7/22.
 */
@Slf4j
@Data
public class FileStorageInfo {

    private String fileName;
    private byte[] content;
    private String fileExt;
    private long fileSize;
    private String contentType;
    private FileStorageNameValuePair[] mateList;

    public FileStorageInfo(MultipartFile multipartFile) {
        try {
            this.content = multipartFile.getBytes();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        String originalFileName = multipartFile.getOriginalFilename();
        this.fileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        this.fileName = multipartFile.getName();
        this.fileSize = multipartFile.getSize();
        this.contentType = multipartFile.getContentType();
    }

    public FileStorageInfo(String fileName, String fileExt, InputStream inputStream) throws IOException {
        this.fileName = fileName;
        this.fileExt = fileExt;
        if(inputStream != null){
            int len1 = inputStream.available();
            content = new byte[len1];
            inputStream.read(content);
            inputStream.close();
        }
    }

    public InputStream getInputStream(){
        if(content == null){
            return null;
        }
        return new ByteArrayInputStream(content);
    }

    public String getFullPath(){
        return fileName + "." + fileExt;
    }

    @Data
    public static class FileStorageNameValuePair {
        private String name;
        private String value;
    }

}
