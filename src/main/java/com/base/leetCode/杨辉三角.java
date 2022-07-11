package com.base.leetCode;

import java.util.ArrayList;
import java.util.List;

public class 杨辉三角 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> generate_list = new ArrayList<>();
        List<Integer> generate_row_before = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> generate_row_now = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                if (j == 1) generate_row_now.add(1);
                else if (j == i) generate_row_now.add(1);
                else generate_row_now.add(generate_row_before.get(j - 2) + generate_row_before.get(j - 1));
            }
            generate_row_before = generate_row_now;
            generate_list.add(generate_row_now);
        }
        return generate_list;
    }

    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> generate_list = new ArrayList<>();
        List<Integer> generate_row_before = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> generate_row_now = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    generate_row_now.add(1);
                else
                    generate_row_now.add(generate_row_before.get(j - 1) + generate_row_before.get(j));
            }
            generate_row_before = generate_row_now;
            generate_list.add(generate_row_now);
        }
        return generate_list;
    }
}
