package com.lihd.part02;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/15 22:10
 */
public class Code06PrintUniquePairAndTriad {
    public static void printUniquePair(int[] arr, int aim) {

        int L = 0;
        int R = arr.length - 1;
        int sum;
        while (L < R) {
            sum = arr[L] + arr[R];
            if (sum > aim) {
                R --;
            } else if (sum < aim) {
                L ++;
            } else {
                if (L == 0 || arr[L - 1] != arr[L]) {
                    System.out.println(arr[L] + " " + arr[R]);
                }
                L++;
            }
        }

    }

    public static void printUniquePair(int[] arr, int aim, int l, int r, int index) {

        int L = l;
        int R = r;
        int sum;
        while (L < R) {
            sum = arr[L] + arr[R];
            if (sum > aim) {
                R--;
            } else if (sum < aim) {
                L++;
            } else {
                if (L == l || arr[L - 1] != arr[L]) {
                    System.out.println(arr[index] + " " + arr[L] + " " + arr[R]);
                }
                L++;
//                R--;
            }
        }

    }

    public static void printUniqueTriad(int[] arr, int aim) {
        for (int i = 0; i < arr.length - 2; i++) {
            if (i == 0 || arr[i - 1] != arr[i]) {
                printUniquePair(arr, aim - arr[i], i + 1, arr.length - 1, i);
            }
        }
    }

    public static void main(String[] args) {
        int sum = 10;
        int[] arr1 = { -8, -4, -3, 0, 1, 2, 4, 5, 8, 9 };
        System.out.println(Arrays.toString(arr1));
        System.out.println("====");
        printUniquePair(arr1, sum);
        System.out.println("====");
        printUniqueTriad(arr1, sum);

    }
}
