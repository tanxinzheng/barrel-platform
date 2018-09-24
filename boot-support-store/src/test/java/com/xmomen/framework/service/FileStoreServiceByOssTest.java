package com.xmomen.framework.service;

import com.xmomen.module.fss.FssConfigProperties;
import com.xmomen.module.fss.model.FileStorageInfo;
import com.xmomen.module.fss.model.FileStorageResult;
import com.xmomen.module.fss.service.FileStoreServiceByOss;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by tanxinzheng on 2018/7/23.
 */
public class FileStoreServiceByOssTest {

    private FileStoreServiceByOss fileOperation;

    private InputStream inputStream;
    private FileStorageResult result;
    @Before
    public void setUp() throws Exception {
        FssConfigProperties fssConfigProperties = new FssConfigProperties();
        fssConfigProperties.setAccessKeyId("LTAIQ9tAnQKgixOa");
        fssConfigProperties.setAccessKeySecret("SvUzBFGETJ3k9DUY0krXEKLYEpOsFF");
        fssConfigProperties.setBucketName("xmomen-test");
        fssConfigProperties.setEndpoint("oss-cn-hangzhou.aliyuncs.com");
        fileOperation = new FileStoreServiceByOss(fssConfigProperties);
        String demoFile = "/Users/jeng/xmomen-repo/webapp/overlays-simple-template-webapp/src/test/resources/demo/logo.png";
        inputStream = new FileInputStream(new File(demoFile));
    }

    @After
    public void tearDown() throws Exception {
        if(result != null && result.getStoragePath() != null){
            fileOperation.deleteFile(result.getStoragePath());
        }
    }

    @Test
    public void existFile() throws Exception {
        FileStorageInfo fileStorageInfo = new FileStorageInfo("png", inputStream);
        FileStorageResult result = fileOperation.newFile(fileStorageInfo);
        Assert.assertTrue(result.isSuccess());
        Boolean isSuccess = fileOperation.existFile(result.getStoragePath());
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void getFile() throws Exception {
        FileStorageInfo fileStorageInfo = new FileStorageInfo("png", inputStream);
        FileStorageResult result = fileOperation.newFile(fileStorageInfo);
        Assert.assertTrue(result.isSuccess());
        result = fileOperation.getFile(result.getStoragePath());
        Assert.assertTrue(result.getFileSize() > 0);
    }

    @Test
    public void newFile() throws Exception {
        FileStorageInfo fileStorageInfo = new FileStorageInfo("png", inputStream);
        FileStorageResult result = fileOperation.newFile(fileStorageInfo);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void deleteFile() throws Exception {
    }

}