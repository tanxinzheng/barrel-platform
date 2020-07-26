package com.github.tanxinzheng.module.system.fss.adapter;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.github.tanxinzheng.module.system.fss.model.FileStorageInfo;
import com.github.tanxinzheng.module.system.fss.model.FileStorageResult;
import com.github.tanxinzheng.module.system.fss.service.FileStorage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 注：图片等静态资源存储在公共读类的bucket，而私密性文件应存储在私有类的bucket
 * Created by Jeng on 15/6/24.
 */
@Slf4j
public class AliyunStorage implements FileStorage {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    private static OSSClient client;

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
            client = new OSSClient(bucketName, accessKeyId, accessKeySecret, conf);
        }
    }

    public OSSClient getClient(){
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    @Override
    public boolean existFile(String filePath) {
        if(StringUtils.isEmpty(filePath)){
            return false;
        }
        reconnectionOSSClient();
        try{
            OSSObject ossObject = getClient().getObject(getBucketName(), filePath);
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
            OSSObject ossObject = getClient().getObject(getBucketName(), filePath);
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
            PutObjectResult result = getClient().putObject(getBucketName(),
                    fileStorageInfo.getFullPath(),
                    fileStorageInfo.getInputStream(), meta);
            if(null == result){
                return FileStorageResult.FAIL();
            }
            return FileStorageResult.SUCCESS(fileStorageInfo.getFullPath(), fileStorageInfo.getInputStream());
        } catch (Exception e) {
            log.error("OSSClient delete file fail, file storage: {}, message: {}", fileStorageInfo, e.getMessage(), e);
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
            client.deleteObject(getBucketName(), filePathAndName);
            return Boolean.TRUE;
        } catch (Exception e){
            log.error("OSSClient delete file fail, file path: {}, message: {}", filePathAndName, e.getMessage(), e);
        }
        return Boolean.FALSE;
    }


}
