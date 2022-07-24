package com.lihd.part09;

import sun.reflect.generics.tree.Tree;

import java.util.Stack;

/**
 * 判断一颗树 是否是完全二叉树
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/17 8:38
 */
public class Code02CompleteTree {

    public static class Info {
        boolean isFull;
        boolean isComplete;
        int height;

        public Info(boolean isFull, boolean isComplete, int height) {
            this.isFull = isFull;
            this.isComplete = isComplete;
            this.height = height;
        }
    }

    public static boolean isCompleteBinaryTree(TreeNode root) {
        return getCompleteBinaryTreeInfo(root).isComplete;
    }

    public static Info getCompleteBinaryTreeInfo(TreeNode node) {
        if (node == null) {
            return new Info(true, true, 0);
        }

        Info leftInfo = getCompleteBinaryTreeInfo(node.left);
        Info rightInfo = getCompleteBinaryTreeInfo(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        //左树是满二叉树 , 右树是满二叉树 , 高度相等 就是满二叉树
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;


        //判断是否是完全二叉树 如果是满的 一定是完全的 这个值只会由false改为true
        boolean isComplete = isFull;

        //必须满足两者都是完全二叉树才有讨论的必要
        if (leftInfo.isComplete && rightInfo.isComplete) {

            if (!leftInfo.isFull && rightInfo.isFull && leftInfo.height - rightInfo.height == 1) {
                isComplete = true;
            }
            //这种情况不要忘记
            if (leftInfo.isFull && rightInfo.isFull && leftInfo.height - rightInfo.height == 1) {
                isComplete = true;
            }

            if (leftInfo.isFull && !rightInfo.isFull && leftInfo.height == rightInfo.height) {
                isComplete = true;
            }

//            //这个条件已经在上面讨论过了
//            if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
//                isComplete = true;
//            }

        }

        return new Info(isFull, isComplete, height);
    }


    public static boolean isCompleteBinaryTree2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        boolean isLeaf = false;

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            TreeNode right = node.right;

            if (left == null && right != null) {
                //有左无右 返回false
                return false;
            }

            if (isLeaf && left != null ) { //(|| right != null) 因为左孩子是null  右孩子一定是null
                //如果叶节点被激活 并且 有一个孩子不是null  即存在叶节点
                return false;
            }

            if (left != null) {
                stack.push(left);
            }

            if (right != null) {
                stack.push(right);
            }

            if (left == null || right == null) {
                isLeaf = true;
            }

        }
        return true;
    }


}


