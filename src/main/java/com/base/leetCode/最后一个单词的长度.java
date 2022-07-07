package com.base.leetCode;

public class 最后一个单词的长度 {

    // string lastIndexOf
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int start = s.lastIndexOf(" ");
        if (start == -1) return s.length();
        else
            return s.length() - start - 1;
    }

    // 正则
    public int lengthOfLastWord2(String s) {
        s = s.trim();
        String[] ss = s.split(" ");
        return ss[ss.length - 1].length();
    }

    // 反向遍历
    public int lengthOfLastWord3(String s) {
        int index = s.length() - 1;
        while (index >= 0 && s.charAt(index) == ' ') {
            index--;
        }

        int len = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            index--;
            len++;
        }
        return len;
    }

}
