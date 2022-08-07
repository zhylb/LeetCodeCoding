package com.lihd.part05;

import sun.security.krb5.internal.PAData;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/28 19:07
 */
public class Code01Manacher {
    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        //s 长度至少为 1
        //处理过的串至少为  1 * 2 + 1 = 3 因此一定会进入下面的for循环
        char[] chs = addAuxiliaryWords(s);
        System.out.println(new String(chs));
        int[] pArr = new int[chs.length];
        int max = Integer.MIN_VALUE;//max 一定会被更新, 因为一个字符也算回文长度为 1
        int R = 1;
        int C = 0;
        int i = 1;
        while (i < chs.length) {
            pArr[i] = i < R ? Math.min(pArr[(C << 1) - i], R - i) : 1;
            while (i + pArr[i] < chs.length && i - pArr[i] >= 0
                    && chs[i + pArr[i]] == chs[ i - pArr[i]]) {
                pArr[i] ++;
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(pArr[i], max);
            i ++;
        }
        return max - 1;
    }


    public static int forceMethod(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] chs = addAuxiliaryWords(s);
        int max = 1;
        for (int i = 0; i < chs.length; i++) {
            int radius = 1;
            while (i - radius >= 0 && i + radius < chs.length && chs[i - radius] == chs[i + radius]) {
                radius++;
            }
            max = Math.max(radius - 1, max);
        }
        return max;
    }

    //辅助字符 char[]数组默认值 0
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
        String s = "xygabaxabaayg";
        System.out.println(manacher(s));
        System.out.println(forceMethod(s));
    }


}
