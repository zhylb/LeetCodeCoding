package com.lihd.part08;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/8 19:44
 */
public class Code06PalindromeMinAdd {


    public static String minP(String s) {
        int[][] dp = getDp(s);
        char[] chs = s.toCharArray();
        int n = chs.length;

        char[] ans = new char[n + dp[0][n - 1]];
        int ansL = 0;
        int ansR = ans.length - 1;
        int row = 0;
        int col = n - 1;
        while (row <= col) {
            if (chs[row] == chs[col]) {
                ans[ansL++] = chs[row++];
                ans[ansR--] = chs[col--];
            } else if (dp[row][col - 1] < dp[row + 1][col]) {
                ans[ansL++] = chs[col];
                ans[ansR--] = chs[col];
                col --;
            } else {
                ans[ansL++] = chs[row];
                ans[ansR--] = chs[row];
                row ++;
            }
        }
        return String.valueOf(ans);
    }


    public static int[][] getDp(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = chs[i] == chs[i + 1] ? 0 : 1;
        }
        for (int row = n - 3; row >= 0; row --) {
            for (int col = row + 2; col < n; col++) {
                dp[row][col] = Math.min(dp[row + 1][col] + 1, dp[row][col - 1] + 1);
                if (chs[row] == chs[col]) {
                    dp[row][col] = Math.min(dp[row][col], dp[row + 1][col - 1]);
                }

            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String str = "AB1CD2EFG3H43IJK2L1MN";
        String minP = minP(str);
        System.out.println( minP);
    }


}
