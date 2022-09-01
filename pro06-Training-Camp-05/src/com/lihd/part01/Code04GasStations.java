package com.lihd.part01;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/20 22:37
 */
public class Code04GasStations {
    /**
     * 为了leetcode 134 编写的, 由于方法暴力, 因此结果惨不忍睹
     * Runtime: 61 ms, faster than 5.00% of Java online submissions for Gas Station.
     * Memory Usage: 90.9 MB, less than 5.00% of Java online submissions for Gas Station.
     * @param gas
	 * @param cost
     * @return int
     * @author lihd
     * @date 2022/8/20 23:19
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //使用辅助空间
        int[] can = new int[gas.length];
        for (int i = 0; i < can.length; i++) {
            can[i] = gas[i] - cost[i];
        }
        //开辟前缀和
        int[] preSum = new int[can.length * 2];
        preSum[0] = can[0];
        for (int i = 1; i < preSum.length; i++) {
            if (i < can.length) {
                preSum[i] = preSum[i - 1] + can[i];
            } else {
                preSum[i] = preSum[i - 1] + can[i - can.length];
            }
        }
        int pre = 0;
        int l = 0;
        int r = can.length - 1;
        // 单调队列
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < can.length; i++) {
            while (!queue.isEmpty() && preSum[queue.peekLast()] >= preSum[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
        }
        // 滑动窗口
        while (l < can.length) {
            int min = preSum[queue.peekFirst()];
            if (min - pre >= 0) {
                return l;
            }
            pre = preSum[l];
            //扩充窗口
            r ++;
            while (!queue.isEmpty() && preSum[queue.peekLast()] >= preSum[r]) {
                queue.pollLast();
            }
            queue.addLast(r);
            //缩小窗口
            l ++;
            if (queue.peekFirst() < l) {
                queue.pollFirst();
            }

        }
        return -1;
    }

    public static boolean[] canCompleteCircuitImprove(int[] gas, int[] cost) {
        //修改gas的含义 此时gas[i]的含义是 i位置 的净获油,任何时刻小于0代表失败
        for (int i = 0; i < gas.length; i++) {
            gas[i] -= cost[i];
        }

        boolean[] ans = new boolean[gas.length];

        int need = 0;
        int oil = 0;
        int b = 0;
        int e = 0;
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] >= 0) {
                b = i;
                e = nextIndex(b, gas.length);
                oil = gas[i];
            }
        }

        for (; nextIndex(e, gas.length) != b; e++) {

        }




        //恢复现场
        for (int i = 0; i < gas.length; i++) {
            gas[i] += cost[i];
        }
        return ans;
    }

    private static int nextIndex(int index, int len) {
        if (index == len - 1) {
            return 0;
        }
        return index + 1;
    }


    public static boolean[] canCompleteCircuitMy(int[] gas, int[] cost) {
        //使用辅助空间
        int[] can = new int[gas.length];
        for (int i = 0; i < can.length; i++) {
            can[i] = gas[i] - cost[i];
        }
        //开辟前缀和
        int[] preSum = new int[can.length * 2];
        preSum[0] = can[0];
        for (int i = 1; i < preSum.length; i++) {
            if (i < can.length) {
                preSum[i] = preSum[i - 1] + can[i];
            } else {
                preSum[i] = preSum[i - 1] + can[i - can.length];
            }
        }
        int pre = 0;
        int l = 0;
        int r = can.length - 1;
        // 单调队列
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < can.length; i++) {
            while (!queue.isEmpty() && preSum[queue.peekLast()] >= preSum[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
        }
        // 滑动窗口
        boolean[] ans = new boolean[can.length];
        while (l < can.length) {
            int min = preSum[queue.peekFirst()];
            ans[l] = min - pre >= 0;
            pre = preSum[l];
            //扩充窗口
            r ++;
            while (!queue.isEmpty() && preSum[queue.peekLast()] >= preSum[r]) {
                queue.pollLast();
            }
            queue.addLast(r);
            //缩小窗口
            l ++;
            if (queue.peekFirst() < l) {
                queue.pollFirst();
            }

        }
        return ans;
    }




    //===================== for test ===============================
    //                      for test
    //===================== for test ===============================

    // for test
    public static boolean[] test(int[] dis, int[] oil) {
        if (dis == null || oil == null || dis.length < 2
                || dis.length != oil.length) {
            return null;
        }
        boolean[] res = new boolean[dis.length];
        for (int i = 0; i < dis.length; i++) {
            dis[i] = oil[i] - dis[i];
        }
        for (int i = 0; i < dis.length; i++) {
            res[i] = canWalkThrough(dis, i);
        }
        return res;
    }

    // for test

    public static boolean canWalkThrough(int[] arr, int index) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[index];
            if (sum < 0) {
                return false;
            }
            index = nextIndex(index, arr.length);
        }
        return true;
    }

    // for test
    public static void printArray(int[] dis, int[] oil) {
        for (int i = 0; i < dis.length; i++) {
            System.out.print(oil[i] - dis[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void printBooleanArray(boolean[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static int[] generateArray(int size, int max) {
        int[] res = new int[size];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * max);
        }
        return res;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(boolean[] res1, boolean[] res2) {
        for (int i = 0; i < res1.length; i++) {
            if (res1[i] != res2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int max = 20;
        for (int i = 0; i < 5000000; i++) {
            int size = (int) (Math.random() * 20) + 2;
            int[] dis = generateArray(size, max);
            int[] oil = generateArray(size, max);
            int[] dis1 = copyArray(dis);
            int[] oil1 = copyArray(oil);
            int[] dis2 = copyArray(dis);
            int[] oil2 = copyArray(oil);
            boolean[] res1 = canCompleteCircuitMy(oil1,dis1);
            boolean[] res2 = test(dis2, oil2);
            if (!isEqual(res1, res2)) {
                printArray(dis, oil);
                printBooleanArray(res1);
                printBooleanArray(res2);
                System.out.println("what a fucking day!");
                break;
            }
        }
    }




}
