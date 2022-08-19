package com.lihd.part05;

import com.lihd.utils.ArrayUtils;
import static org.junit.Assert.*;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/13 20:32
 */
public class Code01PalindromeSubsequence {


    public static int maxPSubSeqLen(String str) {
        char[] chs = str.toCharArray();
        int n = chs.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = chs[i] == chs[i + 1] ? 2 : 1;
        }
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                if (chs[l] != chs[r]) {
                    dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                } else {
                    dp[l][r] = dp[l + 1][r - 1] + 2;
                }
            }
        }
        return dp[0][n - 1];
    }

    public static int maxPSubSeqLenByOtherDp(String s) {
        char[] chs1 = s.toCharArray();
        int n = chs1.length;
        char[] chs2 = new char[n];
        for (int i = 0; i < n; i++) {
            chs2[i] = chs1[n - i - 1];
        }
        int[][] dp = new int[n][n];
        //玩一下最长公共子序列问题
        dp[0][0] = chs1[0] == chs2[0] ? 1 : 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = chs1[i] == chs2[0] || dp[i - 1][0] == 1 ? 1 : 0;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = chs1[0] == chs2[i] || dp[0][i - 1] == 1 ? 1 : 0;
        }

        for (int r = 1; r < n; r++) {
            for (int c = 1; c < n; c++) {
                if (chs1[r] != chs2[c]) {
                    dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]);
                } else {
                    dp[r][c] = dp[r - 1][c - 1] + 1;
                }
            }
        }
        return dp[n - 1][ n - 1];
    }


    public static void main(String[] args) {
        int testTimes = 10000;
        int minLen = 5;
        int maxLen = 10;
        for (int i = 0; i < testTimes; i++) {
            String s = ArrayUtils.randomASKString(minLen, maxLen);
            int ans1 = maxPSubSeqLen(s);
            int ans2 = maxPSubSeqLenByOtherDp(s);
            int ans3 = ans.class05.Code01_PalindromeSubsequence.maxLen1(s);
            assertEquals(ans1, ans2);
            assertEquals(ans1, ans3);
        }
    }

}
