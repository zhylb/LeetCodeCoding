package com.lihd.part08;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/30 9:20
 */
public class Code01CreateMaximumNumber {
    /**
     * 10 ms, 在所有 Java 提交中击败了 51.94% 的用户
     * 43.8 MB, 在所有 Java 提交中击败了5.34% 的用户
     * @param nums1 第一个数组
     * @param nums2 第二个数组
     * @param k 要选的k个数
     * @return int[] 选出k个数字构成的数组
     */
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[][] dp1 = getDp(nums1);
        int[][] dp2 = getDp(nums2);
        int[] ans = new int[k];
        for (int i = Math.max(0, k - n2); i <= Math.min(k, n1); i++) {
            // i 是 n1 选的个数, j 是 n2选的个数
            int j = k - i;
            int[] best1 = getBest(i, nums1, dp1);
            int[] best2 = getBest(j, nums2, dp2);
            // 进行merge操作
            int[] merge = merge(best1, best2);
            if (arrLessEqualThan(ans, merge)) {
                ans = merge;
            }
        }
        return ans;
    }
    /**
     * 按照最好的方式合并两个数组
     * @param a 数组a
	 * @param b 数组b
     * @return int[] 最优方式合并后的数组 长度为两个数组长度之和
     * @author lihd
     * @date 2022/8/30 12:31
     */
    private static int[] merge(int[] a, int[] b) {

        int[] ans = new int[a.length + b.length];
        int ansIndex = 0;
        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {
            int best = selectBest(a, b, i, j);
            if (best == 0) {
                ans[ansIndex++] = a[i++];
            } else {
                ans[ansIndex++] = b[j++];
            }
        }

        while (i < a.length) {
            ans[ansIndex++] = a[i++];
        }
        while (j < b.length) {
            ans[ansIndex++] = b[j++];
        }
        return ans;
    }


    /**
     * 在a 和 b中选择 最好的
     * 如果 先选 a最后结果比较大, 就返回 0
     * 如果 先选 b最后结果比较大, 就返回 1
     * @param a 数组a
	 * @param b 数组b
	 * @param i a的开始索引
	 * @param j b的开始索引
     * @return int
     * @author lihd
     * @date 2022/8/30 10:37
     */
    private static int selectBest(int[] a, int[] b, int i, int j) {
        while (i < a.length && j < b.length && a[i] == b[j]) {
            i ++;
            j ++;
        }
        if (i == a.length) {
            return 1;
        }
        if (j == b.length) {
            return 0;
        }
        return a[i] <= b[j] ? 1 : 0;
    }




    /**
     * 选出k个最好的数, 返回一个数组
     * @param k 要选的k个数
	 * @param arr 要在那个数组中选择
	 * @param dp 动态规划表
     * @return int[]
     * @author lihd
     * @date 2022/8/30 12:30
     */
    private static int[] getBest(int k, int[] arr, int[][] dp) {
        int[] ans = new int[k];
        int index = -1;
        while (k > 0) {
            index = dp[index + 1][k--];
            ans[ans.length - k - 1] = arr[index];
        }
        return ans;
    }


    /**
     * dp[i][j] 的含义是 从 i 到末尾 必须选择 j个数的最优开始索引<br/>
     * 即 dp[i][j] 存放是的是索引
     * @param arr 原数组
     * @return int[][] 二维dp表
     * @author lihd
     * @date 2022/8/30 9:21
     */
    private static int[][] getDp(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n + 1];

        // 先填第一个对角线
        for (int i = n - 1; i >= 0; i--) {
            dp[i][n - i] = i;
        }
        // 普遍位置
        for (int r = n - 2; r >= 0; r--) {
            for (int c = 1; c < n - r; c++) {
                // 是否选择当前位置作为开头
                if (arr[r] >= arr[dp[r + 1][c]]) {
                    dp[r][c] = r;
                } else {
                    dp[r][c] = dp[r + 1][c];
                }
            }
        }
        return dp;
    }
    /**
     * 判断数组 a 是否小于 数组 b
     * @param a 数组a
	 * @param b 和a等长的数组
     * @return boolean
     * @author lihd
     * @date 2022/8/30 12:29
     */
    private static boolean arrLessEqualThan(int[] a, int[] b) {
        return selectBest(a,b, 0, 0) == 1;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        int k = 5;
        System.out.println(Arrays.toString(maxNumber(nums1, nums2, k)));
    }


}
