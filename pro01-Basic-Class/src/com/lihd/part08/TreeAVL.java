package com.lihd.part08;

/**
 * 给定一个节点 判断这个树是不是平衡二叉树
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/6 14:42
 */
public class TreeAVL {

    //需要整合的信息
    public static class Info{
        int height;
        boolean isAVLTree;

        public Info(int height, boolean isAVLTree) {
            this.height = height;
            this.isAVLTree = isAVLTree;
        }
    }


    //
    public static boolean isAVLTree(Node root){
        return getTreeInfo(root).isAVLTree;
    }

    public static Info getTreeInfo(Node node){
        if(node == null){
            return new Info(0,true);
        }

        Info leftInfo = getTreeInfo(node.left);
        Info rightInfo = getTreeInfo(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        //左树是平衡二叉树 右树是平衡二叉树 左树高度 和 右树高度相差不超过 1
        boolean isAVLTree = leftInfo.isAVLTree && rightInfo.isAVLTree && Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new Info(height,isAVLTree);
    }



}
