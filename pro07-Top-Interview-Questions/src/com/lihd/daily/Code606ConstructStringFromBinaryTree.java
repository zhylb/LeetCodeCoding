package com.lihd.daily;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/7 16:10
 */
public class Code606ConstructStringFromBinaryTree {

    /**
     * 树形dp果然天下无敌了 <br/>
     * 30 ms, faster than 44.12%, 55.1 MB, less than 24.29%
     * @param root 根节点
     * @return java.lang.String
     * @author lihd
     * @date 2022/9/7 16:18
     */
    public String tree2str(TreeNode root) {
        String s = getInfo(root);
        return s.substring(1, s.length() - 1);
    }

    private String getInfo(TreeNode node) {
        if (node == null) {
            return "";
        }
        String l = getInfo(node.left);
        String r = getInfo(node.right);
        if (l.equals("") && r.equals("")) {
            return "(" + node.val + ")";
        }
        if (l.equals("")) {
            return "(" + node.val + "()" + r + ")";
        }
        return "(" + node.val + l + r + ")";
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
