package com.lihd.part04;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/22 16:23
 */
public class Code02SuperEggDrop {



    /**
     * 1 ms, faster than 95.47%
     * 41.3 MB, less than 93.20%
     * @param k
	 * @param n
     * @return int
     * @author lihd
     * @date 2022/8/22 23:18
     */
    public static int superEggDrop(int k, int n) {
        //dp[i] 代表用i个棋子, 因此多分配一个空间
        int[] dp = new int[k + 1];
        int times = 0;
        while (dp[k] < n) {
            int before = 0;
            for (int i = 1; i <= k; i++) {
                int val = dp[i] + before + 1;
                before = dp[i];
                dp[i] = val;
            }
            times ++;
        }
        return times;
    }




        public static int superEggDrop2(int k, int n) {
        // k 是鸡蛋, n是楼层
        int[][] dp = new int[n + 1][k + 1];
        int[][] best = new int[n + 1][k + 1];
        // 初始位置填好
        for (int i = 0; i <= n; i++) {
            dp[i][1] = i;
            best[i][1] = 1;
        }
        for (int r = 1; r <= n; r++) {
            for (int c = k; c >= 2; c--) {
                int low = best[r - 1][c];
                int high = c == k ? r - 1 : best[r][c + 1];
                dp[r][c] = r;
                for (int i = low; i <= high; i++) {
                    int val = 1 + Math.max(dp[i][c - 1], dp[r - i - 1][c]);
                    if (dp[r][c] >= val) {// 为啥这里要大于等于, 感觉写成大于最多变慢.为啥就错了
                        dp[r][c] = val;
                        best[r][c] = i;
                    }
                }
            }
        }
        return dp[n][k];
    }



    public static int superEggDrop1(int k, int n) {
        // k 是鸡蛋, n是楼层
        int[][] dp = new int[n + 1][k + 1];
        // 初始位置填好
        for (int i = 0; i <= n; i++) {
            dp[i][1] = i;
        }
        for (int r = 1; r <= n; r++) {
            for (int c = 2; c <= k; c++) {
                dp[r][c] = r;
                for (int i = 0; i <= r - 1; i++) {
                    int val = 1 + Math.max(dp[i][c - 1], dp[r - i - 1][c]);
                    if (dp[r][c] >= val) {
                        dp[r][c] = val;
                    }
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        int k = 3;
        int n = 26;
        int superEggDrop = superEggDrop(k, n);
        int superEggDrop1 = superEggDrop1(k,n);
        System.out.println("superEggDrop = " + superEggDrop);
        System.out.println("superEggDrop1 = " + superEggDrop1);
    }
}
