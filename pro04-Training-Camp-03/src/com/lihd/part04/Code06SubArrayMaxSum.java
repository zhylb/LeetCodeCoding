package com.lihd.part04;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/4 13:09
 */
public class Code06SubArrayMaxSum {


    public static int subArrayMaxSum(int[] arr) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int j : arr) {
            sum += j;
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr1 = { -2, -3, -5, 40, -10, -10, 100, 1 };
        System.out.println(subArrayMaxSum(arr1));

        int[] arr2 = { -2, -3, -5, 0, 1, 2, -1 };
        System.out.println(subArrayMaxSum(arr2));

        int[] arr3 = { -2, -3, -5, -1 };
        System.out.println(subArrayMaxSum(arr3));
    }
}
