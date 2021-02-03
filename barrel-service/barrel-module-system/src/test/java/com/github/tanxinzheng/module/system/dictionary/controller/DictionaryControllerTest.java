package com.github.tanxinzheng.module.system.dictionary.controller;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.tanxinzheng.AppTest;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.module.system.dictionary.domain.dto.DictionaryDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tanxinzheng on 2018/11/20.
 */
public class DictionaryControllerTest extends AppTest {

    String GROUP_CODE = "TEST_GROUP";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    private DictionaryDTO dictionaryModel;
//
//    @After
//    public void setDown() throws Exception {
//        deleteById();
//    }
//
//    protected String createToken(){
//        return "Bearer XMO";
//    }
//
//    private void deleteById() throws Exception {
//        if(dictionaryModel == null){
//            return;
//        }
//        ResultActions actions = mockMvc.perform(delete("/dictionary/{id}", dictionaryModel.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
    private Result<DictionaryDTO> create() throws Exception {
        DictionaryDTO model = new DictionaryDTO();
        model.setGroupCode(GROUP_CODE);
        model.setGroupName("测试分组");
        model.setDictionaryCode("TEST_0"+ RandomUtil.randomNumber());
        model.setDictionaryName("第一测试小组");
        model.setSort(-10);
        ResultActions actions = mockMvc.perform(post("/dictionary")
//                .header(AUTHORIZATION, createToken())
                .content(JSONObject.toJSONString(model))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        Result<DictionaryDTO> result = JSONObject.parseObject(resultJson, new TypeReference<Result<DictionaryDTO>>(){});
        dictionaryModel = result.getData();
        return result;
    }

    @Test
    public void test0createDictionary() throws Exception {
        Result<DictionaryDTO> resultJson = create();
        Assert.assertTrue("新增数据字典，测试不通过", resultJson.isSuccess());
    }

//    @Test
//    public void test1getDictionaryList() throws Exception {
//        ResultActions actions = mockMvc.perform(get("/dictionary")
//                .header(AUTHORIZATION, createToken())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//        String resultJson = actions.andReturn().getResponse().getContentAsString();
//        Result<List<DictionaryDTO>> result = JSONObject.parseObject(resultJson, new TypeReference<Result<List<DictionaryDTO>>>(){});
//        Assert.assertNotNull("查询数据字典列表，测试不通过", result);
//        Assert.assertTrue("查询数据字典列表，测试不通过", result.getData().size() > 0);
//    }
//
//    @Test
//    public void test2getDictionaryById() throws Exception {
//        create();
//        ResultActions actions = mockMvc.perform(get("/dictionary/{id}", dictionaryModel.getId())
//                .header(AUTHORIZATION, createToken())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//        String resultJson = actions.andReturn().getResponse().getContentAsString();
//        JSONObject resultObj = JSONObject.parseObject(resultJson);
//        Assert.assertNotNull("查询数据字典，测试不通过", resultObj);
//        Assert.assertNotNull("查询数据字典，测试不通过",resultObj.get("id"));
//    }
//
    @Test
    public void test3updateDictionary() throws Exception {
        create();
        DictionaryDTO model = dictionaryModel;
        String name = "第一测试小组" + new Date().getTime();
        model.setDictionaryName(name);
        ResultActions actions = mockMvc.perform(put("/dictionary/{id}", model.getId())
//                .header(AUTHORIZATION, createToken())
                .content(JSONObject.toJSONString(model))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        Result<Boolean> resultObj = JSONObject.parseObject(resultJson, new TypeReference<Result<Boolean>>(){});
        Assert.assertTrue("新增数据字典，测试不通过", resultObj.isSuccess());
    }

    @Test
    public void test6downloadTemplate() throws Exception {
//        ResultActions actions = mockMvc.perform(get("/dictionary/template")
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .accept(MediaType.APPLICATION_OCTET_STREAM_VALUE))
//                .andDo(print())
//                .andExpect(status().isOk());
//        actions.andDo(new ResultHandler() {
//            @Override
//            public void handle(MvcResult result) throws Exception {
//                result.getResponse().setCharacterEncoding("UTF-8");
//                MockHttpServletResponse contentRespon = result.getResponse();
//                InputStream contentInStream = new ByteArrayInputStream(
//                        contentRespon.getContentAsByteArray());
//                FileUtils.copyInputStreamToFile(contentInStream, new File("./src/test/resource/ddd.xls"));
//                Assert.assertEquals("application/ms-excel", contentRespon.getContentType());
//            }
//        });
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