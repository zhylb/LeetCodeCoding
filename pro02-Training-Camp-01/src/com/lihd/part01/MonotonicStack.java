package com.lihd.part01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/30 17:31
 */
public class MonotonicStack {


    public static int[][] getNextLeftEqualRightGreater(int[] arr) {
        //使用数组模拟栈
        int[] stack = new int[arr.length];
        int stackSize = 0;
        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (stackSize != 0 && arr[i] > arr[stack[stackSize - 1]]) {
                //结算
                int index = stack[--stackSize];
                ans[index][0] = stackSize == 0 ? -1 : stack[stackSize - 1];
                ans[index][1] = i;
            }
            stack[stackSize++] = i;
        }
        //结算没人要的值
        while (stackSize != 0) {
            //结算
            int index = stack[--stackSize];
            ans[index][0] = stackSize == 0 ? -1 : stack[stackSize - 1];
            ans[index][1] = arr.length;
        }
        return ans;
    }

    public static int[][] getNextLeftEqualRightLesser(int[] arr) {
        //使用数组模拟栈
        int[] stack = new int[arr.length];
        int stackSize = 0;
        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (stackSize != 0 && arr[i] < arr[stack[stackSize - 1]]) {
                //结算
                int index = stack[--stackSize];
                ans[index][0] = stackSize == 0 ? -1 : stack[stackSize - 1];
                ans[index][1] = i;
            }
            stack[stackSize++] = i;
        }
        //结算没人要的值
        while (stackSize != 0) {
            //结算
            int index = stack[--stackSize];
            ans[index][0] = stackSize == 0 ? -1 : stack[stackSize - 1];
            ans[index][1] = arr.length;
        }
        return ans;
    }



    public static int[][] getNextLeftGreaterRightEqual(int[] arr) {
        //使用数组模拟栈
        int[] stack = new int[arr.length];
        int stackSize = 0;
        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (stackSize != 0 && arr[i] >= arr[stack[stackSize - 1]]) {
                //结算
                int index = stack[--stackSize];
                ans[index][0] = stackSize == 0 ? -1 : stack[stackSize - 1];
                ans[index][1] = i;
            }
            stack[stackSize++] = i;
        }
        //结算没人要的值
        while (stackSize != 0) {
            //结算
            int index = stack[--stackSize];
            ans[index][0] = stackSize == 0 ? -1 : stack[stackSize - 1];
            ans[index][1] = arr.length;
        }
        return ans;
    }

    public static int[][] getNextLeftLesserRightEqual(int[] arr) {
        //使用数组模拟栈
        int[] stack = new int[arr.length];
        int stackSize = 0;
        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (stackSize != 0 && arr[i] <= arr[stack[stackSize - 1]]) {
                //结算
                int index = stack[--stackSize];
                ans[index][0] = stackSize == 0 ? -1 : stack[stackSize - 1];
                ans[index][1] = i;
            }
            stack[stackSize++] = i;
        }
        //结算没人要的值
        while (stackSize != 0) {
            //结算
            int index = stack[--stackSize];
            ans[index][0] = stackSize == 0 ? -1 : stack[stackSize - 1];
            ans[index][1] = arr.length;
        }
        return ans;
    }



    public static int[][] getNextAllGreater(int[] arr) {


        Stack<List<Integer>> stack = new Stack<>();
        int[][] ans = new int[arr.length][2];

        for (int i = 0; i < arr.length; i++) {

            while (!stack.isEmpty() && arr[i] > arr[stack.peek().get(0)]) {
                List<Integer> pop = stack.pop();
                int index = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer anInt : pop) {
                    ans[anInt][0] = index;
                    ans[anInt][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.add(list);
            }

        }
        //结算没人激活的
        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            int index = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer anInt : pop) {
                ans[anInt][0] = index;
                ans[anInt][1] = arr.length;
            }
        }
        return ans;
    }

    public static int[][] getNextAllLesser(int[] arr) {


        Stack<List<Integer>> stack = new Stack<>();
        int[][] ans = new int[arr.length][2];

        for (int i = 0; i < arr.length; i++) {

            while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
                List<Integer> pop = stack.pop();
                int index = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer anInt : pop) {
                    ans[anInt][0] = index;
                    ans[anInt][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.add(list);
            }

        }
        //结算没人激活的
        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            int index = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer anInt : pop) {
                ans[anInt][0] = index;
                ans[anInt][1] = arr.length;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {71,55,82,55};
        int[][] leftEqualRightLesser = getNextLeftEqualRightLesser(arr);
        int[][] leftLesserRightEqual = getNextLeftLesserRightEqual(arr);
        int[][] leftEqualRightGreater = getNextLeftEqualRightGreater(arr);
        int[][] leftGreaterRightEqual = getNextLeftGreaterRightEqual(arr);
        int[][] allGreater = getNextAllGreater(arr);
        int[][] allLesser = getNextAllLesser(arr);

        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("allLesser = " + Arrays.deepToString(allLesser));
        System.out.println("allGreater = " + Arrays.deepToString(allGreater));
        System.out.println("leftEqualRightLesser = " + Arrays.deepToString(leftEqualRightLesser));
        System.out.println("leftLesserRightEqual = " + Arrays.deepToString(leftLesserRightEqual));
        System.out.println("leftEqualRightGreater = " + Arrays.deepToString(leftEqualRightGreater));
        System.out.println("leftGreaterRightEqual = " + Arrays.deepToString(leftGreaterRightEqual));
    }


}
