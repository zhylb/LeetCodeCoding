package com.lihd.part12;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/19 17:53
 */
public class Code07Knapsack {


    public static int getMaxValue(int[] w, int[] v, int bag, int curW, int i) {
        if (curW > bag) {
            return -1;//-1表示特殊标记
        }
        if (curW == bag || i == w.length) {
            return 0;
        }

        int notStealThis = getMaxValue(w, v, bag, curW, i + 1);
        int stealThis = getMaxValue(w, v, bag, curW + w[i], i + 1);

        if (stealThis == -1) {//返回值无效 上一步打算偷东西失败了
            return notStealThis;
        }
        //stealThis != -1代表返回值有效
        return Math.max(notStealThis, stealThis + v[i]);
    }


    public static int getMaxValue(int[] w, int[] v, int i, int rest) {
        //如果背包没有容量 或者东西已经偷完 这两种情况都偷不了任何东西 返回0
        if (rest <= 0 || w.length == i) {
            return 0;
        }
        //还可以继续偷东西 还有东西可以偷 我的背包也有容量
        //我不偷这一件
        int notStealThis = getMaxValue(w, v, i + 1, rest);
        //我偷这一件 : 偷之前保证能装下
        int stealThis = 0;
        if (rest >= w[i]) {
            // 偷完背包变小
            stealThis = v[i]+ getMaxValue(w, v, i + 1, rest - w[i]);
        }
        return Math.max(notStealThis,stealThis);
    }

    public static int getMaxValueDp(int[] w, int[] v, int index, int rest) {
        int[][] dp = new int[w.length + 1][rest + 1];
        for (int i = w.length - 1; i >= 0; i--) {
            for (int j = 1; j <= rest; j++) {
                if (w[i] <= j) {
                    dp[i][j] = Math.max(dp[i + 1][j], v[i] + dp[i + 1][j - w[i]]);
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
//        System.out.println("dp = " + Arrays.deepToString(dp));
        return dp[index][rest];
    }

    public static void main(String[] args) {
        int[] w = {4, 7, 5, 3};
        int[] v = {40, 42, 25, 12};
        int bag = 10;

        int maxValueDp = getMaxValueDp(w, v, 0, bag);
        int maxValue = getMaxValue(w, v, 0, bag);
        System.out.println("maxValue = " + maxValue);
        System.out.println("maxValueDp = " + maxValueDp);

    }

}
