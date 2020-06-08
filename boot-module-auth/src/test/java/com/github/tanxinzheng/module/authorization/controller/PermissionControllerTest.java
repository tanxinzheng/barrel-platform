package com.github.tanxinzheng.module.authorization.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.tanxinzheng.framework.model.RestResponse;
import com.github.tanxinzheng.module.authorization.model.PermissionModel;
import com.github.tanxinzheng.test.TestAppController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PermissionControllerTest extends TestAppController {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void syncAll() throws Exception {
        ResultActions actions = mockMvc.perform(get("/permission/sync")
                .header(AUTHORIZATION, createToken())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONObject jsonArray = JSONObject.parseObject(resultJson);
        Assert.assertNotNull("查询用户组，测试不通过", jsonArray);
    }

    @Test
    public void getPermissionList() throws Exception {
        ResultActions actions = mockMvc.perform(get("/permission")
                .header(AUTHORIZATION, createToken())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        RestResponse<List<PermissionModel>> restResponse = parseRestResponse(resultJson);
        Assert.assertNotNull(restResponse);
        Assert.assertTrue(restResponse.getData().size() > 0);
    }

    @Test
    public void getPermissionById() {
    }

    @Test
    public void createPermission() {
    }

    @Test
    public void updatePermission() {
    }

    @Test
    public void deletePermission() {
    }

    @Test
    public void deletePermissions() {
    }

    @Test
    public void downloadTemplate() {
    }

    @Test
    public void exportExcel() {
    }

    @Test
    public void importExcel() {
    }
}