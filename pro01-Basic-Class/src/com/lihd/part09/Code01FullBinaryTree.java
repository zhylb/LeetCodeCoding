package com.lihd.part09;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/17 8:31
 */
public class Code01FullBinaryTree {



    public static class Info{
        int nodeNums;
        int height;

        public Info(int nodeNums, int height) {
            this.nodeNums = nodeNums;
            this.height = height;
        }
    }


    public static boolean isFullTree(TreeNode root) {

        Info info = getBinaryTreeInfo(root);
        int height = info.height;
        int nodeNums = info.nodeNums;
        return (2 << height) - 1 == nodeNums;
    }

    public static Info getBinaryTreeInfo(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = getBinaryTreeInfo(node.left);
        Info rightInfo = getBinaryTreeInfo(node.right);
        int nodeNums = leftInfo.nodeNums + rightInfo.nodeNums + 1;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(nodeNums, height);
    }
}
