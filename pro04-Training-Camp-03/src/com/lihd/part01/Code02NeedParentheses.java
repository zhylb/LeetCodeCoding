package com.lihd.part01;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/9 21:12
 */
public class Code02NeedParentheses {
    public static boolean isValid(String s) {
        char[] chs = s.toCharArray();
        int count = 0;
        for (char ch : chs) {
            if (count < 0) {
                return false;
            } else if (ch == ')') {
                count--;
            } else {
                count++;
            }
        }
        return count == 0;
    }

    public static int needParentheses(String s) {
        char[] chs = s.toCharArray();
        int count = 0;
        int need = 0;
        for (char ch : chs) {
            if (ch == '(') {
                count++;
            } else {
                if (count == 0) {
                    need++;
                } else {
                    count--;
                }
            }
        }
        return count + need;
    }


    public static void main(String[] args) {
        String canCanNeed = "(())";
        System.out.println(isValid(canCanNeed));
        System.out.println(needParentheses(canCanNeed));
    }
}
