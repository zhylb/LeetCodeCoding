package com.lihd.part08;

/**
 * Runtime: 2 ms, faster than 92.43% of Java online submissions for Minimum Cost to Merge Stones.
 * Memory Usage: 41.6 MB, less than 81.35% of Java online submissions for Minimum Cost to Merge Stones.
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/19 10:32
 */
public class Code05MinimumCostToMergeStones {

    public static int mergeStones(int[] stones, int k) {

        int[] preSum = new int[stones.length + 1];
        preSum[0] = 0;
        for (int i = 0; i < stones.length; i++) {
            preSum[i + 1] += preSum[i] + stones[i];
        }

        int[][][] dp = new int[stones.length][stones.length][k + 1];
        return processDp(0, stones.length - 1, 1, k, stones, preSum, dp);
    }


    public static int processDp(int l, int r, int n, int k, int[] stones, int[] preSum, int[][][] dp) {
        if (l == r) {
            return n == 1 ? 0 : -1;
        }
        if (dp[l][r][n] != 0) {
            return dp[l][r][n];
        }
        if (n == 1) {
            //要分割成一份
            int next = processDp(l, r, k, k, stones, preSum, dp);
            if (next == -1) {
                dp[l][r][n] = -1;
                return -1;
            } else {
                dp[l][r][n] = next + preSum[r + 1] - preSum[l];
                return dp[l][r][n];
            }
        }
        // n > 1
        int ans = Integer.MAX_VALUE;
        for (int i = l; i < r; i += k - 1) {
            int next1 = processDp(l, i, 1, k, stones, preSum,dp);
            int next2 = processDp(i + 1, r, n - 1, k, stones, preSum, dp);
            if (next1 != -1 && next2 != -1) {
                ans = Math.min(ans, next1 + next2);
            }
        }
        dp[l][r][n] = ans == Integer.MAX_VALUE ? -1 : ans;
        return dp[l][r][n];
    }

    /**
     * 暴力递归
     * 返回从l,到r,合并成n个数所需要的最小代价
     * 范围上的尝试(l,r) + 业务限制(k) + 从左到右的尝试(for循环 i = l)
     * @param l 开始位置
	 * @param r 结束位置
	 * @param n 合成分数的目标
	 * @param k 要合并的k
	 * @param stones 要合并的数组
	 * @param preSum 前缀和数组 加速查询
     * @return int 返回从l,到r,合并成n个数所需要的最小代价
     * @author lihd
     * @date 2022/8/19 19:06
     */
    public static int process(int l, int r, int n, int k, int[] stones, int[] preSum) {
        if (l == r) {
            return n == 1 ? 0 : -1;
        }
        if (n == 1) {
            //要分割成一份
            int next = process(l, r, k, k, stones, preSum);
            if (next == -1) {
                return -1;
            } else {
                return next + preSum[r + 1] - preSum[l];
            }
        }
        // n > 1
        int ans = Integer.MAX_VALUE;
        for (int i = l; i < r; i += k - 1) {
            int next1 = process(l, i, 1, k, stones, preSum);
            int next2 = process(i + 1, r, n - 1, k, stones, preSum);
            if (next1 != -1 && next2 != -1) {
                ans = Math.min(ans, next1 + next2);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
