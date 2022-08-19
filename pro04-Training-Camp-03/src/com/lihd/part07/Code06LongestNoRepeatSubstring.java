package com.lihd.part07;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/7 9:28
 */
public class Code06LongestNoRepeatSubstring {
    public static int getLongestNoRepeatSubString(@NotNull String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chs = s.toCharArray();
        int[] dp = new int[chs.length];
        //下面这两行一定不能少，尤其是 map.put()这个。
        dp[0] = 1;
        map.put(chs[0], 0);
        for (int i = 1; i < chs.length; i++) {
            Integer lastIndex = map.getOrDefault(chs[i], -1);
            int choice1 = i - lastIndex;
            int choice2 = dp[i - 1] + 1;
            dp[i] = Math.min(choice1, choice2);
            map.put(chs[i], i);
        }
        int ans = 0;
        for (int j : dp) {
            ans = Math.max(ans, j);
        }
        return ans;
    }

    // for test
    public static String getRandomString(int len) {
        char[] str = new char[len];
        int base = 'a';
        int range = 'z' - 'a' + 1;
        for (int i = 0; i != len; i++) {
            str[i] = (char) ((int) (Math.random() * range) + base);
        }
        return String.valueOf(str);
    }

    // for test
    public static String maxUniqueString(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = -1;
        int pre = -1;
        int cur = 0;
        int end = -1;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            if (cur > len) {
                len = cur;
                end = i;
            }
            map[chas[i]] = i;
        }
        return str.substring(end - len + 1, end + 1);
    }

    public static void main(String[] args) {


        for (int i = 0; i < 200000; i++) {
            String str = getRandomString(20);
            int ans1 = getLongestNoRepeatSubString(str);
            int ans2 = maxUniqueString(str).length();
            if (ans1 != ans2) {
                System.out.println(str);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(maxUniqueString(str));
                break;
            }
        }
        System.out.println("测试成功");
    }
}
