package com.base.leetCode;

import com.alibaba.fastjson.JSON;
import com.base.leetCode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的后序遍历 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        System.out.println(JSON.toJSON(new 二叉树的后序遍历().postorderTraversal(root)));
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        postorder(root, res);
        return res;
    }

    public void postorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }
}
