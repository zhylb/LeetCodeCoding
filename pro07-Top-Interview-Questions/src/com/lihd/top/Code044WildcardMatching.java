package com.lihd.top;


/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/3 16:04
 */
public class Code044WildcardMatching {

    // 看看能不能 四边形不等式优化
    // 1 我先调整了求值的范围
    // 129 ms, faster than 7.78%, 54.3 MB, less than 21.15%
    public boolean isMatch3(String s, String p) {

        int R = s.length();
        int C = p.length();

        boolean[][] dp = new boolean[R + 1][C + 1];
        int[][] best = new int[R + 1][C + 1];
        dp[R][C] = true;

        for (int i = C - 1; i >= 0; i--) {
            dp[R][i] = dp[R][i + 1] && p.charAt(i) == '*';
        }

        for (int j = C - 1; j >= 0; j--) {
            for (int i = 0; i < R; i++) {
                if (p.charAt(j) != '*') {
                    // 这个位置不是*
                    if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                        dp[i][j] = dp[i + 1][j + 1];
                    }
                } else {
                    // 这个位置是*
                    // 这个位置不匹配任何字符到匹配所有位置
                    int small = Math.max(best[i][j + 1], i);
                    int large = i == 0 ? R : best[i - 1][j];

                    System.out.println(small + " " + large);

                    for (int k = small; k <= large; k++) {
                        if (dp[k][j + 1]) {
                            dp[i][j] = true;
                            best[i][j] = k;
                        }
                    }
                }
            }
        }
        return dp[0][0];
    }

    // 斜率优化
    // 51 ms, faster than 40.08%, 49 MB, less than 38.65%
    public boolean isMatch2(String s, String p) {

        int R = s.length();
        int C = p.length();

        boolean[][] dp = new boolean[R + 1][C + 1];
        dp[R][C] = true;

        for (int i = C - 1; i >= 0; i--) {
            dp[R][i] = dp[R][i + 1] && p.charAt(i) == '*';
        }

        for (int i = R - 1; i >= 0; i--) {
            for (int j = C - 1; j >= 0; j--) {
                if (p.charAt(j) != '*') {
                    // 这个位置不是*
                    if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                        dp[i][j] = dp[i + 1][j + 1];
                    }
                } else {
                    // 这个位置是*
                    // 这个位置不匹配任何字符到匹配所有位置
                    dp[i][j] =  dp[i + 1][j] || dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }


    /**
     * 自己写的动态规划 惨不忍睹 刚好能过, 或许能斜率优化 ? 48.2 MB, less than 63.78%
     * 220 ms, faster than 5.18% ,
     * @param s
	 * @param p
     * @return boolean
     * @author lihd
     * @date 2022/9/3 16:27
     */
    public boolean isMatch(String s, String p) {

        int R = s.length();
        int C = p.length();

        boolean[][] dp = new boolean[R + 1][C + 1];
        dp[R][C] = true;

        for (int i = C - 1; i >= 0; i--) {
            dp[R][i] = dp[R][i + 1] && p.charAt(i) == '*';
        }

        for (int i = R - 1; i >= 0; i--) {
            for (int j = C - 1; j >= 0; j--) {
                if (p.charAt(j) != '*') {
                    // 这个位置不是*
                    if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                        dp[i][j] = dp[i + 1][j + 1];
                    }
                } else {
                    // 这个位置是*
                    // 这个位置不匹配任何字符到匹配所有位置
                    boolean ans = false;
                    int index = i;
                    while (index <= s.length()) {
                        ans = ans || dp[index][j + 1];
                        index ++;
                    }
                    dp[i][j] =  ans;
                }
            }
        }
        return dp[0][0];
    }

    public boolean isMatch1(String s, String p) {
        return process(0, 0, s, p);
    }

    // s 从 i 到 结尾
    // p 从 j 到 结尾
    // 是否能 匹配
    private boolean process(int i, int j, String s, String p) {
        if (i == s.length()) {
            if (j == p.length()) {
                return true;
            }
            if (p.charAt(j) == '*') {
                return process(i, j + 1, s, p);
            }
            return false;
        }

        if (j == p.length()) {
            return false;
        }
        // i 和 j都是合法位置
        if (p.charAt(j) != '*') {
            // 这个位置不是*
            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                return process(i + 1, j + 1, s, p);
            }
            return false;
        } else {
            // 这个位置是*
            // 这个位置不匹配任何字符到匹配所有位置
            boolean ans = false;
            while (i <= s.length()) {
                ans = ans || process(i, j + 1, s, p);
                i ++;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Code044WildcardMatching matching = new Code044WildcardMatching();
        String s = "mississippi";
        String p = "m??*ss*?i*pi";
        System.out.println(matching.isMatch2(s, p));
    }
}
