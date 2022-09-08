package com.lihd.follow;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/8 23:26
 */
public class Code687LongestUnivaluePath {

    /**
     * 树形dp即可 , 我不懂为什么这么慢, 明明只遍历一遍啊<br/>
     * 11 ms, faster than 9.65%, 72.8 MB, less than 5.05%<br/>
     * @param root 根节点
     * @see com.lihd.top.Code124BinaryTreeMaximumPathSum 差不多的一个题目
     * @return int
     * @author lihd
     * @date 2022/9/8 23:39
     */
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 我们返回的是最多节点个数, 最多路径数量应该减一
        return getInfo(root).max - 1;
    }

    private Info getInfo(TreeNode node) {
        if (node == null) {
            return new Info(0, 0, 0);
        }
        Info leftInfo = getInfo(node.left);
        Info rightInfo = getInfo(node.right);
        // 必须以我出发的节点
        int start = 1;
        // 必须经过我的节点
        int contains = 1;
        int add  = 0;
        if (leftInfo.start != 0 && node.left.val == node.val) {
            add = leftInfo.start;
            contains += leftInfo.start;
        }
        if (rightInfo.start != 0 && node.right.val == node.val) {
            add = Math.max(rightInfo.start, add);
            contains += rightInfo.start;
        }
        start += add;
        int max = Math.max(Math.max(leftInfo.max, rightInfo.max), Math.max(start, contains));
        return new Info(max, contains, start);
    }

    private static class Info{
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
