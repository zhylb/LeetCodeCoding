package com.lihd.part07;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 记忆化搜索的方法, 可以通过北大的acm测试
 * 时间复杂度 不知道,可能和命中率有关 ?
 * 时间复杂度 可能和 数据状况有关
 *
 * @author lihd
 * @date 2022/8/27 10:50
 */
public class Code02PavingTileMemory {
    public static long pavingTile(int r, int c) {
        int lastStatus = (1 << c) - 1;
        long[][] dp = new long[r][1 << c + 1];
        for (long[] longs : dp) {
            Arrays.fill(longs, -1);
        }
        return pavingTile(0, lastStatus, r, c, dp);
    }

    // 如果 1 代表贴过, 0 代表没贴过
    private static long pavingTile(int row, int lastStatus, int rowEnd, int colEnd, long[][] dp) {
        if (row == rowEnd) {
            return lastStatus == (1 << colEnd) - 1 ? 1 : 0;
        }


        int max = (1 << colEnd) - 1;
        int status = (~lastStatus) & max;


        if (dp[row][status] != -1) {
            return dp[row][status];
        }
        long ans = dfs(0, status, row, rowEnd, colEnd, dp);
        dp[row][status] = ans;
        return ans;
    }

    private static long dfs(int col, int status, int row, int rowEnd, int colEnd, long[][] dp) {
        if (col == colEnd) {
            return pavingTile(row + 1, status, rowEnd, colEnd, dp);
        }
        long ans = dfs(col + 1, status, row, rowEnd, colEnd, dp);

        boolean notPaving1 = (status & (1 << col)) == 0;
        boolean notPaving2 = (status & (1 << col + 1)) == 0;
        if (col + 1 < colEnd && notPaving1 && notPaving2) {
            status |= (1 << col);
            status |= (1 << col + 1);
            ans += dfs(col + 2, status, row, rowEnd, colEnd, dp);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a;
        int b;
        while ((a = sc.nextInt()) != 0 && (b = sc.nextInt()) != 0) {
            System.out.println(pavingTile(a, b));
        }
    }

}
