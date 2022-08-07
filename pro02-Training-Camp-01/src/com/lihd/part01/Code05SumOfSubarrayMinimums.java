package com.lihd.part01;

import java.util.Arrays;

/**
 * leetcode 907
 * Runtime: 33 ms, faster than 82.14% of Java online submissions for Sum of Subarray Minimums.
 * Memory Usage: 67.2 MB, less than 56.64% of Java online submissions for Sum of Subarray Minimums.
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/30 22:09
 */
public class Code05SumOfSubarrayMinimums {
    public static final int SPE_VAL = 10_0000_0007;
    public static int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        long ans = 0;
        int[][] nextRightSmallerLeftEqual = getNextRightSmallerLeftEqual(arr);
        for (int i = 0; i < arr.length; i++) {
            //（a * b）％c =（（a％c）*（b％c））％c
            ans +=
                    (long) arr[i] *
                            (i - nextRightSmallerLeftEqual[i][0]) *
                            ((nextRightSmallerLeftEqual[i][1] - i));
            ans %= SPE_VAL;
        }
        return (int) ans;
    }

    public static int[][] getNextRightSmallerLeftEqual(int[] arr) {
        int[] stack = new int[arr.length];
        int stackSize = 0;
        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (stackSize != 0 && arr[i] < arr[stack[stackSize - 1]]) {
                int index = stack[--stackSize];
                ans[index][0] = stackSize == 0 ? -1 : stack[stackSize - 1];
                ans[index][1] = i;
            }
            stack[stackSize++] = i;
        }
        while (stackSize != 0 ) {
            int index = stack[--stackSize];
            ans[index][0] = stackSize == 0 ? -1 : stack[stackSize - 1];
            ans[index][1] = arr.length;
        }
        return ans;
    }




}
