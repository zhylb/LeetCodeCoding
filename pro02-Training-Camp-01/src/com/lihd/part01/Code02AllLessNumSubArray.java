package com.lihd.part01;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/26 10:59
 */
public class Code02AllLessNumSubArray {


    public static int getSubArrayNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        SlidingWindow window = new SlidingWindow(arr);
        window.leftMove();
        window.rightMove();
        int ans = 0;
        while (window.leftIndex() != arr.length - 1) {
            while (!window.isFull() && window.getMax() - window.getMin() <= num) {
                window.rightMove();
            }
            int len = window.rightIndex() - window.leftIndex();
            ans += len;
            window.leftMove();
        }
        return ans;
    }

    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        int[] arr = getRandomArray(30);
        int[] arr = {1, 8, 5, 0, 6, 2, 7, 9, 1, 4, 3, 9, 0, 6, 0, 4, 4, 1, 0, 1, 0, 6, 4, 9, 8, 1, 2, 0, 5, 9};
//        int[] arr = {1, 3,8};
        int num = 5;
        printArray(arr);
        System.out.println(getSubArrayNum(arr, num));
    }

}
