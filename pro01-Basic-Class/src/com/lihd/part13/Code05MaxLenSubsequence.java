package com.lihd.part13;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/23 16:20
 */
public class Code05MaxLenSubsequence {


    public static int getMaxLenSubsequence(String a, String b) {
        if (a == null || b == null || "".equals(a) || "b".equals(b)) {
            return 0;
        }

        char[] chs1 = a.toCharArray();
        char[] chs2 = b.toCharArray();

        int[][] dp = new int[chs1.length][chs2.length];
        dp[0][0] = chs1[0] == chs2[0] ? 1 : 0;

        for (int i = 1; i < chs1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], chs1[i] == chs2[0] ? 1 : 0);
        }

        for (int i = 1; i < chs2.length; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], chs1[0] == chs2[i] ? 1 : 0);
        }

        for (int i = 1; i < chs1.length; i++) {
            for (int j = 1; j < chs2.length; j++) {
                int max = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (chs1[i] == chs2[j]) {
                    max = Math.max(max, dp[i - 1][j - 1] + 1);
                }
                //左上角的情况不用判断
                dp[i][j] = max;
            }
        }
        return dp[chs1.length - 1][chs2.length - 1];
    }

    public static void main(String[] args) {
        String a = "ab12lcio34ijoi5";
        String b = "ciadf123dasg45l;kj6";
        int maxLenSubsequence = getMaxLenSubsequence(a, b);
        System.out.println("maxLenSubsequence = " + maxLenSubsequence);
    }


}
