package com.lihd.part04;

import java.sql.ResultSet;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/22 15:23
 */
public class Code01SplitArrayLargestSum {

    /**
     * 最优解
     * 1 ms, faster than 87.73%
     * 41.4 MB, less than 76.26%
     * @param nums
	 * @param m
     * @return int
     * @author lihd
     * @date 2022/8/22 15:53
     */
    public static int splitArray(int[] nums, int m) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 在 sum/m , sum上玩二分, 找到 符合条件的最小的值
        int l = sum / m;
        int r = sum;
        int ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int minPainter = minPainter(nums, mid);
            if (minPainter > m) {
                //说明 要求太严格了, 画家比较多
                l = mid + 1;
            } else {
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }

    /**
     *
     * @param nums 原数组
	 * @param maxNum 每一份最大是多少
     * @return int 至少需要多少画家
     * @author lihd
     * @date 2022/8/22 15:58
     */
    private static int minPainter(int[] nums, int maxNum) {
        int sum = 0;
        int ans = 0;
        for (int num : nums) {

            if (num > maxNum) {
                //这个条件不能少， 否则 结果一定有问题
                return Integer.MAX_VALUE;
            }

            if (sum + num > maxNum) {
                sum = 0;
                ans++;
            }
            sum += num;
        }
        if (sum > 0) {
            ans++;
        }
        return ans;
    }


    /**
     *
     * 6 ms, faster than 19.76%
     * 42.5 MB, less than 12.40%
     * @param nums
	 * @param m
     * @return int
     * @author lihd
     * @date 2022/8/22 15:52
     */
    public static int splitArray2(int[] nums, int m) {
        int n = nums.length;
        int[] preSum = new int[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        int[][] dp = new int[n][m + 1];
        int[][] best = new int[n][m + 1];
        for (int i = 1; i <= m; i++) {
            dp[0][i] = nums[0];
        }
        for (int i = 0; i < n; i++) {
            dp[i][1] = preSum[i];
        }
        for (int r = 1; r < n; r++) {
            for (int c = m; c >= 2; c--) {
                int low = best[r - 1][c];
                int high = c == m ? r - 1 : best[r][c + 1];
                dp[r][c] = preSum[r];
                for (int i = low; i <= high; i++) {
                    int val = Math.max(preSum[r] - preSum[i], dp[i][c - 1]);
                    if (dp[r][c] > val) {
                        dp[r][c] = val;
                        best[r][c] = i;
                    }
                }
            }
        }
        return dp[n - 1][m];
    }


    /**
     * 一遍过 还是很牛逼的 宝岛未老, 不过没优化, 数据较差
     * 118 ms, faster than 7.87%
     * 41.8 MB, less than 51.30%
     * @param nums
	 * @param m
     * @return int
     * @author lihd
     * @date 2022/8/22 15:45
     */
    public static int splitArray1(int[] nums, int m) {
        int n = nums.length;
        int[] preSum = new int[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        int[][] dp = new int[n][m + 1];

        for (int i = 1; i <= m; i++) {
            dp[0][i] = nums[0];
        }
        for (int i = 0; i < n; i++) {
            dp[i][1] = preSum[i];
        }
        for (int r = 1; r < n; r++) {
            for (int c = 2; c <= m; c++) {
                // 最后一个画家全画了
                dp[r][c] = preSum[r];
                for (int i = 0; i <= r - 1; i++) {
                    int val = Math.max(preSum[r] - preSum[i], dp[i][c - 1]);
                    if (dp[r][c] > val) {
                        dp[r][c] = val;
                    }
                }
            }
        }
        return dp[n - 1][m];
    }

    public static void main(String[] args) {
        int[] nums = {1,4,4};
        int m = 3;
        System.out.println(minPainter(nums, 5));
        System.out.println(splitArray(nums, m));
        System.out.println(splitArray2(nums, m));
    }
}
