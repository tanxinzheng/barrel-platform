package com.github.tanxinzheng.module.system.attachment.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.tanxinzheng.AppTest;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.module.system.attachment.domain.dto.AttachmentDTO;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.ResultActions;

import java.io.File;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FileUploadDownloadControllerTest extends AppTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void upload() throws Exception {
        // 上传
        String filePath = "E:\\xmomen-repo\\webapp\\barrel-parent\\barrel-service\\barrel-module-system\\src\\test\\resources\\demo\\测试Excel文件预览.xlsx";

        ResultActions actions = mockMvc.perform(multipart("/file/upload")
                .file(new MockMultipartFile("multipartFile", "测试Excel文件预览",",multipart/form-data", FileUtils.readFileToByteArray(new File(filePath))))
//                .file("relationId", "JUNIT".getBytes())
//                .file("relationType", "JUNIT".getBytes())
//                .file("owner", "PUBLIC".getBytes())
                .header(AUTHORIZATION, createToken())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        Result<AttachmentDTO> jsonArray = JSONObject.parseObject(resultJson, new TypeReference<Result<AttachmentDTO>>(){});
        Assert.assertTrue("上传文件失败", jsonArray.isSuccess());
    }

    @Test
    public void download() {
    }

    @Test
    public void downloadZip() {
    }

    @Test
    public void downloadTempFile() {
    }
}