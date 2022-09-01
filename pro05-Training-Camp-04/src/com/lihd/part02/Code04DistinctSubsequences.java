package com.lihd.part02;

/**
 * <a href="https://leetcode.com/problems/distinct-subsequences">leetcode链接</a>
 *
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/10 20:58
 */
public class Code04DistinctSubsequences {



    /**
     * <a href="https://leetcode.com/problems/distinct-subsequences">leetcode链接</a>
     * 21 ms, faster than 77.52%
     * 50 MB, less than 62.10%
     * 动态规划
     * dp[i][j]的含义 ：可以在s[0...i]上任意删除，删除出 t[0...j]的方法数
     * 行列对应模型，直接二维表，二维表讨论直接根据以 某个位置作为结尾考虑情况
     * @param s 可以删除的字符串
	 * @param t 目标字符串
     * @return int
     * @author lihd
     * @date 2022/8/10 21:13
     */
    public static int getNumDp(String s, String t) {
        char[] chs1 = s.toCharArray();
        char[] chs2 = t.toCharArray();
        int r = chs1.length;
        int c = chs2.length;
        int[][] dp = new int[r][c];
        //左上角
        dp[0][0] = chs1[0] == chs2[0] ? 1 : 0;
        //第一行 一个字符怎么也不可能删除出 一个比它还长的字符 因此都是0(不包括第一个）, 不用填

        //填好第一列的值
        for (int row = 1; row < r; row++) {
            dp[row][0] = chs1[row] == chs2[0] ? dp[row - 1][0] + 1 : dp[row - 1][0];
        }

        //朴素位置
        for (int row = 1; row < r; row++) {
            for (int col = 1; col < c; col++) {
                //这种情况是 我直接删除s的最后一个字符
                dp[row][col] = dp[row - 1][col];
                //这种情况是不删除，不删除要满足条件 ：s中的最后一个字符 == t中的最后一个字符
                if (chs1[row] == chs2[col]) {
                    //既然最后一个字符都相同，就退化为子问题。
                    dp[row][col] += dp[row - 1][col - 1];
                }
            }
        }
        return dp[r - 1][c - 1];
    }

    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(getNumDp(s, t));
    }


}
