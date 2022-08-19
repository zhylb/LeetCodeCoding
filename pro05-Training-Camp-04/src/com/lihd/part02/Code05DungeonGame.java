package com.lihd.part02;

/**
 * 骑士拯救公主的故事
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/10 21:18
 */
public class Code05DungeonGame {

    /**
     * 暴力递归的调度函数
     * @param map 地图
     * @return int
     * @author lihd
     * @date 2022/8/10 22:05
     */
    public static int minHpForce(int[][] map) {
        return minHpForce(map, 0, 0);
    }

    /**
     * 暴力递归
     * 这里骑士所处的位置 是还没有真正踩上去时的情况，意思是在当前位置，骑士还没有吃血包或者打怪兽
     * 因此这个递归要处理好当前骑士所有的位置。
     * @param map 地图
	 * @param row 骑士当前行
	 * @param col 骑士当前列
     * @return int 从row,col走到最后至少需要的血量
     * @author lihd
     * @date 2022/8/10 22:03
     */
    public static int minHpForce(int[][] map, int row, int col) {
        int R = map.length;
        int C = map[0].length;
        if (row == R - 1 && col == C - 1) {
            //最后一个位置 如果是负数 要减去这个负数的值，无论如何最少保证1血量
            return map[row][col] < 0 ? 1 - map[row][col] : 1;
        }
        int next = 0;
        if (row == R - 1) {
            //最后一行，只能向右走 next是下一个需要的最少血量
            next = minHpForce(map, row, col + 1);
        } else if (col == C - 1) {
            next = minHpForce(map, row + 1, col);
        } else {
            //普遍位置
            next = Math.min(minHpForce(map, row + 1, col), minHpForce(map, row, col + 1));
        }

        if (map[row][col] >= next) {
            //我所处的位置 >= next >= 1，是个正数
            return 1;
        } else {
            //我所处的位置 比next小，我满足我自己的情况下要满足下一个条件
            //比如我的值是3， next的值是 5 我需要再加两个血
            return next - map[row][col];
        }

    }

    /**
     * 由上面的暴力递归修改为动态规划
     * dp[i][j] 代表从当前位置走到最后位置最少需要的血量
     * @param map 地图
     * @return int
     * @author lihd
     * @date 2022/8/10 21:58
     */
    public static int minHpDp(int[][] map) {
        int R = map.length;
        int C = map[0].length;
        int[][] dp = new int[R][C];
        dp[R - 1][C - 1]  = map[R - 1][C - 1] < 0 ? 1 - map[R - 1][C - 1] : 1;
        for (int row = R - 2; row >= 0; row--) {
            int next = dp[row + 1][C - 1];
            dp[row][C - 1] = map[row][C - 1] >= next ? 1 : next - map[row][C - 1];
        }
        for (int col = C - 2; col >= 0; col--) {
            int next = dp[R - 1][col + 1];
            dp[R - 1][col] = map[R - 1][col] >= next ? 1 : next - map[R - 1][col];
        }
        for (int row = R - 2; row >= 0; row--) {
            for (int col = C - 2; col >= 0; col--) {
                int next = Math.min(dp[row + 1][col], dp[row][col + 1]);
                dp[row][col] = map[row][col] >= next ? 1 : next - map[row][col];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] map = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 }, };
        System.out.println(minHpForce(map));
        System.out.println(minHpDp(map));
    }



}
