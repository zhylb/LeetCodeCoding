package com.lihd.part12;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/19 17:45
 */
public class Code08CardsInLine {
    public static int f(int[] cards, int L, int R) {
        if (L == R) {
            return cards[L];
        }
        return Math.max(cards[L] + s(cards, L + 1, R), cards[R] + s(cards, L, R - 1));
    }
    public static int s(int[] cards, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(f(cards, L + 1, R), f(cards, L, R - 1));
    }

    public static int getMaxNum(int[] cards) {
        if (cards == null || cards.length == 0) {
            return 0;
        }
        return Math.max(f(cards, 0, cards.length - 1), s(cards, 0, cards.length - 1));
    }

    public static int getMaxNumDp(int[] cards) {
        int[][] f = new int[cards.length][cards.length + 1];
        int[][] s = new int[cards.length][cards.length + 1] ;
        //对 f 和 s进行初始化
        for (int i = 0; i < cards.length; i++) {
            f[i][i] = cards[i];
        }
        //初始化完成开始模拟递归
        for (int i = 1; i < cards.length; i++) {
            int L = 0;
            int R = i;
            while (L < cards.length && R < cards.length) {
                s[L][R] = Math.min(f[L + 1][R], f[L][R - 1]);
                f[L][R] = Math.max(s[L + 1][R] + cards[L], s[L][R - 1] + cards[R]);
                L ++;
                R ++;
            }
        }
        return Math.max(f[0][cards.length - 1], s[0][cards.length - 1]);
    }

    public static void printMaxNum(int[] cards) {
        if (cards == null || cards.length == 0) {
            System.out.println("没有纸牌 无法游戏");
        }
        int f = f(cards, 0, cards.length - 1);
        int s = s(cards, 0, cards.length - 1);
        int max = Math.max(f, s);
        System.out.println("f = " + f);
        System.out.println("s = " + s);
        System.out.println("max = " + max);
    }
    public static void main(String[] args) {
        int[] cards = {2, 1, 10, 3, 3, 6, 5};
        printMaxNum(cards);
        int maxNumDp = getMaxNumDp(cards);
        System.out.println("maxNumDp = " + maxNumDp);
    }
}
