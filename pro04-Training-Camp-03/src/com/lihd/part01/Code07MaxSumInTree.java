package com.lihd.part01;

import ans.class06.Main;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/9 22:53
 */
public class Code07MaxSumInTree {
    //定义节点
    public static class Node {
        int val;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
        }
    }

    //=================================
    // 问题一解决方案
    //=================================
    public static int a;

    public static int getMaxPathSum1(Node head) {
        a = 0;
        getMaxPathSum1(head,0);
        return a;
    }

    public static void getMaxPathSum1(Node head, int preSum) {
        if (head.left == null && head.right == null) {
            a = Math.max(a, head.val + preSum);
        } else {
            if (head.left != null) {
                getMaxPathSum1(head.left, preSum + head.val);
            }
            if (head.right != null) {
                getMaxPathSum1(head.right, preSum + head.val);
            }
        }
    }

    //=================================
    // 问题二解决方案
    //=================================
    public static class Info {
        int maxNum;
        int maxHeadNum;
        public Info(int maxNum, int maxHeadNum) {
            this.maxNum = maxNum;
            this.maxHeadNum = maxHeadNum;
        }
    }

    public static int getMaxPathSum2(Node head) {
        if (head == null) {
            return -1;
        }
        return getMaxPathInfo(head).maxNum;
    }

    public static Info getMaxPathInfo(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = getMaxPathInfo(head.left);
        Info rightInfo = getMaxPathInfo(head.right);
        int p1 = leftInfo != null ? leftInfo.maxNum : head.val;
        int p2 = rightInfo != null ? rightInfo.maxHeadNum : head.val;
        int p3 = head.val;
        int p4 = leftInfo != null ? head.val + leftInfo.maxHeadNum : head.val;
        int p5 = rightInfo != null ? head.val + rightInfo.maxHeadNum : head.val;
        int maxNum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(p4, p5));
        int maxHeadNum = Math.max(Math.max(p3, p4), p5);
        return new Info(maxNum, maxHeadNum);
    }
    //=================================
    // 问题三 解决方案
    //=================================

    public static int getMaxPathSum3(Node head) {
        if (head == null) {
            return -1;
        }
        return getMaxPathUpInfo(head).maxNum;
    }


    public static Info getMaxPathUpInfo(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = getMaxPathUpInfo(head.left);
        Info rightInfo = getMaxPathUpInfo(head.right);
        int p1 = leftInfo != null ? leftInfo.maxNum : head.val;
        int p2 = rightInfo != null ? rightInfo.maxNum : head.val;
        int p3 = head.val;
        int p4 = leftInfo != null ? head.val + leftInfo.maxHeadNum : head.val;
        int p5 = rightInfo != null ? head.val + rightInfo.maxHeadNum : head.val;
        int p6 = leftInfo != null && rightInfo != null ? head.val + leftInfo.maxHeadNum + rightInfo.maxHeadNum : head.val;
        int maxNum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(Math.max(p4, p5), p6));
        int maxHeadNum = Math.max(Math.max(p3, p4), p5);
        return new Info(maxNum, maxHeadNum);
    }

    //=================================
    // 问题四 解决方案
    //=================================

    public static int b;

    public static int getMaxPathSum4(Node head) {
        if (head.left == null && head.right == null) {
            b = Math.max(b, head.val);
            return head.val;
        }

        int max = Integer.MIN_VALUE;
        //下面两个循环至少会进入一个
        if (head.left != null) {
            max = Math.max(getMaxPathSum4(head.left), max);
        }
        if (head.right != null) {
            max = Math.max(getMaxPathSum4(head.right), max);
        }

        max += head.val;
        b = Math.max(b, max);
        return max;

    }

}

