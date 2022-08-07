package com.lihd.part07;

import com.sun.istack.internal.NotNull;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/5 20:33
 */
public class Code01MinLengthForSort {


    public static int minlengthForSort(@NotNull int[] arr) {
        int n = arr.length;
        int leftIndex = 0;
        int leftMax = arr[0];
        int rightIndex = n - 1;
        int rightMin = arr[n - 1];
        for (int i = 1; i < n; i++) {
            if (arr[i] >= leftMax) {
                leftMax = arr[i];
            } else {
                leftIndex = i;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] <= rightMin) {
                rightMin = arr[i];
            } else {
                rightIndex = i;
            }
        }
        //没有看错 就是 left - right因为一般情况下left就跑到了右边 right跑到了左边
        return leftIndex - rightIndex + 1;

    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
        System.out.println(minlengthForSort(arr));
    }

}
