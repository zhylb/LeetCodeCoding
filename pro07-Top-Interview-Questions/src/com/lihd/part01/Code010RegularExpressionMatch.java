package com.lihd.part01;

import java.util.Random;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/1 20:58
 */
public class Code010RegularExpressionMatch {


    // 不用检测有效性, 题目说了, 如果用再加上就是
    public boolean isMatch1(String s, String p) {
        return process(0, 0, s, p);
    }

    private boolean process(int i, int j, String s, String p) {
        if (i == s.length()) {
            if (j == p.length()) {
                return true;
            }
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                return process(i, j + 2, s, p);
            }
            return false;
        }
        // i 有效, j 到了末尾, 返回false
        if (j == p.length()) {
            return false;
        }
        // i 和 j 有效
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {

            // 无论 s[i] == p[j] 我们都有让 p[j] * 这个两个字符转换成空串的情况
            if (process(i, j + 2, s, p)) {
                return true;
            }
            // p[j] * 两个字符 至少使用了 s的一个字符, 直到不满足为止
            while (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.')) {
                if (process(i + 1, j + 2, s, p)) {
                    return true;
                }
                i++;
            }
            return false;
        } else {
            return (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) && process(i + 1, j + 1, s, p);
        }
    }


    /**
     * 改成动态规划
     * 2 ms, faster than 96.95%, 40.5 MB, less than 99.54%
     * @param s
	 * @param p
     * @return boolean
     * @author lihd
     * @date 2022/9/1 22:13
     */
    public boolean isMatch(String s, String p) {

        int r = s.length();
        int c = p.length();

        boolean[][] dp = new boolean[r + 1][c + 1];
        dp[r][c] = true;

        for (int j = c - 2; j >= 0; j -= 2) {
            dp[r][j] = p.charAt(j + 1) == '*' && dp[r][j + 2];
        }

        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 0; j--) {

                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    // 无论 s[i] == p[j] 我们都有让 p[j] * 这个两个字符转换成空串的情况
                    if (dp[i][j + 2]) {
                        dp[i][j] =  true;
                        continue;
                    }
                    int index = i;
                    // p[j] * 两个字符 至少使用了 s的一个字符, 直到不满足为止
                    while (index < s.length() && (p.charAt(j) == s.charAt(index) || p.charAt(j) == '.')) {
                        if (dp[index + 1][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        index++;
                    }
                } else {
                    dp[i][j] = (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        Code010RegularExpressionMatch match = new Code010RegularExpressionMatch();
        System.out.println(match.isMatch("aa", "a*"));

    }

}
