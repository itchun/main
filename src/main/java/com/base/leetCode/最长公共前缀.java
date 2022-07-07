package com.base.leetCode;

public class 最长公共前缀 {

    public static void main(String[] args) {

    }

    public String longestCommonPrefix(String[] strs) {
        String index0 = strs[0];
        if (index0.length() == 0) return "";
        String commonPrefix = "";
        ok:
        for (int i = 0; i < index0.length(); i++) {
            String startWith = index0.substring(0, i + 1);
            for (String str : strs) {
                if (str.startsWith(startWith))
                    continue;
                else
                    break ok;
            }
            commonPrefix = startWith;
        }
        return commonPrefix;
    }

    public String longestCommonPrefix2(String[] strs) {
        String index0 = strs[0];
        if(index0.length()==0)return "";
        for (String str : strs) {
            while (!str.startsWith(index0)) {
                index0 = index0.substring(0, index0.length() - 1);
            }
        }
        return index0;
    }
}
