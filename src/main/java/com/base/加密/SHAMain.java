package com.base.加密;

import java.security.MessageDigest;

public class SHAMain {

    public static void main(String[] args) {
        System.out.println(encrypt("datahome123"));
    }

    //加密
    public static String encrypt(String password) {
        StringBuffer pwd = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(password.getBytes("utf-8"));
            byte[] raw = md.digest();
            String temp;
            for (int i = 0; i < raw.length; i++) {
                temp = Integer.toHexString(raw[i] & 0xFF);
                if (temp.length() == 1) {
                    pwd.append("0");
                }
                pwd.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pwd.toString();
    }
}
