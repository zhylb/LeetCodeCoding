package com.lihd.part02;

import java.util.PriorityQueue;

/**
 * 思路如此的巧妙 令人感慨万千。
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/15 22:09
 */
public class Code05TrappingRainWaterII {

    public static class Water{
        int val;
        int row;
        int col;
        public Water(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }

    public static int water(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        PriorityQueue<Water> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        boolean[][] enterMap = new boolean[R][C];

        //把四周放进去
        // 0 行 和 最后一行 放进去
        for (int i = 0; i < C; i++) {
            heap.add(new Water(matrix[0][i], 0, i));
            enterMap[0][i] = true;

            heap.add(new Water(matrix[R - 1][i], R - 1, i));
            enterMap[R - 1][i] = true;
        }

        for (int i = 1; i < R - 1; i++) {
            heap.add(new Water(matrix[i][0], i, 0));
            enterMap[i][0] = true;

            heap.add(new Water(matrix[i][C - 1], i, C - 1));
            enterMap[i][C - 1] = true;
        }

        //初始化完成 开始计算工作
        int max = Integer.MIN_VALUE;
        int ans = 0;
        while (!heap.isEmpty()) {

            Water water = heap.poll();
            int val = water.val;
            int row = water.row;
            int col = water.col;
            max = Math.max(val, max);

            //上
            if (row > 0 && !enterMap[row - 1][col]) {
                ans += Math.max(0, max - matrix[row][col]);
                enterMap[row - 1][col] = true;
                heap.add(new Water(matrix[row - 1][col], row - 1, col));
            }

            //下
            if (row < R - 1 && !enterMap[row + 1][col]) {
                ans += Math.max(0, max - matrix[row][col]);
                enterMap[row + 1][col] = true;
                heap.add(new Water(matrix[row + 1][col], row + 1, col));
            }
            //左
            if (col > 0 && !enterMap[row][col - 1]) {
                ans += Math.max(0, max - matrix[row][col]);
                enterMap[row][col - 1] = true;
                heap.add(new Water(matrix[row][col - 1], row, col - 1));
            }

            //右
            if (col < C - 1 && !enterMap[row][col + 1]) {
                ans += Math.max(0, max - matrix[row][col]);
                enterMap[row][col + 1] = true;
                heap.add(new Water(matrix[row][col + 1], row, col + 1));
            }


        }
        return ans;
    }
}
