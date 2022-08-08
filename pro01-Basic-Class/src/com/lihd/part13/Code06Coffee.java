package com.lihd.part13;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/23 16:20
 */
public class Code06Coffee {
    //目前只考虑洗咖啡问题


    public static int wash(int[] drinks, int a, int b, int index, int washFinish) {
        if (drinks.length == index ) {
            return 0;
        }
        //什么时候能洗 + 洗的时间 就是新的洗完时间
        int newWashFinish = Math.max(drinks[index], washFinish) + a;
        int washOther = wash(drinks, a, b, index + 1, newWashFinish);
        int ansWash = Math.max(newWashFinish, washOther);
        //选择不洗
        int dryFinish = drinks[index] + b;
        int washOther2 = wash(drinks, a, b, index + 1, washFinish);
        int ansDry = Math.max(dryFinish, washOther2);
        return Math.min(ansDry, ansWash);
    }

    public static int washDp(int[] drinks, int a, int b) {
        int washAll = 0;
        for (int drink : drinks) {
            washAll = Math.max(washAll, drink) + a;
        }
        //washAll的值就是全洗的时间
        int[][] dp = new int[drinks.length + 1][washAll + 1];
        for (int index = drinks.length - 1; index >= 0; index--) {
            for (int washFinish = 0; washFinish <= washAll; washFinish++) {
                //什么时候能洗 + 洗的时间 就是新的洗完时间
                int newWashFinish = Math.max(drinks[index], washFinish) + a;
                int ansWash = Integer.MAX_VALUE;
                if (newWashFinish <= washAll) {
                    ansWash = Math.max(newWashFinish, dp[index + 1][newWashFinish]);
                }
                //选择不洗
                int dryFinish = drinks[index] + b;
                int ansDry = Math.max(dryFinish, dp[index + 1][washFinish]);
                dp[index][washFinish] =  Math.min(ansDry, ansWash);
            }
        }
//        System.out.println(Arrays.deepToString(dp));
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] test = { 1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15 };
        int a1 = 3;
        int b1 = 10;
        int wash = wash(test, a1, b1, 0, 0);
        int washDp = washDp(test, a1, b1);
        System.out.println("wash = " + wash);
        System.out.println("washDp = " + washDp);
    }

}
