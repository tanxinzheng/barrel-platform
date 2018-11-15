package com.github.tanxinzheng.module.dictionary.model;

import lombok.Data;

/**
 * Created by tanxinzheng on 2018/11/15.
 */
@Data
public class DemoModel {

    private String id;
//    @DictionaryInterpreter(index = "USER", fieldName = "account")
    private String userId;
//    @DictionaryInterpreter(index = "SEX")
    private String sex;
//    @DictionaryInterpreter(index = "DISABLE", fieldName = "disableName")
    private String disable;
}
