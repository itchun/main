package com.base.leetCode;

public class 实现strStr {

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public int strStr2(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        char[] char_haystack = haystack.toCharArray();
        char[] char_needle = needle.toCharArray();
        for (int i = 0; i < char_haystack.length; i++) {
            if (char_haystack[i] == char_needle[0]) {
                int j;
                for (j = 1; j < char_needle.length; j++) {
                    int index = i + j;
                    if (index >= char_haystack.length) return -1;
                    if (char_haystack[index] != char_needle[j]) {
                        break;
                    }
                }
                if (j == char_needle.length) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new 实现strStr().strStr2("aaaaa", "aa");
    }
}
