package com.base.leetCode;

public class 加一 {

    public static void main(String[] args) {
        new 加一().plusOne(new int[]{1, 2, 3});
    }

    public int[] plusOne(int[] digits) {
        digits[digits.length - 1] += 1;
        int i = 1;
        while (digits[digits.length - i] == 10) {
            digits[digits.length - i] = 0;
            if ((digits.length - i - 1) >= 0) {
                digits[digits.length - i - 1] += 1;
                i++;
            } else {
                int[] digits_new = new int[digits.length + 1];
                digits_new[0] = 1;
                for (int j = 0; j < digits.length; j++) {
                    digits_new[j + 1] = digits[j];
                }
                return digits_new;
            }
        }
        return digits;
    }
}
