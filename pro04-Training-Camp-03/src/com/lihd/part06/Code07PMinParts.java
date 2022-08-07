package com.lihd.part06;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/5 13:04
 */
public class Code07PMinParts {
    public static int getMinParts(@NotNull String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        //一般情况
        char[] chs = s.toCharArray();
        int n = chs.length;
        boolean[][] dpMap = getDpMap(chs);
        int[] dp = new int[n + 1];
        for (int begin = n - 1; begin >= 0; begin--) {
            dp[begin] = n;
            for (int end = begin; end < n; end++) {
                //从 begin - end
                if(dpMap[begin][end]){
                    dp[begin] = Math.min(dp[begin], dp[end + 1] + 1);
                }
            }
        }
        return dp[0];
    }


    public static boolean[][] getDpMap(char[] chs) {
        int n = chs.length;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = chs[i] == chs[i + 1];
        }
        for (int row = n - 3; row >= 0; row--) {
            for (int col = row + 2; col < n; col++) {
                dp[row][col] = dp[row + 1][col - 1] && chs[row] == chs[col];
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String test = "aba12321412321TabaKFK";
        System.out.println(getMinParts(test));
    }

}
