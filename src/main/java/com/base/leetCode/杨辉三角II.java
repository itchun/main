package com.base.leetCode;

import java.util.ArrayList;
import java.util.List;

public class 杨辉三角II {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> row_before = new ArrayList<>();
        for (int i = 0; i < rowIndex; i++) {
            List<Integer> row_now = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    row_now.add(1);
                else
                    row_now.add(row_before.get(j - 1) + row_before.get(j));
            }
            row_before = row_now;
        }
        return row_before;
    }
}
