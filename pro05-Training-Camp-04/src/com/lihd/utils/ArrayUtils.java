package com.lihd.utils;


import java.util.Arrays;
import java.util.Random;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/13 12:59
 */
public class ArrayUtils {

    public static Random random = new Random();

    public static int[] randomNotNullArr(int minLen, int maxLen, int numBegin, int numEnd) {
        int len = randomVal(minLen, maxLen);
        int[] ans = new int[len];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = randomVal(numBegin, numEnd);
        }
        return ans;
    }

    public static String randomASKString(int minLen, int maxLen) {
        int len = randomVal(minLen, maxLen);
        char[] chs = new char[len];
        for (int i = 0; i < chs.length; i++) {
            chs[i] = (char) randomVal('a', 'z');
        }
        return Arrays.toString(chs);
    }

    public static int[][] randomNotNullMatrix(int rb, int re, int cb, int ce, int numB, int numE) {
        int r = randomVal(rb, re);
        int c = randomVal(cb, ce);
        int[][] ans = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans[i][j] = randomVal(numB, numE);
            }
        }
        return ans;
    }

    public static int randomVal(int numBegin, int numEnd) {
        return random.nextInt(numEnd - numBegin + 1) + numBegin;
    }

}
