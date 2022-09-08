package com.lihd.daily;

import com.lihd.top.Code098ValidateBinarySearchTree;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/6 20:39
 */
public class Code814BinaryTreePruning {

    /**
     * 树形dp即可 <br/>
     * 0 ms, faster than 100.00%, 41.8 MB, less than 40.79% <br/>
     * @param root
     * @return com.lihd.daily.Code814BinaryTreePruning.TreeNode
     * @author lihd
     * @date 2022/9/6 20:50
     */
    public TreeNode pruneTree(TreeNode root) {
        boolean b = getInfo(root);
        if (b) {
            return null;
        } else {
            return root;
        }
    }

    private boolean getInfo(TreeNode node) {
        if (node == null) {
            return true;
        }
        boolean l = getInfo(node.left);
        boolean r = getInfo(node.right);
        if (l) {
            node.left = null;
        }
        if (r) {
            node.right = null;
        }
        return node.val == 0 && l && r;
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
