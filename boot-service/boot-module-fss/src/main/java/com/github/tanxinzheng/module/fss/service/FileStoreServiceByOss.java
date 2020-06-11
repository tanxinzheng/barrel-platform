package com.github.tanxinzheng.module.fss.service;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.github.tanxinzheng.module.fss.FssConfigProperties;
import com.github.tanxinzheng.module.fss.model.FileStorageInfo;
import com.github.tanxinzheng.module.fss.model.FileStorageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 注：图片等静态资源存储在公共读类的bucket，而私密性文件应存储在私有类的bucket
 * Created by Jeng on 15/6/24.
 */
@Slf4j
public class FileStoreServiceByOss implements FileStoreService {


    private static OSSClient client;

    private FssConfigProperties fssConfigProperties;

    public FileStoreServiceByOss(FssConfigProperties fssConfigProperties) {
        this.fssConfigProperties = fssConfigProperties;
    }

    private void reconnectionOSSClient() {
        createOSSClientConnection();
    }


    /**
     * 创建ossClient连接
     */
    private void createOSSClientConnection() {
        ClientConfiguration conf = new ClientConfiguration();
        conf.setMaxConnections(3500);
        conf.setConnectionTimeout(3500);
        conf.setMaxErrorRetry(6);
        if(client == null){
            client = new OSSClient(fssConfigProperties.getEndpoint(),
                    fssConfigProperties.getAccessKeyId(),
                    fssConfigProperties.getAccessKeySecret(), conf);
        }
    }

    public OSSClient getClient(){
        if(client == null) {
            createOSSClientConnection();
        }
        return client;
    }

    @Override
    public boolean existFile(String filePath) {
        if(StringUtils.isEmpty(filePath)){
            return false;
        }
        reconnectionOSSClient();
        try{
            OSSObject ossObject = getClient().getObject(fssConfigProperties.getBucketName(), filePath);
            if(ossObject != null){
                return true;
            }
        }catch (OSSException e){
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public FileStorageResult getFile(String filePath) {
        try {
            createOSSClientConnection();
            OSSObject ossObject = getClient().getObject(fssConfigProperties.getBucketName(), filePath);
            return FileStorageResult.SUCCESS(filePath, ossObject.getObjectContent());
        } catch (Exception e) {
            log.error("OSSClient getObject fail, filePath: {}, error message: {}", filePath, e.getMessage(), e);
        }
        return FileStorageResult.FAIL();
    }

    @Override
    public FileStorageResult newFile(FileStorageInfo fileStorageInfo) {
        try {
            ObjectMetadata meta = new ObjectMetadata();
            if(ArrayUtils.isNotEmpty(fileStorageInfo.getMateList())){
                for (FileStorageInfo.FileStorageNameValuePair fileStorageNameValuePair : fileStorageInfo.getMateList()) {
                    meta.addUserMetadata(fileStorageNameValuePair.getName(), fileStorageNameValuePair.getValue());
                }
            }
            meta.setContentLength(fileStorageInfo.getContent().length);
            PutObjectResult result = getClient().putObject(fssConfigProperties.getBucketName(),
                    fileStorageInfo.getFullPath(),
                    fileStorageInfo.getInputStream(), meta);
            return FileStorageResult.SUCCESS(fileStorageInfo.getFullPath(), fileStorageInfo.getInputStream());
        } catch (Exception e) {
            log.error("OSSClient delete file fail, file path: {}, message: {}", fileStorageInfo.getFullPath(), e.getMessage(), e);
        }
        return FileStorageResult.FAIL();
    }

    /**
     * 删除文件
     * @param filePathAndName
     * @return
     */
    @Override
    public boolean deleteFile(String filePathAndName) {
        log.debug("OSSClient delete file, file path: {}", filePathAndName);
        try {
            client.deleteObject(fssConfigProperties.getBucketName(), filePathAndName);
            return Boolean.TRUE;
        } catch (Exception e){
            log.error("OSSClient delete file fail, file path: {}, message: {}", filePathAndName, e.getMessage(), e);
        }
        return Boolean.FALSE;
    }


}
