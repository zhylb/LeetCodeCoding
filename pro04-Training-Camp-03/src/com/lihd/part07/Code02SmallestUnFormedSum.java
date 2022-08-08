package com.lihd.part07;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/5 20:42
 */
public class Code02SmallestUnFormedSum {

    //背包问题 忘了 我去
    public static int unformedSum1(int[] arr) {
        return 0;
    }

    public static int unformedSumDp(int[] arr) {

        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        int n = arr.length;
        //sum为总和
        int[][] dp = new int[n + 1][sum];
        return 0;
    }


    public static int unFormedSumImprove(int[] arr) {
        int range = 1;
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= range + 1) {
                range += arr[i];
            } else {
                return range + 1;
            }
        }
        return range + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3, 7};
        System.out.println(unFormedSumImprove(arr));
    }



}
