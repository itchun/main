package com.base.leetCode;

public class 爬楼梯 {

    public static void main(String[] args) {
        System.out.println(new 爬楼梯().climbStairs(5));
    }

    public int climbStairs(int n) {
        int n_2 = 0, n_1 = 1;
        int method = 0;
        for (int i = 1; i <= n; i++) {
            method = n_2 + n_1;
            n_2 = n_1;
            n_1 = method;
        }
        return method;
    }
}
