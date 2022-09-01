package com.lihd.part07;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * 时间复杂度
 * O ( C * R * 2 ^ C) -> 可以发现C是小的那个值比较好, 因此算法做了优化
 *
 * 为什么这里用两行就够了, 而tsp要用 n行 ?
 * 因为每一行的状态只依赖 其上一行的状态, 因此行足矣
 * 而tsp问题 每一行的状态依赖上面 可能任意行的转态, 因此必须建立一个满表
 *
 * 测试链接 <a href="http://poj.org/problem?id=2411">北京大学acm</a>
 * 2272K	344MS
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/27 10:02
 */
public class Code02PavingTileDp {
    public static long pavingTile(int m, int n) {
        if (m < 0 || n < 0 || ((m * n) & 1) != 0) {
            return 0;
        }
        // 合理情况
        int R = Math.max(m, n);
        int C = Math.min(m, n);

        int statusLen = (1 << C);
        int fullStatus = statusLen - 1;

        long[] last = new long[statusLen];
        long[] dp = new long[statusLen];

        // 上一行状态中, 只有全满的状态算一种情况
        last[fullStatus] = 1;

        for (int row = 0; row < R; row++) {
            for (int status = 0; status < statusLen; status++) {
                // 如果等于0 ,说明之前的这个状态是没有有效返回的
                // 其实不加这个判断也可以, 但是dfs中会将 last[status] 作为val属性传入
                // 如果lastVal = 0, dfs遍历每次都 让dp[curStatus] += 0, 是没有意义的
                // 因此可以认为这里是一个小加速
                if (last[status] != 0) {
                    // 加工出当前行的状态
                    int curStatus = (~status) & fullStatus;
                    dfs(last[status], curStatus, 0, C, dp);
                }
            }
            // 处理完成之后, 交换
            long[] temp = last;
            last = dp;
            dp = temp;

            // dp的值清0
            Arrays.fill(dp, 0);
        }
        return last[fullStatus];
    }

    private static void dfs(long lastVal, int status, int c, int colEnd, long[] dp) {
        if (c == colEnd) {
            dp[status] += lastVal;
        } else {
            dfs(lastVal, status, c + 1, colEnd, dp);
            if (c + 1 < colEnd && (status & (3 << c)) == 0) {
                dfs(lastVal, status | (3 << c), c + 2, colEnd, dp);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a;
        int b;
        while ((a = sc.nextInt()) != 0 && (b = sc.nextInt()) != 0) {
            System.out.println(pavingTile(a, b));
        }
    }

}
