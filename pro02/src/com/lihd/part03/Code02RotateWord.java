package com.lihd.part03;

/**
 * 判断两个单词是否是旋转单词
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/27 16:55
 */
public class Code02RotateWord {


    public static boolean isRotateWord(String a, String b) {
        if (a == null || b == null) {
            return false;
        }
        if (a.length() != b.length()) {
            return false;
        }
        String src = a + a;
        return KMP.indexOf(src, b) != -1;
    }

    //使用暴力解
    public static boolean isRotateWord2(String a, String b) {
        return false;
    }

}
