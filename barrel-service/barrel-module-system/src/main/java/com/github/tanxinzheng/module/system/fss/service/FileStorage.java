package com.github.tanxinzheng.module.system.fss.service;

import com.github.tanxinzheng.module.system.fss.model.FileStorageInfo;
import com.github.tanxinzheng.module.system.fss.model.FileStorageResult;

import java.io.IOException;


public interface FileStorage {

    /**
     * 是否存在文件
     * @param key
     * @return
     */
    public boolean existFile(String key);

    /**
     * 根据路径获取文件流
     * @param key
     * @return
     */
    public FileStorageResult getFile(String key);

    /**
     * 保存文件
     * @param fileStorageInfo
     * @return
     */
    public FileStorageResult newFile(FileStorageInfo fileStorageInfo);

    /**
     * 根据fileKey删除文件
     * @param key
     * @return
     */
    public boolean deleteFile(String key);

}
