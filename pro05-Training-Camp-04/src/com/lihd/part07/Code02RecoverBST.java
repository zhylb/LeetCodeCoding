package com.lihd.part07;

import java.util.HashMap;
import java.util.Stack;
import java.util.TreeSet;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/19 19:15
 */
public class Code02RecoverBST {


    public static void recoverTree(TreeNode root) {
        TreeNode err1 = null;
        TreeNode err2 = null;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                if (pre != null && pre.val > node.val) {
                    err1 = err1 == null ? pre : err1;
                    err2 = node;
                }
                pre = node;
                node = node.right;
            }
        }

        int t = err1.val;
        err1.val = err2.val ;
        err2.val = t;

    }



    public static class TreeNode {
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
