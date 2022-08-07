package com.lihd.part01;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/9 21:21
 */
public class Code03ParenthesesDeep {


    public static int deep(String valid) {
        char[] chs = valid.toCharArray();
        int count = 0;
        int ans = 0;

        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '(') {
                count++;
            } else {
                count--;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }


    public static int maxLength(String s) {
        char[] chs = s.toCharArray();
        int[] dp = new int[chs.length];
        //dp[0] = 0不用填入，因为默认就是0
        int max = 0;
        for (int i = 1; i < chs.length; i++) {
            if (chs[i] == ')') {
                int leftIndex = i - dp[i - 1] - 1;
                if (leftIndex >= 0 && chs[leftIndex] == '(') {
                    dp[i] = dp[i - 1] + 2 + (leftIndex > 0 ? dp[leftIndex - 1] : 0);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String canCanNeed = "((()()(())()))";
        System.out.println(maxLength(canCanNeed));
        System.out.println(deep(canCanNeed));
    }
}
