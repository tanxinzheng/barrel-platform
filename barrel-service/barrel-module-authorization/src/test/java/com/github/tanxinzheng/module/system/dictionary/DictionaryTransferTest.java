package com.github.tanxinzheng.module.system.dictionary;

import com.alibaba.fastjson.JSONArray;
import com.github.tanxinzheng.AppTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DictionaryTransferTest extends AppTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testDictionaryTransfer() throws Exception {
        ResultActions actions = mockMvc.perform(get("/demo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.parseArray(resultJson);
        Assert.notEmpty(jsonArray, "测试不通过");
        Assert.isTrue(jsonArray.getJSONObject(0).get("sexDesc").equals("女"), "测试不通过");
        Assert.isTrue(jsonArray.getJSONObject(1).get("sexDesc").equals("男"), "测试不通过");
        Assert.isTrue(jsonArray.getJSONObject(0).get("disableName").equals("启用"), "测试不通过");
        Assert.isTrue(jsonArray.getJSONObject(1).get("disableName").equals("禁用"), "测试不通过");
        Assert.isTrue(jsonArray.getJSONObject(0).getJSONObject("userIdDetail").get("userName").equals("管理员"), "测试不通过");
    }
}
