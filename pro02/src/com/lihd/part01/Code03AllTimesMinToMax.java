package com.lihd.part01;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/30 19:56
 */
public class Code03AllTimesMinToMax {



    public static int getMax(int[] arr) {
        if (arr == null) {
            return -1;
        }
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        int[][] allLesser = MonotonicStack.getNextAllLesser(arr);
        for (int i = 0; i < arr.length; i++) {
            int leftIndex = allLesser[i][0] + 1;
            int rightIndex = allLesser[i][1] - 1;
            int sum = leftIndex == 0 ? preSum[rightIndex] : preSum[rightIndex] - preSum[leftIndex - 1];
            int curMax = sum * arr[i];
            max = Math.max(curMax, max);
        }
        return max;
    }

    public static int getMax2(int[] arr) {
        if (arr == null) {
            return -1;
        }
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {

            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && arr[left] >= arr[i]) {
                left --;
            }
            while (right <= arr.length - 1 && arr[right] >= arr[i]) {
                right ++;
            }
            left = left + 1;
            right = right - 1;
            int sum = left > 0 ? preSum[right] - preSum[left - 1] : preSum[right];
            max = Math.max(max, sum * arr[i]);

        }
        return max;
    }
    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (getMax(arr) != getMax2(arr)) {
                System.out.println("FUCK!");
                System.out.println(Arrays.toString(arr));
                System.out.println(getMax(arr));
                System.out.println(getMax2(arr));
                break;
            }
        }
        System.out.println("test finish");
    }


}
