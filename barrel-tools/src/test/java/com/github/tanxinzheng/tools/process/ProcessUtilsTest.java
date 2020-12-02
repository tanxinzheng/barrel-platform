package com.github.tanxinzheng.tools.process;

import com.alibaba.fastjson.JSONObject;
import com.github.tanxinzheng.tools.process.domain.ProcessVO;
import com.google.common.collect.Lists;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApp.class})
public class ProcessUtilsTest {

    @Resource
    ProcessUtils processUtils;

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void create() throws InterruptedException {
        List<String> list = Lists.newArrayList("A1", "A2", "A3");
        ProcessVO processVO = processUtils.create(list);
        Thread.sleep(1000);
        processUtils.doneSubTask(processVO.getEventKey(), "A3");
        processVO = processUtils.getProcessInfo(processVO.getEventKey());
        System.out.println(JSONObject.toJSONString(processVO));
        Thread.sleep(1000);
        processUtils.doneSubTask(processVO.getEventKey(), "A1");
        processVO = processUtils.getProcessInfo(processVO.getEventKey());
        System.out.println(JSONObject.toJSONString(processVO));
        Thread.sleep(1000);
        processUtils.doneSubTask(processVO.getEventKey(), "A2");
        processVO = processUtils.getProcessInfo(processVO.getEventKey());
        System.out.println(JSONObject.toJSONString(processVO));
    }
}