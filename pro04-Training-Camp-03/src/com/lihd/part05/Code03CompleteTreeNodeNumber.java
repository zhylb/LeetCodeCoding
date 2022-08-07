package com.lihd.part05;


/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/4 13:27
 */
public class Code03CompleteTreeNodeNumber {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }


    public static int getNum(Node node) {
        if (node == null) {
            return 0;
        }
        int nh = leftHeight(node);
        int rh = leftHeight(node.right);
        if (nh == rh + 2) {
            return (1 << rh) + getNum(node.left);
        } else {
            return (1 << rh) + getNum(node.right);
        }
    }

    public static int leftHeight(Node node) {
        int ans  = 0;
        while (node != null) {
            node = node.left;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        System.out.println(getNum(head));

    }




}
