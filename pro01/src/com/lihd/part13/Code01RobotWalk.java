package com.lihd.part13;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/20 20:18
 */
public class Code01RobotWalk {
    public static int robotWalk(int N, int M, int K, int P) {
        return walk(N, M, K, P);
    }
    public static int walk(int N, int cur, int rest, int P) {
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }
        //还有步数
        if (cur == 1) {
            return walk(N, cur + 1, rest - 1, P);
        }
        if (cur == N) {
            return walk(N, cur - 1, rest - 1, P);
        }
        return walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);
    }
    public static int robotWalk2(int N, int M, int K, int P) {
        int[][] dp = new int[N + 1][K + 1];
        dp[P][0] = 1;
        for (int i = 1; i <= K; i++) {
            //处理 j = 1也就是边界
            dp[1][i] = dp[2][i - 1];
            for (int j = 2; j < N; j++) {
                dp[j][i] = dp[j + 1][i - 1] + dp[j - 1][i - 1];
            }
            dp[N][i] = dp[N - 1][i - 1];
        }
        return dp[M][K];
    }

    public static int robotWalk3(int N, int M, int K, int P) {
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i < N + 1 ; i++) {
            for (int j = 0; j < K + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return walk3(N, M, K, P, dp);
    }

    public static int walk3(int N, int i, int rest, int P, int[][] dp) {
        if (dp[i][rest] != -1) {
            return dp[i][rest];
        }
        if (rest == 0) {
            dp[i][rest] =  i == P ? 1 : 0;
            return dp[i][rest];
        }
        if (i == 1) {
            dp[i][rest] = walk3(N, i + 1, rest - 1, P, dp);
        } else if (i == N) {
            dp[i][rest] = walk3(N, i - 1, rest - 1, P, dp);
        } else {
            dp[i][rest] = walk3(N, i + 1, rest - 1, P, dp) + walk3(N, i - 1, rest - 1, P, dp);
        }
        return dp[i][rest];
    }

    public static void main(String[] args) {
        int robotWalk = robotWalk(7, 4, 9, 5);
        System.out.println("robotWalk = " + robotWalk);
        int robotWalk2 = robotWalk2(7, 4, 9, 5);
        System.out.println("robotWalk2 = " + robotWalk2);
        int robotWalk3 = robotWalk3(7, 4, 9, 5);
        System.out.println("robotWalk3 = " + robotWalk3);
    }

}
