package com.lihd.part04;

import java.util.Arrays;

/**
 * 邮局选址问题
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/21 20:45
 */
public class Code03PostOfficeProblem {
    public static int postOffice(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // lint code 传入数组是无序的, 要先排序, 如果有序就别排了.
        Arrays.sort(arr);
        int n = arr.length;
        int[][] dp = new int[n][k + 1];
        int[][] best = new int[n][k + 1];
        int[][] oneCost = oneCost(arr);
        // 不用填第一列 best也不用
        // 由于第一行的值 就是0 , 不用填 best也不用
        // 填好第二列的值
        for (int i = 0; i < n; i++) {
            dp[i][1] = oneCost[0][i];
            // 这个best其实也不用填, 为了便于理解就写上了
            best[i][1] = 0;
        }
        //普遍位置
        for (int r = 1; r < n; r++) {
            // dp[r][c] 使用r - 1个居民点布置 c个邮局, 如果c  >= r - 1 一定是0, 不用填
            for (int c = k; c >= 2; c--) {
                int low = best[r - 1][c];
                int high = c == k ? r - 1 : best[r][c + 1];
                dp[r][c] = Integer.MAX_VALUE;
//                dp[r][c] = dp[r - 1][c - 1] + oneCost[r - 1 + 1][r];
                for (int i = high; i >= low; i--) {
                    // 这里的 i + 1是因为, 我们不用管i位置, 这是 c - 1个邮局负责的
                    int curAns = dp[i][c - 1] + oneCost[i + 1][r];
                    if (dp[r][c] > curAns) {
                        dp[r][c] = curAns;
                        // 为啥这里不是 i + 1 呀 我去
                        // 这里的r更多的是
                        best[r][c] = i;
                    }
                }
            }
        }

        /*for (int c = 2; c <= k ; c++) {
            for (int r = n - 1; r >= 1; r--) {
                int low = best[r][c - 1];
                int high = r == n - 1 ? r - 1 : best[r + 1][c];
                dp[r][c] = Integer.MAX_VALUE;
                for (int i = high; i >= low; i--) {
                    // 这里的 i + 1是因为, 我们不用管i位置, 这是 c - 1个邮局负责的
                    int curAns = dp[i][c - 1] + oneCost[i + 1][r];
                    if (dp[r][c] > curAns) {
                        dp[r][c] = curAns;
                        // 为啥这里不是 i + 1 呀 我去
                        // 这里的r更多的是
                        best[r][c] = i;
                    }
                }
            }
        }*/
        return dp[n - 1][k];
    }

    public static int postOffice2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // lint code 传入数组是无序的, 要先排序, 如果有序就别排了.
        Arrays.sort(arr);
        int n = arr.length;
        int[][] dp = new int[n][k + 1];
        int[][] oneCost = oneCost(arr);
        // 不用填第一列
        // 由于第一行的值 就是0 , 不用填
        // 填好第二列的值
        for (int i = 0; i < n; i++) {
            dp[i][1] = oneCost[0][i];
        }
        //普遍位置
        for (int r = 1; r < n; r++) {
            // dp[r][c] 使用r - 1个居民点布置 c个邮局, 如果c  >= r - 1 一定是0, 不用填
            for (int c = 2; c <= k && c <= r; c++) {
                dp[r][c] = Integer.MAX_VALUE;
//                dp[r][c] = dp[r - 1][c - 1] + oneCost[r - 1 + 1][r];
                for (int i = r - 1; i >= 0; i--) {
                    // 这里的 i + 1是因为, 我们不用管i位置, 这是 c - 1个邮局负责的
                    int curAns = dp[i][c - 1] + oneCost[i + 1][r];
                    dp[r][c] = Math.min(dp[r][c], curAns);
                }
            }
        }
        return dp[n - 1][k];
    }

    private static int[][] oneCost(int[] arr) {
        int n = arr.length;
        int[][] oneCost = new int[n][n];
        for (int i = 0; i < n; i++) {
            oneCost[i][i] = 0;
        }
        for (int l = n - 2; l >= 0; l--) {
            for (int r = l + 1; r < n; r++) {
                oneCost[l][r] = oneCost[l][r - 1] + arr[r] - arr[(l + r) / 2];
            }
        }
        return oneCost;
    }

    public static void main(String[] args) {
        int[] arr = {284,282,720,638,429,282,240,634,688,767,313,176,779,173,464,297,269,288,692,781,150,397,422,403,405,347};
        int k = 4;
        System.out.println(postOffice(arr, k));
        System.out.println(postOffice2(arr, k));

    }


}
