package com.base.leetCode;

import java.util.ArrayList;
import java.util.List;

public class 相同的树 {

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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        List<Integer> p_list = inorderTraversal(p);
        List<Integer> q_list = inorderTraversal(q);
        return p_list.equals(q_list);
    }

    // 递归
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recursion(root, list);
        return list;
    }

    public void recursion(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        if (root.left != null)
            recursion(root.left, list);
        else
            list.add(null);
        if (root.right != null)
            recursion(root.right, list);
        else
            list.add(null);
    }

    // 最佳
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right);
        }
    }
}
