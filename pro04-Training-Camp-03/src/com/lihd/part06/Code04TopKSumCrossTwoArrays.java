package com.lihd.part06;

import java.util.PriorityQueue;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/7 12:48
 */
public class Code04TopKSumCrossTwoArrays {


    public static class Node{
        int r;
        int c;
        int val;

        public Node(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }


    public static int[] topKSum(int[] arrA, int[] arrB, int topK) {


        //大根堆。
        PriorityQueue<Node> heap = new PriorityQueue<>((a,b)->b.val - a.val);

        int lenA = arrA.length;
        int lenB = arrB.length;

        //访问过的表
        boolean[][] map = new boolean[lenA][lenB];

        int[] ans = new int[topK];
        int ansIndex = 0;

        //初始化
        heap.add(new Node(lenA - 1, lenB - 1, arrA[lenA - 1] + arrB[lenB - 1]));
        map[lenA - 1][lenB - 1] = true;

        for (int i = 0; i < topK; i++) {
            Node node = heap.poll();
            ans[i] = node.val;
            int r = node.r;
            int c = node.c;
            if (r > 0 && !map[r - 1][c]) {
                heap.add(new Node(r - 1, c, arrA[r - 1] + arrB[c]));
                map[r - 1][c] = true;
            }
            if (c > 0 && !map[r][c - 1]) {
                heap.add(new Node(r, c - 1, arrA[r] + arrB[c - 1]));
                map[r][c - 1] = true;
            }
        }
        return ans;

    }


}
