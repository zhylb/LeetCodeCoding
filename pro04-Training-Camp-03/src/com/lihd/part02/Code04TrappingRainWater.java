package com.lihd.part02;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/15 22:09
 */
public class Code04TrappingRainWater {

    public static int water1(int[] arr) {
        int ans = 0;
        int[] endMax = new int[arr.length];
        endMax[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            endMax[i] = Math.max(arr[i], endMax[i + 1]);
        }
        int startMax = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            int min = Math.min(endMax[i + 1], startMax);
            ans += Math.max(0, min - arr[i]);
            startMax = Math.max(startMax, arr[i]);
        }
        return ans;
    }


    public static int water2(int[] arr) {
        int ans = 0;
        int startMax = arr[0];
        int endMax = arr[arr.length - 1];
        int L = 1;
        int R = arr.length - 2;
        //必须是 <= 否则会少结算一个 L，R指的是没有结算的位置 要开始结算，
        //L == R的意思是 就剩下一个没有结算，于是 不要少等号。
        while (L <= R) {
            if (startMax < endMax) {
                ans += Math.max(0, startMax - arr[L]);
                startMax = Math.max(startMax, arr[L]);
                L ++;
            } else {
                ans += Math.max(0, endMax - arr[R]);
                endMax = Math.max(endMax, arr[R]);
                R--;
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int value = 200;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(len, value);
            int ans1 = water1(arr);
            int ans2 = water2(arr);
            if (ans1 != ans2 ) {
                System.out.println("Oops!");
                System.out.println(Arrays.toString(arr));
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test finish");
    }
}
