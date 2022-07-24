package com.lihd.part08;

/**
 * 获取树 最大搜索子树的个数
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/6 13:04
 */
public class TreeBST {
    public static class Info{
        Integer max;
        Integer min;
        boolean isAllBST;
        Integer maxBSTLength;
        public Info(Integer max, Integer min, boolean isAllBST, Integer maxBSTLength) {
            this.max = max;
            this.min = min;
            this.isAllBST = isAllBST;
            this.maxBSTLength = maxBSTLength;
        }
        public Info(boolean isAllBST, Integer maxBSTLength) {
            this.isAllBST = isAllBST;
            this.maxBSTLength = maxBSTLength;
        }
    }

    public static int getMaxBSTLength(Node root){
        return getTreeInfo(root).maxBSTLength;
    }

    public static Info getTreeInfo(Node node){
        if(node == null){
            return new Info(true,0);
        }
        Info leftInfo = getTreeInfo(node.left);
        Info rightInfo = getTreeInfo(node.right);
        //处理好 最大值和最小值的问题
        int max = node.value;
        int min = node.value;
        if(leftInfo.max != null){
            max = Math.max(leftInfo.max,max);
            min = Math.min(leftInfo.min,min);
        }
        if(rightInfo.max != null){
            max = Math.max(rightInfo.max,max);
            min = Math.min(rightInfo.min,min);
        }
        //如果 与 X节点没有关系 即我不是 bst树
        int maxBSTLength = Math.max(leftInfo.maxBSTLength, rightInfo.maxBSTLength);
        boolean isAllBST = false;
        //判断是不是 bst树然后更新
        if(leftInfo.isAllBST && rightInfo.isAllBST &&
                (rightInfo.min == null || rightInfo.min > node.value)&&
                (leftInfo.max == null || leftInfo.max < node.value)
        ){//这就是 bst数
            isAllBST = true;
            //这就是整个数的长度
            maxBSTLength = leftInfo.maxBSTLength + rightInfo.maxBSTLength + 1;
        }
        return new Info(max,min,isAllBST,maxBSTLength);
    }

}
