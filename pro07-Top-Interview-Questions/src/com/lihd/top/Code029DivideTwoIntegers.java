package com.lihd.top;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 17:17
 */
public class Code029DivideTwoIntegers {



    public static int divide(int x, int y) {
        if (x == Integer.MIN_VALUE && y == -1) {
            return Integer.MAX_VALUE;
        }
        int a = x < 0 ? x : -x;
        int b = y < 0 ? y : -y;
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            // 左移右移后之不能变, 证明没有溢出, a还得小于等于这个值
            if((b << i)>> i == b && a <= (b << i)){
                a = a - (b << i);
                ans |= 1 << i;
            }
        }
        return x < 0 ^ y < 0 ? -ans : ans;
    }


    public static void main(String[] args) {
        System.out.println(divide(-7, -3));
        System.out.println((-3 << 31 >> 31));
        System.out.println((-3 << 30 >> 30));
        System.out.println((-3 << 29 >> 29));
        System.out.println((-3 << 28 >> 28));
    }


}
