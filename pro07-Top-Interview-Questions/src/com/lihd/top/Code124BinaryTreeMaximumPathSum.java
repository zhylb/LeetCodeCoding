package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/8 13:19
 */
public class Code124BinaryTreeMaximumPathSum {

    /**
     * 树形dp是无敌的 !!! <br/>
     * 2 ms, faster than 17.72%, 49.7 MB, less than 5.02%<br/>
     * @param root 根节点
     * @see com.lihd.follow.Code687LongestUnivaluePath 差不多的一个题目
     * @return int
     * @author lihd
     * @date 2022/9/8 13:33
     */
    public int maxPathSum(TreeNode root) {
        return getInfo(root).max;
    }

    private Info getInfo(TreeNode node) {
        if (node == null) {
            return new Info(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        Info leftInfo = getInfo(node.left);
        Info rightInfo = getInfo(node.right);
        // 必须以当前节点作为开头
        int start = node.val;
        int add = 0;
        // 必须经过当前节点
        int contains = node.val;
        if (leftInfo.start > 0) {
            add = leftInfo.start;
            contains += leftInfo.start;
        }
        if (rightInfo.start > 0) {
            add = Math.max(add, rightInfo.start);
            contains += rightInfo.start;
        }
        start += add;
        // 可以不经过当前节点
        int max = Math.max(Math.max(leftInfo.max, rightInfo.max), Math.max(start, contains));
        return new Info(max, contains, start);
    }


    private static class Info {
        int max;
        int contains;
        int start;

        public Info(int max, int contains, int start) {
            this.max = max;
            this.contains = contains;
            this.start = start;
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
