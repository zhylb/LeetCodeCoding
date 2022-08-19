package com.lihd.part06;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/14 12:38
 */
public class Code02KInversePairs {

    public static int maxPairs(int N, int K) {
        int[][] dp = new int[N + 1][K + 1];
        //填好第一列 不包含第一个位置
        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
        }
        //从第三行开始填 前两行除了第一列都是0
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                //简写后代码
                for (int begin = j; begin >= Math.max(0, j - i + 1); begin--) {
                    dp[i][j] += dp[i - 1][begin];
                }
            }
        }
        return dp[N][K];
    }

    public static int maxPairsImprove(int N, int K) {


        int[][] dp = new int[N + 1][K + 1];
        //填好第一列 不包含第一个位置
        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
        }
        //从第三行开始填 前两行除了第一列都是0
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                //自己画图推一下 很简单的.
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - (j - i >= 0 ? dp[i - 1][j - i] : 0);
            }
        }
        return dp[N][K];
    }


    public static void main(String[] args) {
        int N = 9;
        int K = 15;
        System.out.println(maxPairs(N, K));
        System.out.println(maxPairsImprove(N, K));
    }

}
