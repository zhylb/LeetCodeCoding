package com.lihd.part02;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/9 20:00
 */
public class Code01BestTimeToBuyAndSellStock1 {

    public static int maxProfit(int[] arr) {
        int min = arr[0];
        int max = 0;
        for (int profit : arr) {
            min = Math.min(min, profit);
            max = Math.max(max, profit - min);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 8, 6, 5, 6, 2, 3, 4, 7, 3};
        System.out.println(maxProfit(arr));
    }
}
