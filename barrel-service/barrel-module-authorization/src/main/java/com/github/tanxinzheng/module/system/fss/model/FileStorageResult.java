package com.github.tanxinzheng.module.system.fss.model;

import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tanxinzheng on 2018/7/22.
 */
@Data
public class FileStorageResult {

    private String key;
    private String contentType;
    private byte[] content;
    private String fileExt;
    private boolean success;
    private String message;
    private String fileName;
    private long fileSize;
    private String storagePath;
    private String url;

    public FileStorageResult() {
    }

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
                int len1 = inputStream.available();
                byte[] bytes = new byte[len1];
                inputStream.read(bytes);
                inputStream.close();
                result.setContent(bytes);
                result.setFileSize(bytes.length);
            }
        } catch (IOException e) {

        }
        return result;
    }

    public static FileStorageResult FAIL() {
        FileStorageResult fileStorageResult = new FileStorageResult();
        fileStorageResult.setMessage("FSSClient操作文件失败");
        return fileStorageResult;
    }

    public InputStream getInputStream(){
        return new ByteArrayInputStream(content);
    }
}
