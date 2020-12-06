package com.github.tanxinzheng.module.system.fss.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tanxinzheng on 2018/7/22.
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileStorageResult {

    private String key;
    private String contentType;
    private byte[] content;
    private String fileExt;
    private boolean success;
    private Long lastModified;
    private String message;
    private String fileName;
    private long fileSize;
    private String storagePath;
    private String url;

    public static FileStorageResult SUCCESS(String storagePath) {
        FileStorageResult fileStorageResult = new FileStorageResult();
        String name = storagePath.substring(0, storagePath.lastIndexOf("."));
        String prefix = storagePath.substring(storagePath.lastIndexOf(".")+1);
        fileStorageResult.setFileName(name);
        fileStorageResult.setFileExt(prefix);
        fileStorageResult.setStoragePath(storagePath);
        fileStorageResult.setSuccess(Boolean.TRUE);
        fileStorageResult.setMessage("文件上传成功");
        if(storagePath != null){
            fileStorageResult.setFileExt(storagePath.substring(storagePath.lastIndexOf(".") + 1));
        }
        return fileStorageResult;
    }

    public static FileStorageResult SUCCESS(String storagePath, InputStream inputStream) {
        FileStorageResult result = FileStorageResult.SUCCESS(storagePath);
        try {
            if(inputStream != null){
                result.setContent(IOUtils.toByteArray(inputStream));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    public static FileStorageResult FAIL() {
        return FAIL("FSSClient操作文件失败");
    }

    public static FileStorageResult FAIL(String msg) {
        FileStorageResult fileStorageResult = new FileStorageResult();
        fileStorageResult.setMessage(msg);
        return fileStorageResult;
    }

    public InputStream getInputStream(){
        return new ByteArrayInputStream(content);
    }
}
