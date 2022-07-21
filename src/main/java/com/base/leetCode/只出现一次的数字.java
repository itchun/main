package com.base.leetCode;

import java.util.ArrayList;
import java.util.List;

public class 只出现一次的数字 {

    public static void main(String[] args) {

    }

    public int singleNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int index : nums) {
            String val = index + "a";
            if (list.contains(val)) {
                list.remove(val);
            } else {
                list.add(val);
            }
        }
        return Integer.valueOf(list.get(0).replace("a", ""));
    }

    public int singleNumber2(int[] nums) {
        int num=0;
        for(int i=0;i<nums.length;i++){
            num=num^nums[i];
        }

        return num;
    }
}
