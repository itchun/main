package com.base.leetCode;

import java.util.Arrays;

public class Excel表列名称 {

    public static void main(String[] args) {
        System.out.println(26*26*26*26*26*26*6);
        System.out.println(25 / 26);
        System.out.println(23 % 26);
        System.out.println(convertToTitle(27));
        System.out.println(convertToTitle4(27));
        System.out.println(convertToTitle(701));
        System.out.println(convertToTitle4(701));
        System.out.println(convertToTitle2(2147483647));
        System.out.println(convertToTitle3(2147483647));
        System.out.println(convertToTitle4(2147483647));
        System.out.println(convertToTitle5(674));
        System.out.println(convertToTitle5(25));
        System.out.println(convertToTitle5(26));
//        System.out.println(convertToTitle(2147483647));

        // FXSHRXW = F                   + X                 + S              + H          + R           + X  + W
        // FXSHRXW = 26*26*26*26*26*26*6 + 26*26*26*26*26*24 + 26*26*26*26*19 + 26*26*26*8 + 26*26*18 + 26*24 + 26*0+23
        System.out.println(26 * 26 * 26 * 26 * 26 * 26 * 6 + 26 * 26 * 26 * 26 * 26 * 24 + 26 * 26 * 26 * 26 * 19 + 26 * 26 * 26 * 8 + 26 * 26 * 18 + 26 * 24 + 26 * 0 + 23);

//        String[] strs =     {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",   "K", "L",  "M",  "N",  "O",  "P",  "Q", "R",   "S", "T",   "U",  "V",  "W",  "X", "Y", "Z"};
//        String[] strs_int = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26"};
//        for (int i = 0; i < strs.length; i++) {
//            String str1 = strs[i];
//            String next = "Z".equals(str1) ? "A" : strs[i + 1];
//            System.out.println("case \"" + str1 + "\":\n" +
//                    "                next = \"" + next + "\";\n" +
//                    "                break;");
//        }
    }


    public static String convertToTitle(int columnNumber) {
        String[] result = new String[]{};
        int index = 0;
        while (columnNumber > 0) {

            // 递增
            result = incr(result, 0);

            // 循环
            index++;
            if (index == 26) index = 0;
            columnNumber--;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = result.length - 1; i >= 0; i--) {
            sb.append(result[i]);
        }
        return sb.toString();
    }


    public static String[] incr(String[] result, int index) {

        // 是否扩容
        if (result.length - 1 < index) {
            result = Arrays.copyOf(result, result.length + 1);
        }

        // 取值
        String index_str = result[index];
        index_str = index_str == null ? "" : index_str;

        // 进一位
        if ("Z".equals(index_str)) {
            result = incr(result, index + 1);
        }
        String next = "";
        switch (index_str) {
            case "":
                next = "A";
                break;
            case "A":
                next = "B";
                break;
            case "B":
                next = "C";
                break;
            case "C":
                next = "D";
                break;
            case "D":
                next = "E";
                break;
            case "E":
                next = "F";
                break;
            case "F":
                next = "G";
                break;
            case "G":
                next = "H";
                break;
            case "H":
                next = "I";
                break;
            case "I":
                next = "J";
                break;
            case "J":
                next = "K";
                break;
            case "K":
                next = "L";
                break;
            case "L":
                next = "M";
                break;
            case "M":
                next = "N";
                break;
            case "N":
                next = "O";
                break;
            case "O":
                next = "P";
                break;
            case "P":
                next = "Q";
                break;
            case "Q":
                next = "R";
                break;
            case "R":
                next = "S";
                break;
            case "S":
                next = "T";
                break;
            case "T":
                next = "U";
                break;
            case "U":
                next = "V";
                break;
            case "V":
                next = "W";
                break;
            case "W":
                next = "X";
                break;
            case "X":
                next = "Y";
                break;
            case "Y":
                next = "Z";
                break;
            case "Z":
                next = "A";
                break;
        }

        // 赋值
        result[index] = next;
        return result;
    }


    // 官方解答
    public static String convertToTitle2(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber > 0) {
            int a0 = (columnNumber - 1) % 26 + 1;
            sb.append((char) (a0 - 1 + 'A'));
            columnNumber = (columnNumber - a0) / 26;
        }
        return sb.reverse().toString();
    }


    // 技巧解答
    // FXSHRXW = 26*26*26*26*26*26*6 + 26*26*26*26*26*24 + 26*26*26*26*19 + 26*26*26*8 + 26*26*18 + 26*24 + 26*0+23
    //
    public static String convertToTitle3(int columnNumber) {
        StringBuffer str = new StringBuffer();
        while (columnNumber != 0) {
            --columnNumber;
            int index = columnNumber % 26; // 对应值比下标值比大一, 所以减一之后正好是下标值
            str.append((char) ("A".charAt(0) + index)); // 这里是下标值
            columnNumber /= 26;
        }
        return str.reverse().toString();
    }


    // FXSHRXW = 26*26*26*26*26*26*6 + 26*26*26*26*26*24 + 26*26*26*26*19 + 26*26*26*8 + 26*26*18 + 26*24 + 0+23
    // ZY = 26*26 + 0+25
    // AA = 26*1 + 1
    // AB = 26*1 + 2
    // AB = 26*0 + 1*1
    public static String convertToTitle4(int columnNumber) {
        StringBuffer str = new StringBuffer();
        while (columnNumber > 0) {
            int index = (columnNumber - 1) % 26 + 1;
            str.append((char) ("A".charAt(0) + (index - 1)));
            columnNumber = (columnNumber - index) / 26;
        }
        return str.reverse().toString();
    }


    // A = 0
    // Z = 25
    // AA = 26
    public static String convertToTitle5(int columnNumber) {
        StringBuffer str = new StringBuffer();
        while (columnNumber >= 0) {
            int index = (columnNumber - 1) % 25 + 1; // 对应值等于下标值
            str.append((char) ("A".charAt(0) + index));
            columnNumber /= 25;
        }
        return str.reverse().toString();
    }
}
