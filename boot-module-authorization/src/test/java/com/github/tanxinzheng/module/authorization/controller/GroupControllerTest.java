package com.github.tanxinzheng.module.authorization.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.tanxinzheng.test.TestAppController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tanxinzheng on 2018/12/2.
 */
public class GroupControllerTest extends TestAppController {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getGroupList() throws Exception {
        ResultActions actions = mockMvc.perform(get("/group")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.parseArray(resultJson);
        Assert.assertNotNull("查询用户组，测试不通过", jsonArray);
        Assert.assertTrue("查询用户组，测试不通过", jsonArray.size() > 0);
    }

    @Test
    public void getGroupById() throws Exception {
    }

    @Test
    public void createGroup() throws Exception {
    }

    @Test
    public void updateGroup() throws Exception {
    }

    @Test
    public void deleteGroup() throws Exception {
    }

    @Test
    public void deleteGroups() throws Exception {
    }

    @Test
    public void createGroupPermission() throws Exception {
    }

    @Test
    public void deleteGroupPermission() throws Exception {
    }

    @Test
    public void findPermissionByGroup() throws Exception {
    }

    @Test
    public void findUserByGroup() throws Exception {
    }

    @Test
    public void bindUser2Group() throws Exception {
    }

    @Test
    public void unbindUser2Group() throws Exception {
    }

    @Test
    public void findPermissionByGroup1() throws Exception {
    }

}