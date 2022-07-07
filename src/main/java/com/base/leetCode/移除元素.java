package com.base.leetCode;

public class 移除元素 {

    public int removeElement(int[] nums, int val) {
        int j;
        for (int i = j = 0; i < nums.length; i++) {
            int index = nums[i];
            if (index != val) {
                nums[j] = index;
                j++;
            }
        }
        return j;
    }
}
