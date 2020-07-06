package com.github.tanxinzheng.module.system.fss.service;

import com.github.tanxinzheng.module.system.fss.model.FileStorageInfo;
import com.github.tanxinzheng.module.system.fss.model.FileStorageResult;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * 提供存储服务类，所有存储服务均由该类对外提供
 */
public class StorageService {
    private String active;
    private Storage storage;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param fileName      文件索引名
     */
    public FileStorageResult store(InputStream inputStream, long contentLength, String contentType, String fileName) {
        String key = generateKey(fileName);
        storage.store(inputStream, contentLength, contentType, key);

        String url = generateUrl(key);
        FileStorageResult storageInfo = new FileStorageResult();
        storageInfo.setFileName(fileName);
        storageInfo.setFileSize((int) contentLength);
        storageInfo.setContentType(contentType);
        storageInfo.setKey(key);
        storageInfo.setUrl(url);
//        litemallStorageService.add(storageInfo);

        return storageInfo;
    }

    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);

        String key = null;
        FileStorageInfo storageInfo = null;

        do {
            key = UUID.randomUUID() + suffix;
//            storageInfo = litemallStorageService.findByKey(key);
        }
        while (storageInfo != null);

        return key;
    }

    public Stream<Path> loadAll() {
        return storage.loadAll();
    }

    public Path load(String keyName) {
        return storage.load(keyName);
    }

    public Resource loadAsResource(String keyName) {
        return storage.loadAsResource(keyName);
    }

    public void delete(String keyName) {
        storage.delete(keyName);
    }

    private String generateUrl(String keyName) {
        return storage.generateUrl(keyName);
    }
}
