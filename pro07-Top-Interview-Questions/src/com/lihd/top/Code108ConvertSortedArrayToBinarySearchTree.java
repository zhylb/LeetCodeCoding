package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/8 21:48
 */
public class Code108ConvertSortedArrayToBinarySearchTree {

    /**
     * 就是转就行了 <br/>
     * 1 ms, faster than 33.54%, 43 MB, less than 84.05%
     * @param nums 有序数组
     * @return com.lihd.top.Code108ConvertSortedArrayToBinarySearchTree.TreeNode
     * @author lihd
     * @date 2022/9/8 21:52
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int m = l + (r - l) / 2;
        TreeNode node = new TreeNode(nums[m]);
        node.left = build(nums, l, m - 1);
        node.right = build(nums, m + 1, r);
        return node;
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
