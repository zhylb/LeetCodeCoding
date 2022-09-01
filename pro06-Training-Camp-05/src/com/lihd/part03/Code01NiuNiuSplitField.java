package com.lihd.part03;


import java.util.Scanner;

/**
 * <a href="https://www.nowcoder.com/questionTerminal/fe30a13b5fb84b339cb6cb3f70dca699?answerType=1&f=discussion">牛客链接</a>
 * 通过全部用例 运行时间 1819ms 占用内存 105840KB
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/24 18:58
 */
public class Code01NiuNiuSplitField {


    public static int maxMinSumIn16(int[][] m) {
        int R = m.length;
        int C = m[0].length;
        int[][] preSumArr = getPreSumArr(m);
        int ans = 0;
        for (int i = 0; i < C - 3; i++) {
            for (int j = i + 1; j < C - 2; j++) {
                for (int k = j + 1; k < C - 1; k++) {
                    int val = getMaxMinInOneCase(preSumArr, i, j, k);
                    ans = Math.max(val, ans);
                }
            }
        }
        return ans;
    }

    private static int getMaxMinInOneCase(int[][] preSum, int c1, int c2, int c3) {
        int[] up = generateUpDp(preSum, c1, c2, c3);
        int[] down = generateDownDp(preSum, c1, c2, c3);
        int n = preSum.length;
        int ans = 0;
        for (int i = 1; i <= n - 3; i++) {
            // i 是中间一刀
            int val = Math.min(up[i], down[i + 1]);
            ans = Math.max(val, ans);
        }
        return ans;
    }

    private static int[] generateDownDp(int[][] preSum, int c1, int c2, int c3) {
        int n = preSum.length;
        int[] dp = new int[preSum.length];
        int best = n - 2;
        dp[n - 2] = getTwoRowSumMin(n - 2, best, n - 1, c1, c2, c3, preSum);
        for (int i = n - 3; i >= 0; i--) {
            dp[i] = getTwoRowSumMin(i, best, n - 1, c1, c2, c3, preSum);
            int val;
            while (best - 1 > i && dp[i] <= (val = getTwoRowSumMin(i, best - 1, n - 1, c1, c2, c3, preSum))) {
                best --;
                dp[i] = val;
            }
        }
        return dp;
    }

    private static int[] generateUpDp(int[][] preSum, int c1, int c2, int c3) {
        int[] dp = new int[preSum.length];
        int best = 0;
        dp[1] = getTwoRowSumMin(0, best, 1, c1, c2, c3, preSum);
        for (int i = 2; i < preSum.length; i++) {
            dp[i] = getTwoRowSumMin(0, best, i, c1, c2, c3, preSum);
            int val;
            while (best + 1 < i && dp[i] <= (val = getTwoRowSumMin(0, best + 1, i, c1, c2, c3, preSum))) {
                best ++;
                dp[i] = val;
            }
        }
        return dp;
    }

    private static int getTwoRowSumMin(int rb, int rm, int re, int c1, int c2, int c3, int[][] preSum) {
        return Math.min(getRowFourSumMin(rb, rm, c1, c2, c3, preSum), getRowFourSumMin(rm + 1, re, c1, c2, c3, preSum));
    }

    private static int getRowFourSumMin(int rb, int re, int c1, int c2, int c3, int[][] preSum) {
        int a = getRangeSum(rb, 0, re, c1, preSum);
        int b = getRangeSum(rb, c1 + 1, re, c2, preSum);
        int c = getRangeSum(rb, c2 + 1, re, c3, preSum);
        int d = getRangeSum(rb, c3 + 1, re, preSum[0].length - 1, preSum);
        return Math.min(Math.min(a, b), Math.min(c, d));
    }


    private static int getRangeSum(int r1, int c1, int r2, int c2, int[][] preSum) {
        if (r1 == 0 && c1 == 0) {
            return preSum[r2][c2];
        }
        if (r1 == 0) {
            return preSum[r2][c2] - preSum[r2][c1 - 1];
        }
        if (c1 == 0) {
            return preSum[r2][c2] - preSum[r1 - 1][c2];
        }
        return preSum[r2][c2] + preSum[r1 - 1][c1 - 1] - preSum[r2][c1 - 1] - preSum[r1 - 1][c2];
    }

    private static int[][] getPreSumArr(int[][] m) {
        int R = m.length;
        int C = m[0].length;
        int[][] ans = new int[R][C];
        ans[0][0] = m[0][0];
        for (int i = 1; i < C; i++) {
            ans[0][i] = m[0][i] + ans[0][i - 1];
        }
        for (int i = 1; i < R; i++) {
            ans[i][0] = m[i][0] + ans[i - 1][0];
        }
        //初始化完成
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                ans[i][j] = m[i][j] + ans[i][j - 1] + ans[i - 1][j] - ans[i - 1][j - 1];
            }
        }
        return ans;
    }
    //        int[][] m = {{3, 3, 3, 2},
    //                {3, 2, 3, 3},
    //                {3, 3, 3, 2},
    //                {2, 3, 2, 3},};
    //        int maxMinSumIn16 = maxMinSumIn16(m);
    //        System.out.println("maxMinSumIn16 = " + maxMinSumIn16);



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] chs = sc.next().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = chs[j] - '0';
            }
        }
        int minSumIn16 = maxMinSumIn16(map);
        System.out.println(minSumIn16);
    }




}
