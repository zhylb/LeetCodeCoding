package com.lihd.part04;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/22 23:35
 */
public class LintCode435PostOfficeProblem {

    public static int postOffice(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int[][] dp = new int[n + 1][k + 1];
        int[][] best = new int[n + 1][k + 1];
        int[][] oneCost = oneCost(arr);


        for (int i = 1; i <= n; i++) {
            dp[i][1] = oneCost[0][i - 1];
        }

        for (int r = 2; r <= n; r++) {
            for (int c = k; c >= 2; c--) {
                int low = best[r - 1][c];
                int high = c == k ? r - 1 : best[r][c + 1];
                dp[r][c] = oneCost[0][r - 1];
                for (int i = low; i <= high; i++) {
                    int val = dp[i][c - 1] + oneCost[i][r - 1];
                    if (dp[r][c] >= val) {
                        dp[r][c] = val;
                        best[r][c] = i;
                    }
                }
            }
        }
        return dp[n][k];

    }

    public static int postOffice1(int[] arr, int k) {

        Arrays.sort(arr);
        int n = arr.length;
        int[][] dp = new int[n + 1][k + 1];
        int[][] oneCost = oneCost(arr);

        for (int i = 1; i <= n; i++) {
            dp[i][1] = oneCost[0][i - 1];
        }

        for (int r = 2; r <= n; r++) {
            for (int c = 2; c <= k; c++) {
                dp[r][c] = oneCost[0][r - 1];
                for (int i = 1; i <= r - 1; i++) {
                    int val = dp[i][c - 1] + oneCost[i][r - 1];
                    if (dp[r][c] >= val) {
                        dp[r][c] = val;
                    }
                }
            }
        }
        return dp[n][k];

    }


    private static int[][] oneCost(int[] arr) {
        int n = arr.length;
        int[][] ans = new int[n][n];
        for (int l = 0; l < n; l++) {
            for (int r = l + 1; r < n; r++) {
                ans[l][r] = ans[l][r - 1] + arr[r] - arr[(l + r) / 2];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 2;
        int postOffice = postOffice(arr, k);
        System.out.println("postOffice = " + postOffice);
    }

}
