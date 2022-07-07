package com.base.leetCode;

public class 删除有序数组中的重复项 {

    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int befor = nums[0];
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (befor != nums[i]) {
                j++;
            }
            nums[j] = nums[i];
            befor = nums[i];
        }
        return ++j;
    }

    public static void main(String[] args) {
//        removeDuplicates(new int[]{1, 2});
//        removeDuplicates(new int[]{1, 1, 2});
        removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});

    }

}
