package com.lihd.part13;

import java.util.HashMap;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/21 18:00
 */
public class Code09CoinsWay {


    public static boolean checkWrongData(int[] money, int aim) {
        return money == null || money.length == 0 || aim < 0;
    }

    public static int ways1(int[] money, int aim) {
        if (checkWrongData(money, aim)) {
            return 0;
        }
        return waysRec1(money, 0, aim);
    }

    //外部调用我 请保证 rest >= 0
    public static int waysRec1(int[] money, int index, int rest) {
        if (index == money.length) {
            return rest == 0 ? 1 : 0;
        }
        int ans = 0;
        for (int i = 0; rest >= money[index] * i ; i++) {
            ans += waysRec1(money, index + 1, rest - money[index] * i);
        }
        return ans;
    }

    //========== 没想到使用 傻缓存还挺难改 ==========
    public static int ways2(int[] money, int aim) {
        if (money == null || money.length == 0 || aim < 0) {
            return 0;
        }
        HashMap<String, Integer> cache = new HashMap<>();
        return waysRec2(money, 0, aim, cache);
    }

    public static void mapSave(HashMap<String, Integer> cache,int result,  int ... args) {
        StringBuilder sb = new StringBuilder();
        for (int arg : args) {
            sb.append(arg);
            sb.append(".");
        }
        cache.put(sb.toString(), result);
    }

    public static boolean mapContains(HashMap<String, Integer> cache, int ... args) {
        StringBuilder sb = new StringBuilder();
        for (int arg : args) {
            sb.append(arg);
            sb.append(".");
        }
        return cache.containsKey(sb.toString());
    }

    public static int mapGet(HashMap<String, Integer> cache, int ... args) {
        StringBuilder sb = new StringBuilder();
        for (int arg : args) {
            sb.append(arg);
            sb.append(".");
        }
        return cache.get(sb.toString());
    }

    public static int waysRec2(int[] money, int index, int rest, HashMap<String, Integer> cache) {
        if (mapContains(cache, index, rest)) {
            return mapGet(cache, index, rest);
        }
        if (index == money.length) {
            mapSave(cache,rest == 0 ? 1 : 0,index, rest);
            return rest == 0 ? 1 : 0;
        }
        int ans = 0;
        for (int i = 0; rest >= money[index] * i ; i++) {
            ans += waysRec1(money, index + 1, rest - money[index] * i);
        }
        mapSave(cache,ans, index, rest);
        return ans;
    }

    // ============= 下面改成经典的动态规划 =============
    public static int ways3(int[] money, int aim) {
        if (checkWrongData(money, aim)) {
            return 0;
        }
        int[][] dp = new int[money.length + 1][aim + 1];
        dp[money.length][0] = 1;

        for (int index = money.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = 0;
                for (int i = 0; rest >= money[index] * i ; i++) {
                    ans += dp[index + 1][rest - money[index] * i];
                }
                dp[index][rest] =  ans;
            }
        }
        return dp[0][aim];
    }
    //==== 下面的优化是重点 ===
    //================= 下面进行 优化 怎么优化的还是很神奇的 ========
    public static int ways4(int[] money, int aim) {
        if (checkWrongData(money, aim)) {
            return 0;
        }
        int[][] dp = new int[money.length + 1][aim + 1];
        dp[money.length][0] = 1;
        for (int index = money.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= money[index]) {
                    dp[index][rest] += dp[index][rest - money[index]];
                }
            }
        }
        return dp[0][aim];
    }


    public static void main(String[] args) {
        int[] money = {5, 10, 50, 100};
        int aim = 1000;
        int ways1 = ways1(money, aim);
        int ways2 = ways2(money, aim);
        int ways3 = ways3(money, aim);
        int ways4 = ways4(money, aim);
        System.out.println("ways1 = " + ways1);
        System.out.println("ways2 = " + ways2);
        System.out.println("ways3 = " + ways3);
        System.out.println("ways4 = " + ways4);
    }
}
