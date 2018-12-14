package com.github.tanxinzheng.module.fss;

import com.github.tanxinzheng.module.fss.model.FileStorageInfo;
import com.github.tanxinzheng.module.fss.model.FileStorageResult;
import com.github.tanxinzheng.module.fss.service.FileStoreService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by tanxinzheng on 2018/7/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FSSStartApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class FileStoreServiceByOssTest {

    @Autowired
    private FileStoreService fileOperation;

    private InputStream inputStream;
    private FileStorageInfo fileStorageInfo;
    private FileStorageResult result;
    @Before
    public void setUp() throws Exception {
        String demoFile = this.getClass().getClassLoader().getResource("demo/test.json").getPath();
        inputStream = new FileInputStream(new File(demoFile));
        fileStorageInfo = new FileStorageInfo("demo/test.1", "json", inputStream);
    }

    @After
    public void tearDown() throws Exception {
        if(result != null && result.getStoragePath() != null){
            fileOperation.deleteFile(result.getStoragePath());
        }
    }

    @Test
    public void existFile() throws Exception {
        FileStorageResult result = fileOperation.newFile(fileStorageInfo);
        Assert.assertTrue(result.isSuccess());
        Boolean isSuccess = fileOperation.existFile(result.getStoragePath());
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void getFile() throws Exception {
        FileStorageResult result = fileOperation.newFile(fileStorageInfo);
        Assert.assertTrue(result.isSuccess());
        result = fileOperation.getFile(result.getStoragePath());
        Assert.assertTrue(result.getFileSize() > 0);
    }

    @Test
    public void newFile() throws Exception {
        FileStorageResult result = fileOperation.newFile(fileStorageInfo);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void deleteFile() throws Exception {
    }

}