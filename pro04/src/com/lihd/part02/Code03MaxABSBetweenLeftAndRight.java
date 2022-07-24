package com.lihd.part02;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/9 20:45
 */
public class Code03MaxABSBetweenLeftAndRight {

    //很厉害的贪心 简直了
    public static int maxABSBetweenLeftAndRight(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return Math.max(max - arr[0], max - arr[arr.length - 1]);
    }

    //我想的一个简单贪心 时间复杂度 O(N) 空间复杂度 O(n)比较差
    public static int maxABSBetweenLeftAndRight2(int[] arr) {

        int[] rightMaxArr = new int[arr.length];
        rightMaxArr[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            arr[i] = Math.max(arr[i], arr[i + 1]);
        }

        int preMax = arr[0];
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            ans = Math.max(ans, Math.abs(preMax - rightMaxArr[i]));
            preMax = Math.max(preMax, arr[i]);
        }
        return ans;
    }

    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(1000);
        int maxABSBetweenLeftAndRight = maxABSBetweenLeftAndRight(arr);
        int maxABSBetweenLeftAndRight2 = maxABSBetweenLeftAndRight2(arr);
        System.out.println("maxABSBetweenLeftAndRight = " + maxABSBetweenLeftAndRight);
        System.out.println("maxABSBetweenLeftAndRight2 = " + maxABSBetweenLeftAndRight2);
    }

}
