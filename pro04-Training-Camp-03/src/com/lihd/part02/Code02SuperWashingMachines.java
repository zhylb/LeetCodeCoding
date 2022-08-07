package com.lihd.part02;

import java.time.Period;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/9 20:27
 */
public class Code02SuperWashingMachines {

    // 1 <= n <= 10 ^ 4
    public static int findMinMoves(int[] machines) {

        int sum = 0;
        for (int machine : machines) {
            sum += machine;
        }

        if (sum % machines.length != 0) {
            return -1;
        }
        //每个机器的平均个数
        int avg = sum / machines.length;
        int preSum = 0;
        int ans = 0;
        for (int i = 0; i < machines.length; i++) {
            int left = preSum - avg * i;
            int right = (sum - preSum - machines[i]) - avg * (machines.length - i - 1);
            if (left < 0 && right < 0) {
                ans = Math.max(ans, Math.abs(left) + Math.abs(right));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(left), Math.abs(right)));
            }
            preSum += machines[i];
        }
        return ans;
    }
}
