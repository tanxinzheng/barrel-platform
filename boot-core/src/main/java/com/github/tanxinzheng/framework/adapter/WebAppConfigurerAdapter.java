package com.github.tanxinzheng.framework.adapter;

import com.github.tanxinzheng.framework.web.authentication.CurrentAccountService;

/**
 * Created by tanxinzheng on 2018/9/11.
 */
public abstract class WebAppConfigurerAdapter {


    public abstract CurrentAccountService getCurrentAccountService();
}
