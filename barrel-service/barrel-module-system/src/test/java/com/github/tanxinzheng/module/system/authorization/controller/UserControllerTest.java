package com.github.tanxinzheng.module.system.authorization.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.tanxinzheng.AppTest;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.module.system.authorization.domain.dto.UserDTO;
import com.github.tanxinzheng.module.system.authorization.domain.vo.UserVO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Date 2020/8/7
 */
public class UserControllerTest  extends AppTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findPage() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void create() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setNickname("测试人员");
        userDTO.setUsername("tester");
        userDTO.setPassword("123456");
        userDTO.setEmail("test@xmomen.com");
        userDTO.setPhoneNumber("12345678");
        ResultActions actions = mockMvc.perform(post("/user")
                .content(JSONObject.toJSONString(userDTO))
                .header(AUTHORIZATION, createToken())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        Result<UserVO> result = JSONObject.parseObject(resultJson, new TypeReference<Result<UserVO>>(){});
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getData().getId());
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void batchDelete() {
    }

    @Test
    public void createGroupPermission() {
    }

    @Test
    public void deleteRolePermission() {
    }

    @Test
    public void findRolePermission() {
    }
}