package com.base.字符串;

import java.util.Random;

public class 随机数 {
    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            int ran1 = r.nextInt(100);
//            System.out.println(ran1);
        }

        int i=(int)(Math.random()*900)+100;
        System.out.println(i);
    }
}
