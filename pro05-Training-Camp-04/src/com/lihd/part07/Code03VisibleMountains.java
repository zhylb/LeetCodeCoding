package com.lihd.part07;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/16 21:54
 */
public class Code03VisibleMountains {

    public static int getValueSimple(int[] arr) {
        int n = arr.length;
        return (n - 2) * 2 + 1;
    }

    public static int getValue(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        class Record{
            int height;
            int times;
            public Record(int height, int times) {
                this.height = height;
                this.times = times;
            }
        }


        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }

        //先定义好一个类
        Stack<Record> stack = new Stack<>();
        stack.push(new Record(arr[maxIndex], 1));
        int ans = 0;
        int index = nextIndex(maxIndex, arr.length);
        while (index != maxIndex) {
            while (stack.peek().height < arr[index]) {
                //结算
                int times = stack.pop().times;
                ans += getInternalSum(times) + 2 * times;
            }
            if (stack.peek().height == arr[index]) {
                stack.peek().times++;
            } else {
                stack.push(new Record(arr[index], 1));
            }

            index = nextIndex(index, arr.length);
        }

        while (stack.size() > 2) {
            int times = stack.pop().times;
            ans += getInternalSum(times) + 2 * times;
        }

        if (stack.size() == 2) {
            int times = stack.pop().times;
            int maxTimes = stack.peek().times;
            ans += getInternalSum(times) + (maxTimes > 1 ? 2 * times : times);
        }
        ans += getInternalSum(stack.pop().times);
        return ans;

    }


    private static int nextIndex(int index, int n) {
        if (index == n - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    private static int getInternalSum(int k) {
        if (k == 1) {
            return 0;
        } else {
            return k * (k - 1) / 2;
        }
    }



    public static int lastIndex(int i, int size) {
        return i > 0 ? (i - 1) : (size - 1);
    }


    // for test, O(N^2)的解法，绝对正确
    public static int rightWay(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        HashSet<String> equalCounted = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            // 枚举从每一个位置出发，根据“小找大”原则能找到多少对儿，并且保证不重复找
            res += getVisibleNumFromIndex(arr, i, equalCounted);
        }
        return res;
    }

    // for test
    // 根据“小找大”的原则返回从index出发能找到多少对
    // 相等情况下，比如arr[1]==3，arr[5]==3
    // 之前如果从位置1找过位置5，那么等到从位置5出发时就不再找位置1（去重）
    // 之前找过的、所有相等情况的山峰对，都保存在了equalCounted中
    public static int getVisibleNumFromIndex(int[] arr, int index,
                                             HashSet<String> equalCounted) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) { // 不找自己
                if (arr[i] == arr[index]) {
                    String key = Math.min(index, i) + "_" + Math.max(index, i);
                    // 相等情况下，确保之前没找过这一对
                    if (equalCounted.add(key) && isVisible(arr, index, i)) {
                        res++;
                    }
                } else if (isVisible(arr, index, i)) { // 不相等的情况下直接找
                    res++;
                }
            }
        }
        return res;
    }

    // for test
    // 调用该函数的前提是，lowIndex和highIndex一定不是同一个位置
    // 在“小找大”的策略下，从lowIndex位置能不能看到highIndex位置
    // next方向或者last方向有一个能走通，就返回true，否则返回false
    public static boolean isVisible(int[] arr, int lowIndex, int highIndex) {
        if (arr[lowIndex] > arr[highIndex]) { // “大找小”的情况直接返回false
            return false;
        }
        int size = arr.length;
        boolean walkNext = true;
        int mid = nextIndex(lowIndex, size);
        // lowIndex通过next方向走到highIndex，沿途不能出现比arr[lowIndex]大的数
        while (mid != highIndex) {
            if (arr[mid] > arr[lowIndex]) {
                walkNext = false;// next方向失败
                break;
            }
            mid = nextIndex(mid, size);
        }
        boolean walkLast = true;
        mid = lastIndex(lowIndex, size);
        // lowIndex通过last方向走到highIndex，沿途不能出现比arr[lowIndex]大的数
        while (mid != highIndex) {
            if (arr[mid] > arr[lowIndex]) {
                walkLast = false; // last方向失败
                break;
            }
            mid = lastIndex(mid, size);
        }
        return walkNext || walkLast; // 有一个成功就是能相互看见
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 10;
        int testTimes = 3000000;
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = getRandomArray(size, max);
            if (rightWay(arr) != getValue(arr)) {
                printArray(arr);
                System.out.println(rightWay(arr));
                System.out.println(getValue(arr));
                break;
            }
        }
        System.out.println("test end!");


    }



}
