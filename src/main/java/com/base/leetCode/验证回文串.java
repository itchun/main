package com.base.leetCode;

import java.util.ArrayList;
import java.util.List;

public class 验证回文串 {

    public static void main(String[] args) {
        System.out.println(new 验证回文串().isPalindrome("A man, a plan, a canal: Panama"));
    }

    public boolean isPalindrome(String s) {
        List<String> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i]) || Character.isLetter(chars[i])) {
                list.add(String.valueOf(chars[i]).toLowerCase());
            }
        }
        for (int i = 0, lengt = list.size() / 2; i < lengt; i++) {
            if (!list.get(i).equals(list.get(list.size() - 1 - i))) return false;
        }
        return true;
    }

    class Solution {
        public boolean isPalindrome(String s) {
            s = s.toLowerCase();
            int l = 0;
            int r = s.length() - 1;
            while (l < r) {
                while (l < r && !checkChar(s.charAt(l))) {
                    l++;
                }
                while (r > l && !checkChar(s.charAt(r))) {
                    r--;
                }
                if (l == r) {
                    break;
                }
                if (s.charAt(l) != s.charAt(r)) {
                    return false;
                }
                l++;
                r--;
            }
            return true;
        }

        boolean checkChar(char c) {
            return c >= '0' && c <= '9' || (c >= 'a' && c <= 'z');
        }
    }
}
