package com.base.leetCode;

public class 罗马数字转整数 {

    public static void main(String[] args) {
        int index_I = "I".toCharArray()[0];
        int index_V = "V".toCharArray()[0];
        int index_X = "X".toCharArray()[0];
        int index_L = "L".toCharArray()[0];
        int index_C = "C".toCharArray()[0];
        int index_D = "D".toCharArray()[0];
        int index_M = "M".toCharArray()[0];
        System.out.println(index_I + "_" + index_V);

        romanToInt3("MCMXCIV");
    }

    public int romanToInt(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            String index0 = String.valueOf(s.charAt(i));
            String index1 = (i + 1) == s.length() ? "" : String.valueOf(s.charAt(i + 1));
            String index = index0 + index1;
            int val = 0;
            switch (index) {
                case "IV":
                    val = 4;
                    break;
                case "IX":
                    val = 9;
                    break;
                case "XL":
                    val = 40;
                    break;
                case "XC":
                    val = 90;
                    break;
                case "CD":
                    val = 400;
                    break;
                case "CM":
                    val = 900;
                    break;
            }
            if (val != 0) {
                i++;
                sum += val;
                continue;
            }
            switch (index0) {
                case "I":
                    val = 1;
                    break;
                case "V":
                    val = 5;
                    break;
                case "X":
                    val = 10;
                    break;
                case "L":
                    val = 50;
                    break;
                case "C":
                    val = 100;
                    break;
                case "D":
                    val = 500;
                    break;
                case "M":
                    val = 1000;
                    break;
            }
            sum += val;
        }
        return sum;
    }

    public int romanToInt2(String s) {
        s = s.replace("IV", "+4+")
                .replace("IX", "+9+")
                .replace("XL", "+40+")
                .replace("XC", "+90+")
                .replace("CD", "+400+")
                .replace("CM", "+900+")
                .replace("I", "+1+")
                .replace("V", "+5+")
                .replace("X", "+10+")
                .replace("L", "+50+")
                .replace("C", "+100+")
                .replace("D", "+500+")
                .replace("M", "+1000+");
        int sum = 0;
        for (String val : s.split("\\+")) {
            sum += Integer.valueOf(val == null || val.isEmpty() ? "0" : val);
        }
        return sum;
    }

    public static int romanToInt3(String s) {
        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            char index0 = chars[i];
            char index1 = (i + 1) == chars.length ? 0 : chars[i + 1];
            int val = 0;
            if (index1 != 0) {
                if (index0 == 73 && index1 == 86) {
                    val = 4;
                } else if (index0 == 73 && index1 == 88) {
                    val = 9;
                } else if (index0 == 88 && index1 == 76) {
                    val = 40;
                } else if (index0 == 88 && index1 == 67) {
                    val = 90;
                } else if (index0 == 67 && index1 == 68) {
                    val = 400;
                } else if (index0 == 67 && index1 == 77) {
                    val = 900;
                }
            }
            if (val != 0) {
                i++;
                sum += val;
                continue;
            }
            switch (index0) {
                case 73:
                    val = 1;
                    break;
                case 86:
                    val = 5;
                    break;
                case 88:
                    val = 10;
                    break;
                case 76:
                    val = 50;
                    break;
                case 67:
                    val = 100;
                    break;
                case 68:
                    val = 500;
                    break;
                case 77:
                    val = 1000;
                    break;
            }
            sum += val;
        }
        return sum;
    }

}
