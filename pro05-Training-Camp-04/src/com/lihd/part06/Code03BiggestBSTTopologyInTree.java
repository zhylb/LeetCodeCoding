package com.lihd.part06;


/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/16 22:32
 */
public class Code03BiggestBSTTopologyInTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info{
        int leftVal;
        int rightVal;

        public Info(int leftVal, int rightVal) {
            this.leftVal = leftVal;
            this.rightVal = rightVal;
        }
    }






    public static Info getInfo(Node node) {

        if (node.left == null && node.right == null) {
            return new Info(0, 0);
        }

        if (node.left != null) {
            Info info = getInfo(node.left);
        }
        if (node.right != null) {

        }

        return null;





    }

    public static Node findPrecursor(Node node) {
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }


    public static Node findSuccessor(Node node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


}
