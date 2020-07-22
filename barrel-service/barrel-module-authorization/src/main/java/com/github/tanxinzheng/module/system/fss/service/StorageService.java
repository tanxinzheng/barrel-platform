package com.github.tanxinzheng.module.system.fss.service;

import com.github.tanxinzheng.module.system.fss.model.FileStorageInfo;
import com.github.tanxinzheng.module.system.fss.model.FileStorageResult;

/**
 * 提供存储服务类，所有存储服务均由该类对外提供
 */
public class StorageService implements FileStorage {

    private String active;
    private FileStorage storage;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public FileStorage getStorage() {
        return storage;
    }

    public void setStorage(FileStorage storage) {
        this.storage = storage;
    }

    /**
     * 是否存在文件
     *
     * @param key
     * @return
     */
    @Override
    public boolean existFile(String key) {
        return storage.existFile(key);
    }

    /**
     * 根据路径获取文件流
     *
     * @param key
     * @return
     */
    @Override
    public FileStorageResult getFile(String key) {
        return storage.getFile(key);
    }

    /**
     * 保存文件
     *
     * @param fileStorageInfo
     * @return
     */
    @Override
    public FileStorageResult newFile(FileStorageInfo fileStorageInfo) {
        return storage.newFile(fileStorageInfo);
    }

    /**
     * 根据fileKey删除文件
     *
     * @param key
     * @return
     */
    @Override
    public boolean deleteFile(String key) {
        return storage.deleteFile(key);
    }
}
