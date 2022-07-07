package com.base.字符串;

public class 长度计算 {
    public static void main(String[] args) throws Exception{
        String a = "310101FB06E687-DF4A-408F-9BF0-97EDB4F9CA96FB06E687-DF4A-408F-9BF0-97EDB4F9CA96FB06E687-DF4A-408F-9BF0-97EDB4F9CA96105232022/3/1 8:11:01";
        System.out.println(a.getBytes("utf-8").length);
        String b = "FB06E687-DF4A-408F-9BF0-97EDB4F9CA96105232022/3/1 8:11:01";
        System.out.println(b.getBytes("utf-8").length);

        String c = "FB06E687-DF4A-408F-9BF0-97EDB4F9CA96室外活动区100.791168269.711128230.6999999882022/3/1 9:02:12";
        System.out.println(c.getBytes("utf-8").length);
    }
}
