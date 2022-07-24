package com.lihd.part08;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/6 15:00
 */
public class TreeBSTNode {

    public static class Info{
        Node avl;
        Integer max;
        Integer min;
        int maxBSTLen;
        boolean isAllBST;
        public Info(Node avl, Integer max, Integer min, int maxBSTLen, boolean isAllBST) {
            this.avl = avl;
            this.max = max;
            this.min = min;
            this.maxBSTLen = maxBSTLen;
            this.isAllBST = isAllBST;
        }
        public Info(int maxBSTLen, boolean isAllBST) {
            this.maxBSTLen = maxBSTLen;
            this.isAllBST = isAllBST;
        }
    }

    public static Node getMaxBSTTree(Node root){
        return getTreeInfo(root).avl;
    }

    public static Info getTreeInfo(Node node){
        if(node == null){
            return new Info(0,true);
        }

        Info leftInfo = getTreeInfo(node.left);
        Info rightInfo = getTreeInfo(node.right);

        //更新好 最大值 和最小值
        int max = node.value;
        int min = node.value;
        if(leftInfo.max != null){
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if(rightInfo.max != null){
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }
        // 假设不通过 X
        Node curNode = node;//这个node可能被更新两次
        int maxBSTLen = 0;
        boolean isAllBST = false;
        if(leftInfo.maxBSTLen >= rightInfo.maxBSTLen){
            curNode = leftInfo.avl;
            maxBSTLen = leftInfo.maxBSTLen;
        }else{
            curNode = rightInfo.avl;
            maxBSTLen = rightInfo.maxBSTLen;
        }
        //如果通过
        if(leftInfo.isAllBST && rightInfo.isAllBST &&
                (leftInfo.max== null || leftInfo.max < node.value)&&
                (rightInfo.min == null|| rightInfo.min > node.value)
        ){
            curNode = node;
            maxBSTLen = leftInfo.maxBSTLen + rightInfo.maxBSTLen + 1;
            isAllBST = true;
        }
        return new Info(curNode, max, min, maxBSTLen, isAllBST);
    }
}
