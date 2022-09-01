package com.lihd.part06;

/**
 * 题目描述 :
 * 整型数组arr长度为n(3 <= n <= 10^4)，最初每个数字是<=200的正数且满足如下条件： 1. 0位置的要求：arr[0] <=
 * arr[1] 2. n-1位置的要求：arr[n-1] <= arr[n-2] 3. 中间i位置的要求：arr[i] <= max(arr[i-1],
 * arr[i+1]) 但是在arr有些数字丢失了，比如k位置的数字之前是正数，丢失之后k位置的数字为0。 请你根据上述条件，
 * 计算可能有多少种不同的arr可以满足以上条件。 比如 [6,0,9] 只有还原成 [6,9,9]满足全部三个条件，所以返回1种。 [6,9,9] 达标
 *
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/27 23:16
 */
public class Code04RestoreWays {


    public static int waysForce(int[] arr) {

        if (arr[arr.length - 1] != 0) {
            return waysForce(arr.length - 1, arr[arr.length - 1], 2, arr);
        }
        int ans = 0;
        for (int val = 1; val <= 200; val++) {
            ans += waysForce(arr.length - 1, val, 2, arr);
        }
        return ans;
    }

    /**
     *
     * @param i
	 * @param v
	 * @param s 代表和后面的值的关系 0 代表比后面的数小, 1相等, 2 比后面的值大
	 * @param arr
     * @return int
     * @author lihd
     * @date 2022/8/27 23:22
     */
    private static int waysForce(int i, int v, int s, int[] arr) {
        if (arr[i] != 0 && arr[i] != v) {
            // 我不能随意变 , 也和你期待的值不一样
            return 0;
        }

        if (i == 0) {
            // 来到最后位置
            return s == 0 || s == 1 ? 1 : 0;
        }

        int ans = 0;
        if (s == 0 || s == 1) {
            // 前一个位置可以随意变
            for (int val = 1; val <= 200; val++) {
                int status = val > v ? 2 : (val == v ? 1 : 0);
                ans += waysForce(i - 1, val, status, arr);
            }
        } else {
            // 前一个位置只能大于等于当前位置 , 当前位置的值是v,而不是arr[i](因为arr[i]可能是0)
            ans += waysForce(i - 1, v, 1, arr);
            for (int val = v + 1; val <= 200; val++) {
                ans += waysForce(i - 1, val, 2, arr);
            }
        }
        return ans;
    }


    public static int waysDp(int[] arr) {


        int n = arr.length;
        int[][][] dp = new int[n][201][3];

        // 先填第0行
        for (int val = 1; val <= 200; val++) {
            if (arr[0] == 0 || arr[0] == val) {
                dp[0][val][0] = 1;
                dp[0][val][1] = 1;
            }
        }

        // 其他位置
        for (int i = 1; i < n; i++) {
            for (int v = 1; v <= 200; v++) {
                for (int s = 0; s <= 2; s++) {
                    if (arr[i] != 0 && arr[i] != v) {
                        continue;
                    }
                    int ans = 0;
                    if (s == 0 || s == 1) {
                        // 前一个位置可以随意变
                        for (int val = 1; val <= 200; val++) {
                            int status = val > v ? 2 : (val == v ? 1 : 0);
                            ans += dp[i-1][val][status];
                        }
                    } else {
                        ans += dp[i - 1][v][1];
                        for (int val = v + 1; val <= 200; val++) {
                            ans += dp[i - 1][val][2];
                        }
                    }
                    dp[i][v][s] =  ans;
                }
            }
        }
        if (arr[n - 1] != 0) {
            return dp[n - 1][arr[n - 1]][2];
        }
        int ans = 0;
        for (int val = 0; val <= 200; val++) {
            ans += dp[n - 1][val][2];
        }
        return ans;
    }

    public static int waysDpImprove(int[] arr) {


        int n = arr.length;
        int[][][] dp = new int[n][201][3];

        // 先填第0行
        for (int val = 1; val <= 200; val++) {
            if (arr[0] == 0 || arr[0] == val) {
                dp[0][val][0] = 1;
                dp[0][val][1] = 1;
            }
        }

        // 其他位置
        for (int i = 1; i < n; i++) {
            for (int v = 1; v <= 200; v++) {
                for (int s = 0; s <= 2; s++) {
                    if (arr[i] != 0 && arr[i] != v) {
                        continue;
                    }
                    int ans = 0;
                    if (s == 0 || s == 1) {
                        // 前一个位置可以随意变
                        for (int val = 1; val <= 200; val++) {
                            int status = val > v ? 2 : (val == v ? 1 : 0);
                            ans += dp[i-1][val][status];
                        }
                    } else {
                        ans += dp[i - 1][v][1];
                        for (int val = v + 1; val <= 200; val++) {
                            ans += dp[i - 1][val][2];
                        }
                    }
                    dp[i][v][s] =  ans;
                }
            }
        }
        if (arr[n - 1] != 0) {
            return dp[n - 1][arr[n - 1]][2];
        }
        int ans = 0;
        for (int val = 0; val <= 200; val++) {
            ans += dp[n - 1][val][2];
        }
        return ans;
    }
}
