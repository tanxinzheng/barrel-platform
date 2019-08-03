package com.github.tanxinzheng.framework.utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

public class DateTimeUtils {

    private static FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyyMMddHHmmss");

    /**
     * 获取当前日期字符，字符格式：yyyyyMMddHHmmss
     * @return
     */
    public static String getDatetimeString(Date date){
        return fastDateFormat.format(date);
    }
}
