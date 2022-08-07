package com.lihd.part03;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/26 17:22
 */
public class Code01KMP {

    public static int indexOf(String str, String target) {
        if (str == null || target == null || str.length() == 0|| str.length() < target.length()) {
            return -1;
        }
        if (target.length() == 0) {
            return 0;
        }
        char[] srcChs = str.toCharArray();
        char[] tarChs = target.toCharArray();
        int[] next = getNextArr(tarChs);
        int i = 0;
        int j = 0;
        //O(N)
        while (i < srcChs.length && j < tarChs.length) {
            if (srcChs[i] == tarChs[j]) {
                i++;
                j++;
            } else if (next[j] != -1) {
                j = next[j];
            } else {//此时j = 0
                i ++;
            }
        }
        return j == tarChs.length ? i - j : -1;
    }

    //传入的值绝对不是null 长度也不是0
    public static int[] getNextArr(char[] chs) {
        if (chs.length == 1) {
            return new int[]{-1};
        }
        int[] ans = new int[chs.length];
        ans[0] = -1;
        ans[1] = 0;
        int last = 0;
        int i = 2;
        //O(M)
        while (i < ans.length) {
            if (chs[last] == chs[i - 1]) {// 千万不要写成 ans[last] == ans[i = 1]
                ans[i++] = ++last;
            } else if (last > 0) {
                last = ans[last];
            } else {
                ans[i++] = 0;
            }
        }
        return ans;
    }
    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (KMP.indexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
                System.out.println(str);
                System.out.println(match);
                System.out.println(Arrays.toString(getNextArr(match.toCharArray())));
                System.out.println(indexOf(str,match));
                System.out.println(str.indexOf(match));
                break;
            }
        }
        System.out.println("test finish");
    }

}
