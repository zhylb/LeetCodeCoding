package com.lihd.part06;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/14 12:37
 */
public class Code01SplitNumber {

    public static int splitNumsForce(int N) {
        return splitNumsForce(1, N);
    }


    public static int splitNumsForce(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ans = 0;
        for (int i = pre; i <= rest; i++) {
            ans += splitNumsForce(i, rest - i);
        }
        return ans;
    }


    public static int splitNumsDp(int N) {
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }

        for (int pre = N; pre >= 1; pre--) {
            for (int rest = pre; rest <= N; rest++) {
                int ans = 0;
                for (int i = pre; i <= rest; i++) {
                    ans += dp[i][rest - i];
                }
                dp[pre][rest] = ans;
            }
        }
        return dp[1][N];
    }

    public static int splitNumsDpImprove(int N) {
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }
        dp[N][N] = dp[N][0] ;//dp[N][0] = 1;
        for (int pre = N - 1; pre >= 1; pre--) {
            for (int rest = pre; rest <= N; rest++) {
                dp[pre][rest] = dp[pre][rest - pre] + dp[pre + 1][rest];
            }
        }
        return dp[1][N];
    }


    public static void main(String[] args) {
        int N = 20;
        System.out.println(splitNumsForce(N));
        System.out.println(splitNumsDp(N));
        System.out.println(splitNumsDpImprove(N));
    }


}
