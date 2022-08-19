package com.lihd.part04;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/14 9:22
 */
public class Code03Nim {


    public static boolean firstWin(int[] arr) {
        int xor = 0;
        for (int x : arr) {
            xor ^= x;
        }
        return xor != 0;
    }

}
