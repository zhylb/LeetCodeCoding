package com.lihd.part08;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/6 14:51
 */
public class TreeMaxLen {

    public static class Info{
        int maxLen;
        int height;

        public Info(int maxLen, int height) {
            this.maxLen = maxLen;
            this.height = height;
        }
    }

    public static int getTreeMaxLen(Node root){
        return getTreeInfo(root).maxLen;
    }

    public static Info getTreeInfo(Node node){
        if(node == null){
            return new Info(0,0);
        }
        Info leftInfo = getTreeInfo(node.left);
        Info rightInfo = getTreeInfo(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxSubTreeLen = Math.max(leftInfo.maxLen, rightInfo.maxLen);
        int maxTreeLen = Math.max(maxSubTreeLen, leftInfo.height + rightInfo.height + 1);
        return new Info(maxTreeLen, height);
    }

}
