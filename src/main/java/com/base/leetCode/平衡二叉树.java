package com.base.leetCode;

import com.base.leetCode.entity.TreeNode;

public class 平衡二叉树 {

    public static void main(String[] args) {

    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        TreeNode left = root.left;
        int left_ = 0;
        while (left != null) {
            left_++;
            left = left.left;
        }
        TreeNode right = root.right;
        int right_ = 0;
        while (right != null) {
            right_++;
            right = right.right;
        }
        if ((left_ - right_) > 1 || (right_ - left_) > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

}
