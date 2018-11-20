package com.github.tanxinzheng.module.dictionary.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.tanxinzheng.module.dictionary.model.Dictionary;
import com.github.tanxinzheng.module.dictionary.model.DictionaryModel;
import com.github.tanxinzheng.module.dictionary.service.DictionaryService;
import com.github.tanxinzheng.test.TestAppController;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tanxinzheng on 2018/11/20.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DictionaryControllerTest extends TestAppController {

    String GROUP_CODE = "TEST_GROUP";

    private static DictionaryModel dictionaryModel;

    @Autowired
    DictionaryService dictionaryService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void setDown() throws Exception {
        deleteById();

    }

    public void deleteById() throws Exception {
        ResultActions actions = mockMvc.perform(delete("/dictionary/{id}", dictionaryModel.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    public String create() throws Exception {
        DictionaryModel model = new DictionaryModel();
        model.setGroupCode(GROUP_CODE);
        model.setGroupName("测试分组");
        model.setDictionaryCode("TEST_02");
        model.setDictionaryName("第一测试小组");
        ResultActions actions = mockMvc.perform(post("/dictionary")
                .content(JSONObject.toJSONString(model))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        dictionaryModel = JSONObject.parseObject(resultJson, DictionaryModel.class);
        return resultJson;
    }

    @Test
    public void test0createDictionary() throws Exception {
        String resultJson = create();
        JSONObject resultObj = JSONObject.parseObject(resultJson);
        Assert.notEmpty(resultObj, "新增数据字典，测试不通过");
        Assert.notNull(resultObj.get("id"), "新增数据字典，测试不通过");
    }

    @Test
    public void test1getDictionaryList() throws Exception {
        ResultActions actions = mockMvc.perform(get("/dictionary")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.parseArray(resultJson);
        Assert.notEmpty(jsonArray, "查询数据字典列表，测试不通过");
    }

    @Test
    public void test2getDictionaryById() throws Exception {
        create();
        ResultActions actions = mockMvc.perform(get("/dictionary/{id}", dictionaryModel.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONObject resultObj = JSONObject.parseObject(resultJson);
        Assert.notEmpty(resultObj, "查询数据字典，测试不通过");
        Assert.notNull(resultObj.get("id"), "查询数据字典，测试不通过");
    }

    @Test
    public void test3updateDictionary() throws Exception {
        create();
        DictionaryModel model = dictionaryModel;
        String name = "第一测试小组" + new Date().getTime();
        model.setDictionaryName(name);
        ResultActions actions = mockMvc.perform(put("/dictionary/{id}", model.getId())
                .content(JSONObject.toJSONString(model))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONObject resultObj = JSONObject.parseObject(resultJson);
        Assert.notEmpty(resultObj, "新增数据字典，测试不通过");
        Assert.isTrue(resultObj.get("dictionaryName").equals(name), "新增数据字典，测试不通过");
    }

    @Test
    public void test6downloadTemplate() throws Exception {
    }

    @Test
    public void test7exportDictionaries() throws Exception {
    }

    @Test
    public void test8importDictionaries() throws Exception {
    }


    @Test
    public void test9deleteDictionary() throws Exception {
    }

    @Test
    public void test9deleteDictionaries() throws Exception {

    }

}