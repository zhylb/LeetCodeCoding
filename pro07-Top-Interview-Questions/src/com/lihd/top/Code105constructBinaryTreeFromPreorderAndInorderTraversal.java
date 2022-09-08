package com.lihd.top;

import java.util.HashMap;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/8 13:46
 */
public class Code105constructBinaryTreeFromPreorderAndInorderTraversal {
    /**
     * 主要是角标换算而已 <br/>
     * 先序遍历的第一个是头, 在中序中找到这个头, 使用map加速, 一步步来就好<br/>
     * 2 ms, faster than 98.36% , 41.7 MB, less than 98.01%<br/>
     * @param preorder
	 * @param inorder
     * @return com.lihd.top.Code105constructBinaryTreeFromPreorderAndInorderTraversal.TreeNode
     * @author lihd
     * @date 2022/9/8 15:22
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, 0, n - 1, inorder, 0, n - 1, map);
    }

    private TreeNode build(int[] preorder, int pL, int pR, int[] inorder, int iL, int iR, HashMap<Integer, Integer> map) {
        if (pL > pR) {
            return null;
        }
        // pL 和 pR 还有东西
        TreeNode head = new TreeNode(preorder[pL]);
        Integer index = map.get(preorder[pL]);
        // index - 1 - iL = ? - pL - 1
        // ? = index - iL + pL
        head.left = build(preorder, pL + 1, index - iL + pL, inorder, iL, index - 1, map);
        // pR - ? = iR - index - 1
        // ? = pR - iR + index + 1
        head.right = build(preorder, pR - iR + index + 1, pR, inorder, index + 1, iR, map);
        return head;
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
