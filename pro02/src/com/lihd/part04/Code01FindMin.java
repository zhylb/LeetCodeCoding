package com.lihd.part04;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/27 21:14
 */
public class Code01FindMin {

    public static int findMin(int[] arr, int index) {
        return findMin(arr, 0, arr.length - 1, index - 1);
    }


    public static int findMin(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int randomIndex = (int) (L + Math.random() * (R - L + 1));
        int[] partition = partition(arr, L, R, arr[randomIndex]);

        if (index < partition[0]) {
            return findMin(arr, L, partition[0] - 1, index);
        } else if (index > partition[1]) {
            return findMin(arr, partition[1] + 1, R, index);
        } else {
            return arr[index];
        }
    }



    public static int[] partition(int[] arr, int L, int R, int val) {
        int less = L - 1;
        int more = R + 1;
        int i = L;
        while (i < more) {
            if (arr[i] == val) {
                i ++;
            } else if (arr[i] < val) {
                swap(arr, ++less, i++);
            } else {
                swap(arr, --more, i);
            }
        }
        return new int[]{less + 1, more - 1};
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 5;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = findMin(arr, k);
            int ans2 = Code02Bfprt.bfprt(arr, k);
//            int ans3 = minKth3(arr, k);
            if (ans1 != ans2) {//|| ans2 != ans3
                System.out.println(Arrays.toString(arr));
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
