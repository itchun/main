package com.base.leetCode;

public class 回文数 {

    public static void main(String[] args) {

    }

    public boolean isPalindrome(int x) {
        String x_str = x + "";
        int x_size = x_str.length();
        if (x == 0) return true;
        if (x < 0) return false;
        int index = x_size % 2 == 0 ? x_size / 2 : (x_size + 1) / 2;
        for (int i = 0; i < index; i++) {
            if (x_str.charAt(i) != (x_str.charAt(x_size - 1 - i)))
                return false;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        String x_str = x + "";
        int x_size = x_str.length();
        if (x == 0) return true;
        if (x < 0) return false;
        if (x_size % 2 == 0 && x_str.substring(0, x_size / 2).equals(new StringBuffer(x_str.substring(x_size / 2, x_size)).reverse().toString()
        ))
            return true;
        if (x_size % 2 == 1 && x_str.substring(0, (x_size - 1) / 2).equals(new StringBuffer(x_str.substring((x_size + 1) / 2, x_size)).reverse().toString()
        ))
            return true;
        return false;
    }
}
