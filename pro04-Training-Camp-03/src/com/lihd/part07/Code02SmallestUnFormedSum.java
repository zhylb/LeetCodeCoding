package com.lihd.part07;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/5 20:42
 */
public class Code02SmallestUnFormedSum {

    //背包问题 忘了 我去
    //我又回来补上了
    public static int unformedSumDp(int[] arr) {

        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max += arr[i];
            min = Math.min(min, arr[i]);
        }

        return unformedSumDp(arr, max, min);
    }


    //不要为了节省空间 导致计算很复杂
    public static int unformedSumDp(int[] arr, int sum, int min) {
        //动态规划
        int n = arr.length;

        boolean[][] dp = new boolean[n][sum + 1];

        //第一列初始化 随便使用0...i 恰好拼出 0
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        //第一行初始化
        dp[0][ arr[0] ] = true;


        //dp[i][j] 0...i的数字最多使用一次 ，是否可以 刚好拼出 val
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= sum; j++) {
                //情况一 我不使用这个字符 这种情况一定存在
                dp[i][j] = dp[i - 1][j];
                //情况二 我使用这个字符，
                //j - arr[i] 代表 刚好拼出 j - arr[i]
                //dp[i - 1][j - arr[i]] 代表使用 0...i - 1 刚好拼出 j - arr[i]
                //如果dp[i - 1][j - arr[i]]是真，那么就返回true
                if (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]) {
                    dp[i][j] = true;
                }
            }
        }
        for (int i = min; i <= sum; i++) {
            if (!dp[n - 1][i]) {
                //上面的意思是 dp最后一行第一个为假的
                return i;
            }
        }
        return sum + 1;
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
        int[] arr = {1,3,7};
        System.out.println(unFormedSumImprove(arr));
        System.out.println(unformedSumDp(arr));
    }



}
