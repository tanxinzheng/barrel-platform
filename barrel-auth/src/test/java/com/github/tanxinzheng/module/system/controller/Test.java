package com.github.tanxinzheng.module.system.controller;

import com.github.tanxinzheng.framework.utils.UUIDGenerator;

public class Test {

    public static void main(String[] args) {
        String uuid = UUIDGenerator.getInstance().getUUID();
        System.out.println(uuid);
    }
}
