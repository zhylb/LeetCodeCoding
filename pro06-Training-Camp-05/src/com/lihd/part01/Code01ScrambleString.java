package com.lihd.part01;

/**
 * Runtime: 9 ms, faster than 93.29% of Java online submissions for Scramble String.
 * Memory Usage: 43.9 MB, less than 82.54% of Java online submissions for Scramble String.
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/20 20:11
 */
public class Code01ScrambleString {

    /**
     * 返回两个字符是否是 扰乱字符串
     * 三维动态规划, 已经通过leetcode的测试
     * @param s1 第一个字符串
	 * @param s2 第二个字符串
     * @return boolean
     * @author lihd
     * @date 2022/8/20 21:30
     */
    public static boolean isScramble(String s1, String s2) {
        if (!isScrambleStringCheck(s1, s2)) {
            return false;
        }
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int n = chs1.length;
        boolean[][][] dp = new boolean[n][n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][i][j] = chs1[i] == chs2[j];
            }
        }
        for (int l1 = n - 2; l1 >= 0; l1--) {
            for (int r1 = l1 + 1; r1 < n; r1++) {
                for (int l2 = n - 2; l2 >= 0; l2--) {
                    //开始填表
                    int r2 = l2 + r1 - l1;
                    if (r2 < l2 || r2 >= n) {
                        continue;
                    }
                    for (int mid = l1; mid < r1; mid++) {
                        if (dp[l1][mid][l2] && dp[mid + 1][r1][mid + 1 + r2 - r1]
                                ||
                            dp[l1][mid][r2 + l1 - mid] && dp[mid + 1][r1][l2]
                        ) {
                            dp[l1][r1][l2] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][n - 1][0];
    }

    private static boolean isScrambleForce(String s1, String s2) {
        if (!isScrambleStringCheck(s1, s2)) {
            return false;
        }
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        return isScrambleForce(0, chs1.length - 1, 0, chs1, chs2);
    }

    /**
     * 返回 两个字符串是否是变位词
     * @param l1 第一个字符串开始位置
	 * @param r1 第一个字符串结束位置
	 * @param l2 第二个字符串开始位置
	 * @param chs1 第一个字符串
	 * @param chs2 第二个字符串
     * @return boolean
     * @author lihd
     * @date 2022/8/20 21:28
     */
    private static boolean isScrambleForce(int l1, int r1, int l2, char[] chs1, char[] chs2) {
        if (l1 == r1) {
            //说明范围上只要一个数
            return chs1[l1] == chs2[l2];
        }
        //说明范围上 还有一些数
        int r2 = l2 + r1 - l1;
        for (int mid = l1; mid < r1; mid++) {
            if(
                    isScrambleForce(l1, mid, l2, chs1, chs2)
                    &&
                    isScrambleForce(mid + 1, r1, mid + 1 + r2 - r1 , chs1, chs2)
                    ||
                    isScrambleForce(l1, mid, r2 + l1 - mid, chs1,chs2)
                    &&
                    isScrambleForce(mid + 1, r1, l2, chs1,chs2)
            ){
                return true;
            }
        }
        return false;
    }



    /**
     * 过滤条件
     * @param s1 第一个字符串
	 * @param s2 第二个字符串
     * @return boolean 返回两个是否有可能称为扰乱字符串
     * @author lihd
     * @date 2022/8/20 21:31
     */
    private static boolean isScrambleStringCheck(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        // 长度相等 , 每个字符个数必须完全相同
        int[] map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            // s1中的每个字符 设为正
            map[s1.charAt(i) - 'a']++;
            // s2中每个字符 设为符
            map[s2.charAt(i) - 'a']--;
        }
        for (int j : map) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "abb";
        String s2 = "bba";
        boolean scrambleForce = isScrambleForce(s1, s2);
        System.out.println("scrambleForce = " + scrambleForce);
        boolean scramble = isScramble(s1, s2);
        System.out.println("scramble = " + scramble);
    }
}
