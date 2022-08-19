package com.lihd.part02;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/9 20:00
 */
public class Code03BestTimeToBuyAndSellStockFollow {

    //这个k指的是买了再卖了的次数 同一时刻，可以一直买卖，不过都算次数。
    public static int maxProfit(int[] arr, int k) {
        int n = arr.length;
        int[][] dp = new int[n][k + 1];
        int max = 0;
        for (int times = 1; times <= k; times++) {
            int sum = dp[0][times - 1] - arr[0];
            for (int index = 1; index < n; index++) {
                sum = Math.max(dp[index][times - 1] - arr[index], sum);
                dp[index][times] = Math.max(dp[index - 1][times], sum + arr[index]);
                max = Math.max(max, dp[index][times]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] test = { 4, 1, 231, 21, 12, 312, 312, 3, 5, 2, 423, 43, 146 };
        int K = 3;
        int i = maxProfit(test, K);
        System.out.println(i);
    }


}
