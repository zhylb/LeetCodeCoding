package com.lihd.top;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/8 21:56
 */
public class Code116PopulatingNextRightPointersInEachNode {

    /**
     * 层序遍历即可 <br/>
     * 5 ms, faster than 20.84%, 47.4 MB, less than 50.04%
     * @param root 根节点
     * @return com.lihd.top.Code116PopulatingNextRightPointersInEachNode.Node
     * @author lihd
     * @date 2022/9/8 22:06
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node prev = null;
        Node poll = null;
        while (!queue.isEmpty()) {
            int size = queue.size();
            prev = queue.poll();
            if (prev.left != null) {
                queue.add(prev.left);
            }
            if (prev.right != null) {
                queue.add(prev.right);
            }
            for (int i = 0; i < size - 1; i++) {
                poll = queue.poll();
                prev.next = poll;
                prev = poll;
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }

            }
        }
        return root;
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
