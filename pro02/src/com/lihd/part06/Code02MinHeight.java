package com.lihd.part06;

import com.lihd.ans.class06.Code05_MinHeight;

import javax.print.DocFlavor;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/29 21:31
 */
public class Code02MinHeight {

    public static int minHeight3(Node head) {
        if (head == null) {
            return 0;
        }
        return minHeight2(head);
    }

    public static int minHeight2(Node head) {
        if (head.left == null && head.right == null) {
            return 1;
        }
        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        if (head.left != null) {
            left = minHeight2(head.left);
        }
        if (head.right != null) {
            right = minHeight2(head.right);
        }
        return Math.min(left, right) + 1;
    }

    public static int minHeight(Node head) {
        if (head == null) {
            return 0;
        }
        Node cur = head;
        Node mostRight;
        int level = 0;
        int move;
        int min = Integer.MAX_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight == null) {
                level++;
                cur = cur.right;
            } else {
                move = 1;
                while (mostRight.right != null && mostRight.right != cur) {
                    move ++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    level ++;
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    if (mostRight.left == null) {
                        //叶节点
                        min = Math.min(min, level);
                    }
                    level -= move;
                    cur = cur.right;
                    mostRight.right = null;

                }
            }
        }
        Node right = head;
        int l = 1;
        while (right.right != null) {
            right = right.right;
            l++;
        }
        if (right.left == null) {
            min = Math.min(l, min);
        }

        return min;
        
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int treeLevel = 7;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(treeLevel, nodeMaxValue);
            int ans1 = minHeight(head);
            int ans2 = minHeight3(head);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                Code01Morris.preMorris(head);
                break;
            }
        }
        System.out.println("test finish!");
    }
}
