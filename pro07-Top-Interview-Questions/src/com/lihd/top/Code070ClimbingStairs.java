package com.lihd.top;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/8 15:38
 */
public class Code070ClimbingStairs {

    /**
     * 指标上是最优解logN , 但是 实际效果比较差<br/>
     * 1 ms, faster than 10.03%, 41.2 MB, less than 21.51%<br/>
     * @param n
     * @return int
     * @author lihd
     * @date 2022/9/8 16:02
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        // n >= 3时
        int[][] m = {{1, 1}, {1, 0}};
        int[][] power = power(m, n - 2);
        return 2 * power[0][0] + power[1][0];
    }

    private int[][] power(int[][] m, int p){
        int[][] ans = new int[m.length][m.length];
        for (int i = 0; i < m.length; i++) {
            ans[i][i]  = 1;
        }
        int[][] base = m;
        while (p != 0) {
            if ((p & 1) == 1) {
                ans = multi(base, ans);
            }
            p = p >> 1;
            base = multi(base, base);
        }
        return ans;
    }

    // 两个矩阵相乘
    private int[][] multi(int[][] a, int[][] b) {
        int[][] ans = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    ans[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code070ClimbingStairs m = new Code070ClimbingStairs();
        System.out.println(m.climbStairs(3));
        int[][] a = {{1, 0}, {0, 1}};
        int[][] b = {{1, 1}, {1, 0}};
        System.out.println(Arrays.deepToString(m.multi(a, b)));
    }


}
