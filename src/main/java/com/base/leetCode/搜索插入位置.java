package com.base.leetCode;

// 考二分法
public class 搜索插入位置 {

    public int searchInsert(int[] nums, int target) {
        int i;
        for(i = 0 ;i < nums.length ; i++){
            if(nums[i] < target) continue;
            if(nums[i] >= target) return i;
        }
        return i;
    }
}
