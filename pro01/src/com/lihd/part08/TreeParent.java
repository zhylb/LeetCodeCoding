package com.lihd.part08;

/**
 * 查找一个节点的前驱结点 后继节点
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/6 19:50
 */
public class TreeParent {
    public static class Node{
        int value;
        Node left;
        Node right;
        Node parent;
        public Node(int value) {
            this.value = value;
        }
    }
    //找到一个节点的后继节点
    public static Node getSuccessor(Node root){
        if(root == null){
            return null;
        }
        if(root.right != null) {
            return root.right;
        }

        Node parent = root.parent;
        Node cur = root;
        while(parent != null && cur != parent.right){
            cur = parent;
            parent = parent.parent;
        }
        return parent;
    }
    //找到一个节点的前驱节点
    public static Node getPrecursor(Node root){
        if(root == null){
            return null;
        }
        Node parent = root.parent;
        if(parent != null && parent.right == root) return parent;
        Node son = root.left;
        while(son != null && son.right != null){
            son = son.right;
        }
        return son;
    }

    public static void main(String[] args) {

    }
}
