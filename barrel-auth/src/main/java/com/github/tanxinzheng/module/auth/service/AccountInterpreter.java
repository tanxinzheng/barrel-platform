package com.github.tanxinzheng.module.auth.service;

import com.github.tanxinzheng.framework.web.dictionary.AccountInterpreterService;
import com.github.tanxinzheng.module.account.model.AccountModel;
import com.github.tanxinzheng.module.account.service.AccountService;
import com.github.tanxinzheng.module.system.authorization.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountInterpreter implements AccountInterpreterService {

    @Resource
    AccountService accountService;

    /**
     * 翻译
     *
     * @param userId
     * @return
     */
    @Override
    public AccountModel translateAccount(String userId) {
        return accountService.getAccountInfo(userId);
    }
}
