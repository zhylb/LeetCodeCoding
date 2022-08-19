package com.lihd.part07;


import com.lihd.utils.ArrayUtils;

import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/19 19:15
 */
public class Code04SubsequenceMaxModM {
    // 背包问题, 不过我好像把方向写的很奇怪
    public static int max1(int[] arr, int m) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        //sum 为累加和
        int n = arr.length;
        boolean[][] dp = new boolean[n][sum + 1];
        // 填好最后一行
        dp[n - 1][0] = true;
        dp[n - 1][arr[n - 1]] = true;
        // 填好第一行
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        //普遍位置
        for (int r = n - 2; r >= 0; r--) {
            for (int c = 1; c <= sum; c++) {
                //不用
                dp[r][c] = dp[r + 1][c];
                //用
                if (c - arr[r] >= 0 && !dp[r][c]) {
                    dp[r][c] = dp[r + 1][c - arr[r]];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= sum; i++) {
            if (dp[0][i]) {
                ans = Math.max(ans, i % m);
            }
        }
        return ans;
    }

    public static int max2(int[] arr, int m) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][m];
        // 填好最后一行
        dp[n - 1][arr[n - 1] % m] = true;
        // 填好第一列
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        //普遍位置
        for (int r = n - 2; r >= 0; r--) {
            for (int c = 1; c < m; c++) {
                //不使用当前位置的数
                dp[r][c] = dp[r + 1][c];
                if (dp[r][c]) {
                    continue;
                }
                // 使用当前的数 一定要先 % m
                int mod = arr[r] % m;
                if (c > mod) {
                    dp[r][c] = dp[r + 1][m + mod - c];
                } else {
                    dp[r][c] = dp[r + 1][mod  - c];
                }
            }
        }

        for (int c = m - 1; c >= 0; c--) {
            if (dp[0][c]) {
                return c;
            }
        }
        return 0;
    }


    public static int max3(int[] arr, int m) {

        int n = arr.length;
        int mid = n / 2;
        TreeSet<Integer> left = new TreeSet<>();
        TreeSet<Integer> right = new TreeSet<>();
        generateSubSequence(arr, 0, mid, 0, m, left);
        generateSubSequence(arr, mid, n, 0, m, right);
        int ans = 0;
        for (Integer l : left) {
            Integer f = right.floor(m - 1 - l);
            f = f == null ? 0 : f;
            ans = Math.max(ans, f + l);
        }
        return ans;
    }


    public static void generateSubSequence(int[] arr, int index, int end, int preSum, int m, TreeSet<Integer> set) {
        if (index == end) {
            // 这里不要忘了 % m
            set.add(preSum % m);
        } else {
            generateSubSequence(arr, index + 1, end, preSum + arr[index], m, set);
            generateSubSequence(arr, index + 1, end, preSum, m, set);
        }
    }


    public static void main(String[] args) {
        int testTimes = 1000;
        int arrLenBegin = 10;
        int arrLenEnd = 20;
        int arrNumBegin = 1;
        int arrNumEnd = 20;
        int kBegin = 10;
        int kEnd = 20;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = ArrayUtils.randomNotNullArr(arrLenBegin, arrLenEnd, arrNumBegin, arrNumEnd);
            int k = ArrayUtils.randomVal(kBegin, kEnd);
            int ans1 = max1(arr, k);
            int ans2 = max2(arr, k);
            int ans3 = max3(arr, k);
            assertEquals(ans1, ans2);
            assertEquals(ans1, ans3);
        }
    }

}
