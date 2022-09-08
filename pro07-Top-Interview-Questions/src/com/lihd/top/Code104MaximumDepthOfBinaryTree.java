package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/8 13:45
 */
public class Code104MaximumDepthOfBinaryTree {
    /**
     * dfs 或者bfs都行吧 <br/>
     * 0 ms, faster than 100.00%, 43.4 MB, less than 33.74%
     * @param root 根节点
     * @return int
     * @author lihd
     * @date 2022/9/8 13:50
     */
    public int maxDepth(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int level) {
        if (node == null) {
            return level;
        } else {
            return Math.max(dfs(node.left, level + 1), dfs(node.right, level + 1));
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
