package com.lihd.part08;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/8 20:31
 */
public class Code04MoneyProblem {
    //暴力尝试
    public static long minAbilityForce(int[] d, int[] p, int ability, int index) {
        if (index == d.length) {
            return 0;
        }
        //还有怪兽
        if (ability < d[index]) {
            //干不过，只能买
            return p[index] + minAbilityForce(d, p, ability + d[index], index + 1);
        } else {
            //干得过，两种选择
            return Math.min(
                    //买
                    p[index] + minAbilityForce(d, p, ability + d[index], index + 1),
                    //不买
                    minAbilityForce(d, p, ability, index + 1)
            );
        }
    }

    //根据上面的动态规划改出来的
    public static long minnAbilityDp(int[] d, int[] p) {
        int sum = 0;
        for (int i : d) {
            sum += i;
        }
        int n = d.length;
        long[][] dp = new long[n + 1][sum + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int ability = sum; ability >= 0; ability--) {
                if (ability + d[index] > sum) {
                    continue;
                }
                if (ability < d[index]) {
                    dp[index][ability] = p[index] + dp[index + 1][ability + d[index]];
                } else {
                    dp[index][ability] = Math.min(
                            p[index] + dp[index + 1][ability + d[index]],
                            dp[index + 1][ability]
                    );

                }
            }
        }
        return dp[0][0];
    }

    //必须刚好使用完 money , 返回获取能力的最大值 算了 这个暴力递归感觉难写
    public static long minMoneyForce(int[] d, int[] p,int index, int money) {
        return 0;
    }

    public static long minMoneyDp(int[] d, int[] p) {

        int sum = 0;
        for (int i : p) {
            sum += i;
        }
        int n = d.length;
        int[][] dp = new int[n][sum + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }

        dp[0][p[0]] = d[0];
        for (int index = 1; index < n; index++) {
            for (int money = 0; money <= sum; money++) {
                //花费
                if (money - p[index] >= 0 && dp[index - 1][money - p[index]] > 0) {
                    dp[index][money] = dp[index - 1][money - p[index]] + d[index];
                }
                if (dp[index - 1][money] >= d[index]) {
                    dp[index][money] = Math.max(dp[index - 1][money], dp[index][money]);
                }
            }
        }

        for (int i = 0; i <= sum; i++) {
            if (dp[n - 1][i] != -1) {
                return i;
            }
        }
        return -1;
    }


    public static int[][] generateTwoRandomArray(int len, int value) {
        int size = (int) (Math.random() * len) + 1;
        int[][] arrs = new int[2][size];
        for (int i = 0; i < size; i++) {
            arrs[0][i] = (int) (Math.random() * value) + 1;
            arrs[1][i] = (int) (Math.random() * value) + 1;
        }
        return arrs;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 20;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[][] arrs = generateTwoRandomArray(len, value);
            int[] d = arrs[0];
            int[] p = arrs[1];
            long ans1 = minAbilityForce(d, p, 0, 0);
            long ans2 = minnAbilityDp(d, p);
            long ans3 = minMoneyDp(d, p);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                System.out.println("d = " + Arrays.toString(d));
                System.out.println("p = " + Arrays.toString(p));
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("ans3 = " + ans3);
                break;
            }
        }

    }
}
