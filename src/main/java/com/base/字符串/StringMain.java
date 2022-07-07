package com.base.字符串;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StringMain {

    public static final String A = "ab";
    public static void main(String[] args) {
        String s1 = "hello" + new String("world");
        String s2 = "hello" + new String("world");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        String s3 = "hello word";
        System.out.println(s3.indexOf("w"));
        System.out.println(s3.indexOf("l"));

        String a = "ab";
        System.out.println(a == A);
        System.out.println(a.intern());

        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        String updates = "update registration_baby_data_center set status = '正在拉取', get_time = ''  where id in(" + StringUtils.join(list, ",") + ")";
        System.out.println(updates);

    }

}
