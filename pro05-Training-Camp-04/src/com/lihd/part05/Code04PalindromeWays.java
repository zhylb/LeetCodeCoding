package com.lihd.part05;
import ans.class05.Code04_PalindromeWays;
import java.util.UUID;
import static org.junit.Assert.*;
/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/13 19:28
 */
public class Code04PalindromeWays {

    /**
     * 获取切割字符串得到的最多的回文串方案,注意空串不算回文串,单个字符算
     * @param str 要求的字符串
     * @return int 切割字符串得到的最多的回文串方案
     * @author lihd
     * @date 2022/8/13 19:33
     */
    public static int pWays(String str) {
        char[] chs = str.toCharArray();
        int n = chs.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        //第二个对角线, AA 答案是3, AB答案是2 ,因此这么填
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = chs[i] == chs[i + 1] ? 3 : 2;
        }

        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                dp[l][r] = dp[l + 1][r] + dp[l][r - 1] - dp[l + 1][r - 1];
                if (chs[l] == chs[r]) {
                    //这个 1代表我比你多一个空串的情况
                    dp[l][r] += dp[l + 1][r - 1] + 1;
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            String s = UUID.randomUUID().toString();
            int ways = pWays(s);
            int way2 = Code04_PalindromeWays.way2(s);
            assertEquals(way2,ways);
        }
    }

}
