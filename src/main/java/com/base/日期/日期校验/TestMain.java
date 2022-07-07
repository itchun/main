package com.base.日期.日期校验;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestMain {

    public static void main(String[] args) throws Exception {
        String time_str_en = "Sep 21, 2021 | 09:05am";
        SimpleDateFormat dateFormat_en = new SimpleDateFormat("MMM d, yyyy | h:m aa", Locale.ENGLISH);
        Date time = dateFormat_en.parse(time_str_en.replace("am"," am").replace("pm"," pm"));

        SimpleDateFormat dateFormat_zh = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        String time_zh = dateFormat_zh.format(time);

        System.out.println(time_zh);
    }

}
