package com.lihd.part03;

import java.util.UUID;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/28 0:04
 */
public class TestKmpFaster {


    public static String[]  createString(int len) {
        StringBuilder sb = new StringBuilder(len);
        int random = (int) (Math.random() * len);
        String ans = String.valueOf(UUID.randomUUID());
        for (int i = 0; i < len; i++) {
            String s = String.valueOf(UUID.randomUUID());
            sb.append(s);
            if (i == random) {
                ans = s;
            }
        }
        String s = sb.toString();
        return new String[]{s, ans};
//        return new String[]{s, s.substring(s.length()/2)};
    }

    /*
    int testTime = 100_000;
    int len = 100;
    jdk : String.indexOf cost : 11222 ms
    kmp : kmp.indexOf cost : 10332 ms

    修改了 match的长度, 怎么看都是对暴力解法的不利, 可是实际上还是jdk快
    jdk : String.indexOf cost : 12194 ms
    kmp : kmp.indexOf cost : 14049 ms

     */

    public static void main(String[] args) {
        int testTime = 1000;
        int len = 10000;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < testTime; i++) {
            String[] string = createString(len);
            string[0].indexOf(string[1]);
        }
        long end = System.currentTimeMillis();
        System.out.println("jdk : String.indexOf cost : " + (end - begin) + " ms");
        begin = System.currentTimeMillis();
        for (int i = 0; i < testTime; i++) {
            String[] string = createString(len);
            Code01KMP.indexOf(string[0], string[1]);
        }
        end = System.currentTimeMillis();
        System.out.println("kmp : kmp.indexOf cost : " + (end - begin) + " ms");


    }
}
