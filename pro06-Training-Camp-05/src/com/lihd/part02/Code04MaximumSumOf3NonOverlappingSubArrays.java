package com.lihd.part02;

import java.time.Period;
import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/24 13:30
 */
public class Code04MaximumSumOf3NonOverlappingSubArrays {
    /**
     * 7 ms, faster than 41.09%
     * 54.6 MB, less than 70.79%
     * @param nums 原数组
	 * @param k 3 子数组的长度
     * @return int[] 三个子数组的开始位置 因此长度固定是3
     * @author lihd
     * @date 2022/8/24 15:54
     */
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] leftIndex = new int[n];
        int[] rightIndex = new int[n];
        int[] preSum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            // preSum 计算方式比较奇怪 如果要求 [l,R]上的累加和 -> preSum[R + 1] - preSum[L]
            // 这样的好处是不用判断越界 因此要多申请一个空间
            preSum[i] += preSum[i - 1] + nums[i - 1];
        }

        leftIndex[k - 1] = 0;
        for (int i = k; i < n; i++) {
            leftIndex[i] = i - k + 1;
            if (startKLenSum(leftIndex[i], k, preSum) <= startKLenSum(leftIndex[i - 1], k, preSum)) {
                leftIndex[i] = leftIndex[i - 1];
            }
        }

        rightIndex[n - k] = n - k;
        for (int i = n - k - 1; i >= 0; i--) {
            rightIndex[i] = i;
            if (startKLenSum(rightIndex[i], k, preSum) < startKLenSum(rightIndex[i + 1], k, preSum)) {
                rightIndex[i] = rightIndex[i + 1];
            }
        }

        int max = 0;
        int[] ans = new int[3];
        for (int i = k; i <= n - 2 * k; i++) {
            int val = startKLenSum(leftIndex[i - 1], k, preSum) + startKLenSum(rightIndex[i + k], k, preSum) + startKLenSum(i, k, preSum);
            if (val > max) {
                max = val;
                ans[0] = leftIndex[i - 1];
                ans[1] = i;
                ans[2] = rightIndex[i + k];
            }
        }
        return ans;
    }

    private static int startKLenSum(int startIndex, int k, int[] preSum) {
        return preSum[startIndex + k] - preSum[startIndex];
    }




    public static int maxSumOf3Array(int[] arr, int k) {
        // 假设参数合理
        int n = arr.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            // preSum 计算方式比较奇怪 如果要求 [l,R]上的累加和 -> preSum[R + 1] - preSum[L]
            // 这样的好处是不用判断越界 因此要多申请一个空间
            preSum[i] += preSum[i - 1] + arr[i - 1];
        }
        int[] leftDp = new int[n];
        int[] rightDp = new int[n];
        // leftDp[k - 1] 代表0...k - 1的累加和 刚好k个值
        leftDp[k - 1] = preSum[k] - preSum[0];
        for (int i = k; i < n; i++) {
            // 长度是 k, 结束位置是 i, 因此累加和的最后索引是 i + 1
            // 于是开始位置索引是 i + 1 - k , preSum[i + 1] - preSum[i + 1 - k]
            leftDp[i] = Math.max(leftDp[i - 1], preSum[i + 1] - preSum[i + 1 - k]);
        }
        rightDp[n - k] = preSum[n] - preSum[n - k];
        for (int i = n - k - 1; i >= 0; i--) {
            // 开始位置是i ,求 i开始向右k个值的累加和 preSum[i + k] - preSum[i]
            rightDp[i] = Math.max(rightDp[i + 1], preSum[i + k] - preSum[i]);
        }
        int ans = 0;
        for (int i = k; i <= n - 2 * k; i++) {
            //枚举所有的合理位置
            int val = preSum[i + k] - preSum[i];
            ans = Math.max(ans, val + leftDp[i - 1] + rightDp[i + k]);
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 1, 2, 1, 2, 1};
        int k = 2;
        int maxSumOf3Array = maxSumOf3Array(nums, k);
        System.out.println("maxSumOf3Array = " + maxSumOf3Array);
        System.out.println(Arrays.toString(maxSumOfThreeSubarrays(nums, k)));
    }

}
