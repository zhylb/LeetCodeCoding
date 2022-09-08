package com.lihd.follow;

import ans.follow.MaxSum;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/8 23:12
 */
public class Add04MaxSum {
    // 不能选相邻的数, 问最大子序列和是多少 ?
    // 假设子序列可以什么也不选
    public static int maxSum(int[] arr) {
        // dp[i] 代表从0到i按规则选取的最好情况
        int[] dp = new int[arr.length];
        dp[0] = Math.max(0, arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= 0) {
                // 我直接不选
                dp[i] = dp[i - 1];
            } else if (i > 1) {
                // 保证不越界
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
            }
        }
        return dp[arr.length - 1];
    }

    // 最少选一个的话
    public static int maxSumAtLeastPickOne(int[] arr) {
        // dp[i] 代表从0到i按规则选取的最好情况
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            // 只选我自己的情况
            dp[i] = arr[i];
            // dp[i - 1]是不选我自己
            // dp[i - 2] + arr[i] 是选了我自己然后 选在dp[i - 2]规模选
            if (i > 1) {
                dp[i] = Math.max(Math.max(dp[i], dp[i - 1]), dp[i - 1] + arr[i]);
            }
        }
        return dp[arr.length - 1];
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int[] arr = { -10, -9, -25, -3, -6, -31, -19 };
        System.out.println(maxSum(arr));
        System.out.println(maxSumAtLeastPickOne(arr));
    }
}
