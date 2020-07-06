package com.github.tanxinzheng;

import com.github.tanxinzheng.module.system.dictionary.model.DemoModel;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Created by tanxinzheng on 2018/11/15.
 */
@RestController
public class DemoController {

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public List<DemoModel> getDictionaryList(){
        DemoModel demoModel = new DemoModel();
        demoModel.setId(UUID.randomUUID().toString());
        demoModel.setSex("W");
        demoModel.setUserId("1");
        demoModel.setDisable(Boolean.FALSE.toString());
        DemoModel demoModel2 = new DemoModel();
        demoModel2.setId(UUID.randomUUID().toString());
        demoModel2.setSex("M");
        demoModel2.setUserId("2");
        demoModel2.setDisable(Boolean.TRUE.toString());
        return Lists.newArrayList(demoModel, demoModel2);
    }
}
