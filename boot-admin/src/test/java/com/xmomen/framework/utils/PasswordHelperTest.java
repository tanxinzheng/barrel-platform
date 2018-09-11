package com.xmomen.framework.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tanxinzheng on 2018/8/2.
 */
public class PasswordHelperTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void encryptPassword() throws Exception {
        String uuid = UUIDGenerator.getInstance().getUUID(32);
        System.out.println(uuid);
        String password = PasswordHelper.encryptPassword("123456", uuid);
        System.out.println(password);
    }

}