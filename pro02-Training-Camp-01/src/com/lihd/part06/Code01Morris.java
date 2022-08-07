package com.lihd.part06;


/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/29 20:09
 */
public class Code01Morris {
    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                //找到前驱节点
                if (mostRight.right == cur) {
                    mostRight.right = null;
                    System.out.print(cur.val + " ");
                    cur = cur.right;
                } else {
                    mostRight.right = cur;
                    System.out.print(cur.val + " ");
                    cur = cur.left;
                }
            } else {
                System.out.print(cur.val + " ");
                cur = cur.right;   
            }
        }
        System.out.println();
    }

    public static void preMorris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight == null) {
                System.out.print(cur.val + " ");
                cur = cur.right;
            } else {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.val + " ");
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }

    public static void inMorris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight == null) {
                System.out.print(cur.val + " ");
                cur = cur.right;
            } else {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    System.out.print(cur.val + " ");
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }


    public static void postMorris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight == null) {
                cur = cur.right;
            } else {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    printNodeReverseRight(cur.left);
                    cur = cur.right;
                }
            }
        }
        printNodeReverseRight(head);
        System.out.println();
    }


    public static void printNodeReverseRight(Node node) {
        Node newHead = reverseNodeThrowRight(node);
        Node cur = newHead;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseNodeThrowRight(newHead);
    }

    public static Node reverseNodeThrowRight(Node node) {
        Node pre = null;
        Node next;
        while (node != null) {
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }



    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        morris(head);
        preMorris(head);
        inMorris(head);
        postMorris(head);
    }
}
