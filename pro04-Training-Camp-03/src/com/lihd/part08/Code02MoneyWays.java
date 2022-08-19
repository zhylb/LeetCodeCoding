package com.lihd.part08;

import ans.class08.Code02_MoneyWays;
import com.sun.istack.internal.NotNull;


/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/9 12:40
 */
public class Code02MoneyWays {

    public static int moneyWaysDp(@NotNull int[] prototype, @NotNull int[] single, int aim) {
        int[][] prototypeMoneyDpArr = prototypeMoneyDpArr(prototype, aim);
        int[][] singleMoneyDpArr = singleMoneyDpArr(single, aim);
        int ans = 0;
        if (prototypeMoneyDpArr == null && singleMoneyDpArr == null) {
            return 0;
        }
        if (prototypeMoneyDpArr == null) {
            return singleMoneyDpArr[singleMoneyDpArr.length - 1][aim];
        }
        if (singleMoneyDpArr == null) {
            return prototypeMoneyDpArr[prototypeMoneyDpArr.length - 1][aim];
        }
        for (int money = 0; money <= aim; money++) {
            ans += prototypeMoneyDpArr[prototype.length - 1][money]
                    * singleMoneyDpArr[singleMoneyDpArr.length - 1][aim - money];
        }
        return ans;

    }

    public static int[][] prototypeMoneyDpArr(int[] prototype, int aim) {
        if (prototype.length == 0) {
            return null;
        }
        int n = prototype.length;
        int[][] dp = new int[n][aim + 1];
        //初始化第一行
        for (int index = 0; index < n; index++) {
            dp[index][0] = 1;
        }

//        for (int money = 0; money <= aim; money++) {
//            dp[0][money] = money % prototype[0] == 0 ? 1 : 0;
//        }
        //初始化第一列
        for (int index = 0; prototype[0] * index <= aim; index++) {
            dp[0][prototype[0] * index] = 1;
        }

        for (int index = 1; index < n; index++) {
            for (int money = 1; money <= aim; money++) {
                //不使用当前的任何一张
                dp[index][money] = dp[index - 1][money];
                //使用当前的硬币，有前提条件
                if (money - prototype[index] >= 0) {
                    dp[index][money] += dp[index][money - prototype[index]];
                }
            }
        }
        return dp;
    }

    public static int[][] singleMoneyDpArr(int[] single, int aim) {
        if (single.length == 0) {
            return null;
        }
        int n = single.length;
        int[][] dp = new int[n][aim + 1];
        //初始化第一列
        for (int index = 0; index < n; index++) {
            dp[index][0] = 1;
        }
        //初始化第一列
        if (single[0] <= aim) {
            dp[0][single[0]] = 1;
        }
        for (int index = 1; index < n; index++) {
            for (int money = 1; money <= aim; money++) {
                //不使用当前的硬币
                dp[index][money] = dp[index - 1][money];
                //使用当前的硬币，有前提条件
                if (money - single[index] >= 0) {
                    dp[index][money] += dp[index - 1][money - single[index]];
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] prototype = {7, 6, 3, 4};
        int[] single = {1000, 3, 2, 4, 9, 8};
        int aim = 100;
        int moneyWaysDp = moneyWaysDp(prototype, single, aim);
        int moneyWays = Code02_MoneyWays.moneyWays(prototype, single, aim);
        System.out.println(moneyWaysDp);
        System.out.println(moneyWays);
    }
}
