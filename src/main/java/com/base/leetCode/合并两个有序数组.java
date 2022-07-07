package com.base.leetCode;

import com.alibaba.fastjson.JSON;

public class 合并两个有序数组 {

    public static void main(String[] args) {
        System.out.println(JSON.toJSON(new 合并两个有序数组().merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3)));
    }

    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        int e = 0;
        int nums[] = new int[nums1.length];
        for (int i = 0; i < m; i++) {
            int nums1_val = nums1[i];

            // 比较最终值
            for (int k = j; k < n; k++) {
                int nums2_val = nums2[k];
                if (nums1_val >= nums2_val) {
                    nums[e] = nums2_val;
                    j++;
                    e++;
                }
            }
            nums[e] = nums1_val;
            e++;
        }
        for (int k = j; k < n; k++) {
            int nums2_val = nums2[k];
            nums[e] = nums2_val;
            j++;
            e++;
        }

        for(int i = 0 ; i < nums.length; i++){
            nums1[i] = nums[i];
        }
        return nums1;
    }

}
