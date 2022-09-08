package com.lihd.top;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/6 20:11
 */
public class Code094BinaryTreeInorderTraversal {

    // morris遍历


    // 非递归版


    // 递归版
    // 0 ms, faster than 100.00%, 42.7 MB, less than 9.88%
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    private void dfs(TreeNode node, List<Integer> ans) {
        if (node != null) {
            dfs(node.left, ans);
            ans.add(node.val);
            dfs(node.right, ans);
        }
    }


    private static final class TreeNode {
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
