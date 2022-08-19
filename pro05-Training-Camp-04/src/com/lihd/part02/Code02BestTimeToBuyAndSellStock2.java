package com.lihd.part02;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/9 20:00
 */
public class Code02BestTimeToBuyAndSellStock2 {

    public static int maxProfitNoLimit(int[] arr) {

        int pre = arr[0];
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                ans += arr[i] - arr[i - 1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 8, 6, 5, 6, 2, 3, 4, 7, 3};
        System.out.println(maxProfitNoLimit(arr));
    }
}
