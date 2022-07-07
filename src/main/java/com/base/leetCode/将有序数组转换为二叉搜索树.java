package com.base.leetCode;

public class 将有序数组转换为二叉搜索树 {

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        new 将有序数组转换为二叉搜索树().sortedArrayToBST(nums);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;
        int length = nums.length;
        if (length == 0) return null;
        if (length == 1) return new TreeNode(nums[0]);
        int half_index = nums.length / 2;
        int[] nums_left = new int[half_index];
        for (int i = 0; i < half_index; i++) {
            nums_left[i] = nums[i];
        }
        int[] nums_rigth = (half_index + 1) == length ? null : new int[length - (half_index + 1)];
        for (int i = 0; (i + half_index + 1) < length; i++) {
            nums_rigth[i] = nums[i + half_index + 1];
        }
        return new TreeNode(nums[half_index], sortedArrayToBST(nums_left), sortedArrayToBST(nums_rigth));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
