package com.lihd.part07;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/7 9:53
 */
public class Code03MinPatches {


    public static int minPatches(int[] arr, int aim) {
        int range = 0;
        int addNums = 0;
        Arrays.sort(arr);
        for (int j : arr) {
            while (j > range + 1) {
                // range 加上一个range + 1
                range += range + 1;
                addNums++;
                if (range >= aim) {
                    return addNums;
                }
            }
            //此时已经非常节省
            range += j;
            if (range >= aim) {
                return addNums;
            }
        }

        //如果还是没达到要求，继续变
        while (range < aim) {
            range += range + 1;
            addNums ++;
            if (range < 0) {
                return addNums;
            }
        }

        return addNums;
    }

    public static void main(String[] args) {
        int[] test = { 1, 2, 31, 33 };
        int n = 2147483647;
        System.out.println(minPatches(test, n));
    }

}
