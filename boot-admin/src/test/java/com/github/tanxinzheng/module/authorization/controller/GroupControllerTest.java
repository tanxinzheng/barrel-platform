package com.github.tanxinzheng.module.authorization.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.tanxinzheng.AppTest;
import com.github.tanxinzheng.framework.web.model.RestResponse;
import com.github.tanxinzheng.module.authorization.model.GroupModel;
import com.github.tanxinzheng.module.authorization.model.GroupPermissionModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tanxinzheng on 2018/12/2.
 */
public class GroupControllerTest extends AppTest {
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
                .header(AUTHORIZATION, createToken())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        RestResponse<List<GroupModel>> restResponse = parseRestResponse(resultJson);
        Assert.assertNotNull("查询用户组，测试不通过", restResponse);
        Assert.assertTrue("查询用户组，测试不通过", restResponse.getData().size() > 0);
    }

    @Test
    public void getGroupById() throws Exception {
    }

    @Test
    public void createGroup() throws Exception {
        GroupModel groupModel = new GroupModel();
        groupModel.setGroupCode("ROLE_TEST");
        groupModel.setGroupName("测试组");
        groupModel.setDescription("测试组专用角色");
        ResultActions actions = mockMvc.perform(post("/group")
                .header(AUTHORIZATION, createToken())
                .content(JSONObject.toJSONString(groupModel))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        RestResponse<GroupModel> restResponse = parseRestResponse(resultJson);
        Assert.assertNotNull(restResponse);
        Assert.assertNotNull(restResponse.getData().getId());
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
        GroupPermissionModel groupPermissionModel = new GroupPermissionModel();
        groupPermissionModel.setPermissionId("98a60980f2864a49972ca7c1379d18ce");
        groupPermissionModel.setGroupId("556912fca5f211e89ca40242ac110003");
        ResultActions actions = mockMvc.perform(post("/group/permission")
                .header(AUTHORIZATION, createToken())
                .content(JSONObject.toJSONString(groupPermissionModel))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        RestResponse<GroupPermissionModel> restResponse = JSONObject.parseObject(resultJson, new TypeReference<RestResponse<GroupPermissionModel>>(){});
        Assert.assertNotNull(restResponse);
        Assert.assertNotNull(restResponse.getData().getId());
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