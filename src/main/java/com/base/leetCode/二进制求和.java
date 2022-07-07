package com.base.leetCode;

public class 二进制求和 {

    public static void main(String[] args) {
        new 二进制求和().addBinary("100", "110010");
    }

    public String addBinary(String a, String b) {
        if ("0".equals(a)) return b;
        if ("0".equals(b)) return a;
        String max = a.length() >= b.length() ? a : b;
        String min = a.length() < b.length() ? a : b;
        String str = "";
        String next = "0";
        for (int i = 0; i <= max.length(); i++) {
            String max_index = max.length() - 1 - i >= 0 ? max.charAt(max.length() - 1 - i) + "" : "0";
            String min_index = min.length() - 1 - i >= 0 ? min.charAt(min.length() - 1 - i) + "" : "0";
            if ("1".equals(max_index) && "1".equals(min_index) && "0".equals(next)) {
                str = "0" + str;
                next = "1";
            } else if ("1".equals(max_index) && "1".equals(min_index) && "1".equals(next)) {
                str = "1" + str;
                next = "1";
            } else if (("1".equals(max_index) || "1".equals(min_index)) && "1".equals(next)) {
                str = "0" + str;
                next = "1";
            } else if (("1".equals(max_index) || "1".equals(min_index)) && "0".equals(next)) {
                str = "1" + str;
                next = "0";
            } else if ("0".equals(max_index) && "0".equals(min_index) && "1".equals(next)) {
                str = "1" + str;
                next = "0";
            } else if ("0".equals(max_index) && "0".equals(min_index) && "0".equals(next) && i < max.length()) {
                str = "0" + str;
                next = "0";
            } else if ("0".equals(max_index) && "0".equals(min_index) && "0".equals(next) && i == max.length()) {
                break;
            }
        }
        return str;
    }

}
