package com.lihd.part02;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/27 10:29
 */
public class Code02HorseJump {

    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    //马踏棋盘问题
    //返回 从 x, y跳 k次到0,0的所有方法数
    public static int jump(int x, int y, int K) {
        if (K == 0) {
            return x == 0 && y == 0 ? 1 : 0;
        }
        //还能跳, 检查当前位置是否合理
        if (x < 0 || y < 0 || x >= HEIGHT || y >= WIDTH) {
            return 0;
        }
        //还能跳 当前位置合理
        K --;
        return
                jump(x + 1, y + 2, K) +
                jump(x + 1, y - 2, K) +
                jump(x - 1, y + 2, K) +
                jump(x - 1, y - 2, K) +
                jump(x + 2, y + 1, K) +
                jump(x + 2, y - 1, K) +
                jump(x - 2, y + 1, K) +
                jump(x - 2, y - 1, K) ;

    }


    public static int jumpDp(int x, int y, int K) {

        int[][][] dp = new int[HEIGHT][WIDTH][K + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= K; i++) {
            //1 层一层填入
            for (int j = 0; j < HEIGHT; j++) {
                for (int k = 0; k < WIDTH; k++) {
                    // j代表行 k 代表列
                    dp[j][k][i] =
                            getValue(dp, j + 1, k + 2, i - 1) +
                            getValue(dp, j + 1, k - 2, i - 1) +
                            getValue(dp, j - 1, k + 2, i - 1) +
                            getValue(dp, j - 1, k - 2, i - 1) +
                            getValue(dp, j + 2, k + 1, i - 1) +
                            getValue(dp, j + 2, k - 1, i - 1) +
                            getValue(dp, j - 2, k - 1, i - 1) +
                            getValue(dp, j - 2, k + 1, i - 1) ;
                }
            }
        }
        return dp[x][y][K];
    }

    public static int getValue(int[][][] dp, int j, int k, int i) {
        if (j < 0 || j >= HEIGHT || k < 0 || k >= WIDTH) {
            return 0;
        }
        return dp[j][k][i];
    }



    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int k = 10;
        System.out.println(jump(x, y, k));
        System.out.println(jumpDp(x, y, k));
    }

}
