package com.lihd.part04;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/16 22:51
 */
public class Code04LIS {

    public static int[] lis(int[] arr) {
        int[] dp = getDp(arr);
        //遍历找到最大值
        int max = dp[0];
        int maxIndex = 0;
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] > max) {
                maxIndex = i;
                max = dp[i];
            }
        }
        //根据最大值生成长度，并且填入内容
        int[] ans = new int[max];
        int ansIndex = ans.length - 1;
        ans[ansIndex --] = arr[maxIndex];
        for (int i = maxIndex - 1; i >= 0; i--) {
            if (arr[i] < arr[i + 1] && dp[i] == dp[i + 1] - 1) {
                ans[ansIndex--] = arr[i];
            }
        }
        return ans;
    }


    //生成dp表的策略
    public static int[] getDp(int[] arr) {

        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];

        //填好第一个值，无论如何，这个值都是没有问题的。
        dp[0] = 1;
        ends[0] = arr[0];
        //ends的有效范围 [0,right]闭区间。
        int right = 0;
        // 二分所需要的变量。
        int l;
        int r;
        int m;

        for (int i = 1; i < arr.length; i++) {
            //二分代码
            l = 0;
            r = right;
            //找到 大于等于 arr[i] 的第一个数的位置
            while (l <= r) {//为什么有 equal
                m = l + (r - l)/2;
                if (ends[m] < arr[i]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            //跳出循环 一定是 l == r + 1;
            //如果 r没有改变过  那么 l == r + 1 == right + 1
            right = Math.max(l, right);
            ends[l] = arr[i];
            dp[i] = l + 1;
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 5, 3, 6, 4, 8, 9, 7 };
        int[] lis = lis(arr);
        System.out.println("lis = " + Arrays.toString(lis));
    }


}
