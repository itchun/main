package com.base.leetCode;

public class 有效的括号 {

    public static void main(String[] args) {
        String s = "[[(){}]]{}[]";
    }

    public boolean isValid(String s) {
        if (s.length() == 1) return false;
        if (s.length() % 2 > 0) return false;
        int i = 0;
        while (i != s.length()) {
            i = s.length();
            s = s.replace("()", "").
                    replace("{}", "").
                    replace("[]", "");
        }
        return s.length() == 0;
    }
}
