package com.base.leetCode;

public class Excel表列序号 {

    public static void main(String[] args) {

        // 2147483647
        // FXSHRXW = 26*26*26*26*26*26*6 + 26*26*26*26*26*24 + 26*26*26*26*19 + 26*26*26*8 + 26*26*18 + 26*24 + 26*1/26*23
        System.out.println(titleToNumber("FXSHRXW"));
        System.out.println(titleToNumber2("FXSHRXW"));
    }


    public static int titleToNumber(String columnTitle) {
        String[] columnTitles = columnTitle.split("");
        int sum = 0;
        int index_a = "A".charAt(0);
        for (int i = 0; i < columnTitles.length; i++) {
            String index = columnTitles[i];
            int index_int = index.charAt(0);
            int y = columnTitles.length - i - 1;
            int a0 = index_int - index_a + 1;
            sum += Math.pow(26, y) * a0;
        }
        return sum;
    }

    public static int titleToNumber2(String columnTitle) {
        String[] columnTitles = columnTitle.split("");
        int sum = 0;
        int curMulti = 1;
        for (int i = columnTitles.length - 1; i >= 0; i--) {
            String index = columnTitles[i];
            int index_int = index.charAt(0) - "A".charAt(0) + 1;
            sum = sum + curMulti * index_int;
            curMulti *= 26;
        }
        return sum;
    }

    public static int titleToNumber3(String columnTitle) {
        int sum = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            sum = 26 * sum + (columnTitle.charAt(i) - "A".charAt(0) + 1);
        }
        return sum;
    }
}
