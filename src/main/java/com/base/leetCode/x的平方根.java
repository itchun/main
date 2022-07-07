package com.base.leetCode;

public class x的平方根 {

    public static void main(String[] args) {
        System.out.println(new x的平方根().mySqrt(2147395599));
    }

    public int mySqrt(int x) {
        int left = 0, right = x;
        int i = x;
        while (right - left > 1) {
            i = left + (right - left) / 2;
            long il = (long)i * (long)i;
            if (il == x) return i;
            if (il < x) {
                left = i;
            } else {
                right = i;
            }
        }
        return i * i > x ? i - 1 : i;
    }
}
