package com.lihd.part06;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/29 23:50
 */
public class Code03IsBST {


    public static class Info{
        boolean isBST;
        Integer max;
        Integer min;

        public Info(boolean isBST) {
            this.isBST = isBST;
        }

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBST(Node head) {
        return getBSTInfo(head).isBST;
    }

    public static Info getBSTInfo(Node head) {
        if (head == null) {
            return new Info(true);
        }
        Info leftInfo = getBSTInfo(head.left);
        Info rightInfo = getBSTInfo(head.right);
        int max = head.val;
        int min = head.val;
        if (leftInfo.max != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo.max != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        boolean isBST = leftInfo.isBST && rightInfo.isBST && (leftInfo.max == null || leftInfo.max < head.val) && (rightInfo.min == null || rightInfo.min > head.val);

        return new Info(isBST, max, min);
    }


    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight;
        Integer lastVal = null;
        boolean ans = true;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight == null) {
                if (lastVal != null && lastVal >= cur.val) {
                    ans = false;
                }
                lastVal = cur.val;
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
                    if (lastVal != null && lastVal >= cur.val) {
                        ans = false;
                    }
                    lastVal = cur.val;
                    cur = cur.right;
                }
            }
        }
        return ans;

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
            boolean ans2 = isBST2(head);
            boolean ans1 = isBST(head);
            if (ans1 != ans2) {
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                Code01Morris.preMorris(head);
                break;
            }
        }
        System.out.println("test finish!");
    }





}
