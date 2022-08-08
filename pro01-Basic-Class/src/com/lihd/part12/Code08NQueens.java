package com.lihd.part12;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/20 19:48
 */
public class Code08NQueens {


    public static int NQueens(int N) {
        if (N <= 1) {
            return 0;
        }
        int[] pre = new int[N];
        return NQueens(N, 0, pre);
    }

    public static int NQueens(int N, int index, int[] pre) {
        if (index == N) {
            return 1;
        }
        //还没有到末尾 遍历一遍
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (isRight(pre, index, i)) {
                pre[index] = i;
                ans += NQueens(N, index + 1, pre);
            }
        }
        return ans;
    }

    public static boolean isRight(int[] pre, int x, int y) {
        
        //(i, pre[i])
        //(x, y)
        for (int i = 0; i < x; i++) {
            if (pre[i] == y || Math.abs(i - x) == Math.abs(pre[i] - y)) {
                return false;
            }
        }
        return true;
    }


    public static int NQueens2(int N) {

        int scale = N == 32 ? -1 : (1 << N) -1;
        return NQueens2(scale, 0, 0, 0);
    }

    public static int NQueens2(int scale, int colLim, int leftLineLim, int rightLineLim) {
        if (scale == colLim) {
            return 1;
        }
        int pos = scale & ~(colLim | leftLineLim | rightLineLim);
        int ans = 0;
        while (pos != 0) {
            int mostRightOne = pos & (~pos + 1);
            pos = pos ^ mostRightOne; //pos - mostRightOne;
            ans += NQueens2(scale, colLim | mostRightOne, (leftLineLim | mostRightOne) << 1, (rightLineLim | mostRightOne) >> 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int scale = 15;
        long start = System.currentTimeMillis();
        int nQueens2 = NQueens2(scale);
        long end = System.currentTimeMillis();
        System.out.println("nQueens2 = " + nQueens2);
        System.out.println("使用位运算 " + (end - start));
        start = System.currentTimeMillis();
        int nQueens = NQueens(scale);
        end = System.currentTimeMillis();
        System.out.println("nQueens = " + nQueens);
        System.out.println("使用数组运算 " + (end - start));
        /*
        当规模是 15 时
        nQueens2 = 2279184
        使用位运算 1464
        nQueens = 2279184
        使用数组运算 40552
         */
    }
}
