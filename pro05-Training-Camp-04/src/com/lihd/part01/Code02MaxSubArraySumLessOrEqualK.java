package com.lihd.part01;

import sun.nio.cs.ext.MacHebrew;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/11 12:49
 */
public class Code02MaxSubArraySumLessOrEqualK {


    public static int sumLessOrEqualK(int[] arr, int K) {

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            Integer ceiling = set.ceiling(sum - K);
            if (ceiling != null) {
                ans = Math.max(ans, sum - ceiling);
            }
        }
        return ans;
    }


    public static int sumLessOrEqualK2(int[] arr, int K) {

        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        for (int i = 1; i < sum.length; i++) {
            sum[i] = Math.max(sum[i], sum[i - 1]);
        }
        int ans = 0;
        int s = 0;
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
            int index = binarySearch(sum, s - K);
            if (index != -1) {
                ans = Math.max(ans, s - sum[index]);
            }
        }
        return ans;
    }

    /**
     * 二分法 寻找 >= k的最左位置
     * @param arr 有序数组
	 * @param K 要寻找的值
     * @return int
     * @author lihd
     * @date 2022/8/11 13:18
     */
    public static int binarySearch(int[] arr, int K) {
        int l = 0;
        int r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (K > arr[m]) {
                l = m + 1;
            }else{
                ans = m;
                r = m - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 9, 7, 6, 3, 4, 2, 5, 9};
        System.out.println(sumLessOrEqualK(arr, 21));
        System.out.println(sumLessOrEqualK2(arr, 21));

        arr = new int[]{1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3,};
        System.out.println(binarySearch(arr,-1));
    }


}
