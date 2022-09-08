package com.lihd.top;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/7 16:29
 */
public class Code103BinaryTreeZigzagLevelOrderTraversal {

    /**
     * 转过来 转过去确实烦 <br/>
     * 可能是设计的不合理 ? 有点慢 , 我就觉得用链表的list太慢了<br/>
     * 2 ms, faster than 37.06%, 42.5 MB, less than 75.42%
     * @param root 根节点
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @author lihd
     * @date 2022/9/7 16:53
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root, 1));
        int curLevel = 1;
        LinkedList<Integer> list = new LinkedList<>();
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            TreeNode node = poll.node;
            int nodeLevel = poll.level;

            if(nodeLevel != curLevel){
                // 到下一行了
                ans.add(list);
                curLevel++;
                list = new LinkedList<>();
            }
            if (nodeLevel % 2 != 0) {
                // 正着来
                list.addLast(node.val);
            } else {
                // 倒着来
                list.addFirst(node.val);
            }

            if (node.left != null) {
                queue.add(new Node(node.left, nodeLevel + 1));
            }
            if (node.right != null) {
                queue.add(new Node(node.right, nodeLevel + 1));
            }
        }
        ans.add(list);
        return ans;
    }


    private static class Node{
        TreeNode node;
        int level;

        public Node(TreeNode node, int level) {
            this.node = node;
            this.level = level;
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
