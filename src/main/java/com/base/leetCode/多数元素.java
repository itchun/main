package com.base.leetCode;

import java.util.Arrays;
import java.util.HashMap;

public class 多数元素 {

    public static void main(String[] args) {

    }


    // hash
    public static int majorityElement(int[] nums) {
        int max = (nums.length + 1) / 2;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; 0 < max; i++) {

            // 顺序
            int num = map.getOrDefault(nums[i], 0);
            map.put(nums[i], num + 1);

            // 倒序
            int last_index = nums.length - 1 - i;
            int num2 = 0;
            if (last_index > i) {
                num2 = map.getOrDefault(nums[last_index], 0);
                map.put(nums[last_index], num2 + 1);
            }

            if ((num + 1) == max) return nums[i];
            if ((num2 + 1) == max) return nums[last_index];
        }
        return 0;
    }

    // 无论众数是多少，返回[n/2]下标对应的值都是正确的。
    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


    // 如果一个数组有大于一半的数相同，那么任意删去两个不同的数字，新数组还是会有相同的性质
    public static int majorityElement3(int[] nums) {
        int index = nums[0];
        int times = 1;
        for (int i = 1; i < nums.length; i++) {
            if (index == nums[i]) {
                times++;
            } else {
                if (times == 0) {
                    index = nums[i];
                    times = 1;
                } else
                    times--;
            }
        }
        return index;
    }
}
