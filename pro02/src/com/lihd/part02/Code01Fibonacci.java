package com.lihd.part02;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/26 0:09
 */
public class Code01Fibonacci {

    public static int f1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    public static int f2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int lastOne = 1;
        int lastTwo;
        int cur = 1;
        //计算 n - 2次即可, 因为 此时lastOne = n - 2, lastOne = n- 1, cur = n
        for (int i = 0; i < n - 2; i++) {
            lastTwo = lastOne;
            lastOne = cur;
            //循环一次, cur更新一次
            cur = lastOne +lastTwo;
        }
        return cur;
    }
    //如果
    public static int f4(int n) {
        double a = Math.sqrt(5);
        return (int)Math.round(1 / a * (Math.pow((1 + a) / 2, n) - Math.pow((1 - a) / 2, n)));
    }

    public static void main(String[] args) {
        int f1 = f1(10);
        int f2 = f2(10);
        int f4 = f4(10);
        System.out.println("f1 = " + f1);
        System.out.println("f2 = " + f2);
        System.out.println("f4 = " + f4);
    }

}
