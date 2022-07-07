package com.base.leetCode;

public class 最大数组和 {

    public static void main(String[] args) {
        int[] nums = {2, -1, -1, 2, 0, -3, 3};
        System.out.println(new 最大数组和().maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {

        // 特殊
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // 判断是否全部大于等于0
        boolean all = true;
        int sum_all = 0;
        for (int i : nums) {
            if (i < 0) {
                all = false;
                break;
            }
            sum_all += i;
        }
        if (all) {
            return sum_all;
        }

        // 判断是否全部小于0
        all = true;
        sum_all = 0;
        Integer max_0 = null;
        for (int i : nums) {
            if (i >= 0) {
                all = false;
                break;
            }
            sum_all += i;
            max_0 = max_0 == null ? i : max_0;
            max_0 = max_0 >= i ? max_0 : i;
        }
        if (all) {
            return max_0;
        }

        // 首先剥离左右负数
        boolean l = true, r = true;
        for (int i = 0; i < nums.length; i++) {
            int left = nums[i];
            if (left < 0 && l) {
                nums[i] = 0;
            }
            if (left >= 0 && l) {
                l = false;
            }

            int right = nums[nums.length - 1 - i];
            if (right < 0 && r) {
                nums[nums.length - 1 - i] = 0;
            }
            if (right >= 0 && r) {
                r = false;
            }

            if (!l && !r) break;
        }

        // 求一次和
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        // 左边再剥离一次正负
        int[] nums_left = copyArray(nums);
        l = true;
        boolean l_next = true;
        for (int i = 0; i < nums_left.length; i++) {
            int left = nums_left[i];
            if (left == 0) continue;
            if (left > 0 && l) {
                nums_left[i] = 0;
            }
            if (left < 0 && (l || l_next)) {
                nums_left[i] = 0;
                l = false;
            }
            if (left > 0 && !l && l_next) {
                l_next = false;
            }
            if (!l && !l_next) break;
        }
        int sum_left = 0;
        for (int i : nums_left) {
            sum_left += i;
        }

        // 右边再剥离一次正负
        int[] nums_right = copyArray(nums);
        r = true;
        boolean r_next = true;
        for (int i = 0; i < nums_right.length; i++) {
            int right = nums_right[nums_right.length - 1 - i];
            if (right == 0) continue;
            if (right > 0 && r) {
                nums_right[nums_right.length - 1 - i] = 0;
            }
            if (right < 0 && (r || r_next)) {
                nums_right[nums_right.length - 1 - i] = 0;
                r = false;
            }
            if (right > 0 && !r && r_next) {
                r_next = false;
            }
            if (!r && !r_next) break;
        }
        int sum_right = 0;
        for (int i : nums_right) {
            sum_right += i;
        }

        // 左右同时剥离一次正负
        int[] nums_left_right = copyArray(nums);
        l = true;
        l_next = true;
        r = true;
        r_next = true;
        for (int i = 0; i < nums_left_right.length; i++) {
            int left = nums_left_right[i];
            if (left > 0 && l) {
                nums_left_right[i] = 0;
            }
            if (left < 0 && (l || l_next)) {
                nums_left_right[i] = 0;
                l = false;
            }
            if (left > 0 && !l && l_next) {
                l_next = false;
            }

            int right = nums_left_right[nums_left_right.length - 1 - i];
            if (right > 0 && r) {
                nums_left_right[nums_left_right.length - 1 - i] = 0;
            }
            if (right < 0 && (r || r_next)) {
                nums_left_right[nums_right.length - 1 - i] = 0;
                r = false;
            }
            if (right > 0 && !r && r_next) {
                r_next = false;
            }

            if (!l && !l_next && !r && !r_next) break;
        }
        int sum_left_right = 0;
        for (int i : nums_left_right) {
            sum_left_right += i;
        }

        //比较大小
        int[] nums_new = sum >= sum_left ? nums : nums_left;
        int nums_max = sum >= sum_left ? sum : sum_left;
        nums_new = nums_max >= sum_right ? nums_new : nums_right;
        nums_max = nums_max >= sum_right ? nums_max : sum_right;
        nums_new = nums_max >= sum_left_right ? nums_new : nums_left_right;
        nums_max = nums_max >= sum_left_right ? nums_max : sum_left_right;
        if (nums_new != nums) {
            int nums_max_next = maxSubArray(nums_new);
            nums_max = nums_max >= nums_max_next ? nums_max : nums_max_next;
        }
        Integer max = null;
        for (int i = 0; i < nums.length; i++) {
            if (max == null) max = nums[i];
            else {
                max = Math.max(max, nums[i]);
            }
        }
        return Math.max(max, nums_max);
    }

    public int[] copyArray(int[] nums) {
        int[] nums_new = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums_new[i] = nums[i];
        }
        return nums_new;
    }
}
