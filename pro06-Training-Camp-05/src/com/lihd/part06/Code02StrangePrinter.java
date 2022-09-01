package com.lihd.part06;

/**
 * <a href="https://leetcode.com/problems/strange-printer/submissions/">...</a>
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/29 11:59
 */
public class Code02StrangePrinter {
    public static int strangePrinter(String s) {
        return process(0, s.length() - 1, s.toCharArray());
    }
    /**
     * 30 ms, faster than 72.65%
     * 44 MB, less than 52.56%
     * 记得把方法名后面的dp去掉
     * @param s
     * @return int
     * @author lihd
     * @date 2022/8/29 15:03
     */
    public static int strangePrinterDp(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }


        for (int l = n - 2; l >= 0; l--) {
            for (int r = l + 1; r < n; r++) {
                int ans = Integer.MAX_VALUE;
                for (int i = l; i < r; i++) {
//                    int val = process(l, i, chs) + process(i + 1, r, chs);
                    int val = dp[l][i] + dp[i + 1][r];
                    if (chs[l] == chs[i + 1]) {
                        val -= 1;
                    }
                    ans = Math.min(ans, val);
                }
                dp[l][r] = ans;
            }
        }
        return dp[0][n - 1];
    }


    private static int process(int l, int r, char[] chs) {
        if (l == r) {
            return 1;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = l; i < r; i++) {
            int val = process(l, i, chs) + process(i + 1, r, chs);
            if (chs[l] == chs[i + 1]) {
                val -= 1;
            }
            ans = Math.min(ans, val);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "aaabcbbaaa";
        System.out.println(strangePrinter(s));
        System.out.println(strangePrinterDp(s));
    }
}
