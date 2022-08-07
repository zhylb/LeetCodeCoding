package com.lihd.part01;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/9 22:23
 */
public class Code06MakeNo {


    public static int[] createNoArr(int m) {
        int[] arr = new int[m];
        int half = 1;
        while (half < arr.length) {
            int right = Math.min(half * 2, arr.length);
            for (int i = half; i < right; i++) {
                arr[i] = arr[i - half] * 2;
            }
            for (int i = 0; i < half; i++) {
               arr[i] = arr[i] * 2 - 1;
            }
            half = half * 2;
        }
        return arr;
    }

    // 检验函数
    public static boolean isValid(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {
                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int N = 1; N < 1000; N++) {
            int[] arr = createNoArr(N);
            if (!isValid(arr)) {
                System.out.println("Oops!");
                System.out.println(Arrays.toString(arr));
                break;
            }
        }
        System.out.println("test end");

        System.out.println(isValid(createNoArr(1042)));
        System.out.println(isValid(createNoArr(2981)));


    }


}
