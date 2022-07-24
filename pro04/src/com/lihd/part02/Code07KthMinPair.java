package com.lihd.part02;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/15 22:10
 */
public class Code07KthMinPair {

    public static int[] kthMinPair(int[] arr, int k) {
        int N = arr.length;
        int val = findArrLessKth(arr, (k - 1) / N);
        int lessCount = 0;
        int equalCount = 0;
        for (int j : arr) {
            if (j < val) {
                lessCount++;
            }
            if (j == val) {
                equalCount++;
            }
        }
        k = k - lessCount * N;
        int secondVal = findArrLessKth(arr, (k - 1) / equalCount);
        return new int[]{val, secondVal};
    }

    // k 从0开始 是实际的索引。
    public static int findArrLessKth(int[] arr, int k) {
        int L = 0;
        int R = arr.length - 1;
        int[] pairs;
        int random = 0;
        while (L < R) {
            random = (int) (Math.random() * (R - L + 1) + L);
            pairs = partition(arr, L, R, arr[random]);
            if (pairs[0] > k) {
                R = pairs[0] - 1;
            } else if (pairs[1] < k) {
                L = pairs[1] + 1;
            } else {
                return arr[pairs[0]];
            }
        }
        //跳出循环 L == R 此时的位置就应该被返回
        return arr[L];
    }


    public static int[] partition(int[] arr,int L, int R, int k) {
        int less = L - 1;
        int more = R + 1;
        int i = L;
        while (i < more) {
            if (arr[i] == k) {
                i ++;
            } else if (arr[i] < k) {
                swap(arr, ++less, i++);
            } else {
                swap(arr, --more, i);
            }
        }
        return new int[]{ less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr = {3,9, 2, 4, 5, 8, 9, 6, 1, 2};
        int[] partition = partition(arr, 0, arr.length - 1, 1);
        System.out.println(Arrays.toString(partition));
        System.out.println(Arrays.toString(arr));
        System.out.println(findArrLessKth(arr,6));
    }

}
