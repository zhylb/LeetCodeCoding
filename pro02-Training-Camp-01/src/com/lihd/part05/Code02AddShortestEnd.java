package com.lihd.part05;

import com.sun.media.sound.SF2Sample;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/28 22:40
 */
public class Code02AddShortestEnd {


    public static String getShortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] chs = addAuxiliaryWords(s);
        int[] ans = new int[chs.length];
        int len = 1;
        int R = 1;
        int C = 0;
        for (int i = 1; i < chs.length; i++) {
            ans[i] = i < R ? Math.min(ans[(C << 1) - i], R - i) : 1;
            while (i + ans[i] < chs.length && i - ans[i] >= 0 && chs[i + ans[i]] == chs[i - ans[i]]) {
                ans[i]++;
            }

            if (i + ans[i] > R) {
                R = i + ans[i];
                C = i;
            }
            if (R == chs.length) {
                len = ans[i] - 1;
                break;
            }
        }
        char[] ret = new char[s.length() - len];
        for (int i = 0; i < ret.length; i++) {
            ret[ret.length - i - 1] = chs[i << 1 | 1];
        }
        return new String(ret);
    }

    public static char[] addAuxiliaryWords(String s) {
        if (s == null) {
            return null;
        }
        char[] chs = s.toCharArray();
        char[] ans = new char[chs.length << 1 | 1];

        for (int i = 0; i < chs.length; i++) {
            ans[i << 1 | 1] = chs[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "789789121787121313abba";
        String shortestEnd = getShortestEnd(s);
        System.out.println(shortestEnd);
    }

}
