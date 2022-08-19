package com.lihd.part01;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/11 9:23
 */
public class Code01LongestIncreasingPath {

    public static int all;
    public static int hit;

    /**
     * 暴力递归调度函数
     * 注意每一个位置的调度，因为每个位置都可能是递增链的开始
     * @param map 二维数组
     * @return int 最长递增链的长度
     * @author lihd
     * @date 2022/8/11 9:44
     */
    public static int maxIncreasingPath(int[][] map) {
        int R = map.length;
        int C = map[0].length;

        int ans = 0;

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                int val = maxIncreasingPath(map, row, col);
                ans = Math.max(ans, val);
            }
        }
        return ans;

    }

    /**
     * 暴力递归
     * 注意 这个暴力递归 最少返回1
     * @param map 二维数组
	 * @param row 当前所在行
	 * @param col 当前所在列
     * @return int 返回这个位置出发的最长递增链
     * @author lihd
     * @date 2022/8/11 9:45
     */
    public static int maxIncreasingPath(int[][] map, int row, int col) {
        int R = map.length;
        int C = map[0].length;
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;
        if (row + 1 < R && map[row][col] < map[row + 1][col]) {
            p1 = maxIncreasingPath(map, row + 1, col);
        }
        if (row - 1 >= 0 && map[row][col] < map[row - 1][col]) {
            p2 = maxIncreasingPath(map, row - 1, col);
        }
        if (col + 1 < C && map[row][col] < map[row][col + 1]) {
            p3 = maxIncreasingPath(map, row, col + 1);
        }
        if (col - 1 >= 0 && map[row][col] < map[row][col - 1]) {
            p4 = maxIncreasingPath(map, row, col - 1);
        }
        return 1 + Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }


    /**
     * 动态规划 调度函数
     * 注意每一个位置的调度
     * @param map 二维数组 最长递增链的长度
     * @return int
     * @author lihd
     * @date 2022/8/11 9:47
     */
    public static int maxIncreasingPathDp(int[][] map) {
        int R = map.length;
        int C = map[0].length;
        int[][] dp = new int[R][C];
        int ans = 0;

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                int val = maxIncreasingPathDp(map,dp, row, col);
                ans = Math.max(ans, val);
            }
        }


        return maxIncreasingPathDp(map, dp, 0, 0);
    }


    /**
     * 暴力递归 + 记忆化搜索
     * 命中后记得把 结果加到缓存
     * @param map 二维数组
	 * @param dp dp表
	 * @param row 当前所在行
	 * @param col 当前所在列
     * @return int
     * @author lihd
     * @date 2022/8/11 9:46
     */
    public static int maxIncreasingPathDp(int[][] map, int[][] dp, int row, int col) {
        int R = map.length;
        int C = map[0].length;

        all++;
        if (dp[row][col] != 0) {
            hit++;
            return dp[row][col];
        }

        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;
        if (row + 1 < R && map[row][col] < map[row + 1][col]) {
            p1 = maxIncreasingPathDp(map, dp, row + 1, col);
        }
        if (row - 1 >= 0 && map[row][col] < map[row - 1][col]) {
            p2 = maxIncreasingPathDp(map, dp, row - 1, col);
        }
        if (col + 1 < C && map[row][col] < map[row][col + 1]) {
            p3 = maxIncreasingPathDp(map, dp, row, col + 1);
        }
        if (col - 1 >= 0 && map[row][col] < map[row][col - 1]) {
            p4 = maxIncreasingPathDp(map, dp, row, col - 1);
        }
        dp[row][col] = 1 + Math.max(Math.max(p1, p2), Math.max(p3, p4));
        return dp[row][col];
    }




    public static void main(String[] args) {
        int[][] map = {
                {5, 4, 3,},
                {3, 1, 2,},
                {2, 1, 3,},
        };

        System.out.println(maxIncreasingPath(map));
        System.out.println(maxIncreasingPathDp(map));

        System.out.println("hit = " + hit);
        System.out.println("all = " + all);
    }


}
