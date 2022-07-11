package com.base.leetCode;

public class 二叉树的最大深度 {

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

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int level = 1;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return level + (left >= right ? left : right);
    }
}
