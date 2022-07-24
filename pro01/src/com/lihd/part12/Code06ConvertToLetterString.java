package com.lihd.part12;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/19 18:11
 */
public class Code06ConvertToLetterString {


    public static int getConditionCount(String str) {
        return getConditionCount(str.toCharArray(),0);
    }

    public static int getConditionCount(char[] chs, int i) {
        if (i == chs.length) {
            return 1;
        }
        //chs[i] = '0'-'9'
        if (chs[i] == '0') {
            //独自面对一个0 返回0 因为我无法独自面对0 这种情况被杀死
            return 0;
        }
        //chs[i] = '1'-'9'
        if (chs[i] == '1') {
            int ans = getConditionCount(chs, i + 1);
            if (i + 1 < chs.length) {
                //存在下一个 存在就肯定能组合
                ans += getConditionCount(chs, i + 2);
            }
            return ans;
        }
        if (chs[i] == '2') {
            int ans = getConditionCount(chs, i + 1);
            //20 - 26对应字母 超过26就没有了
            if (i + 1 < chs.length&& chs[i + 1] <= '6' && chs[i + 1] >= '0') {
                ans += getConditionCount(chs, i + 2);
            }
            return ans;
        }
        //chs[i] = '3'-'9' 只有一条分支 就是向下走一格
        return getConditionCount(chs, i + 1);
    }

    public static int getConditionCountDp(char[] chs, int index) {

        int[] dp = new int[chs.length + 2];
        dp[chs.length] = 1;
        for (int i = chs.length - 1; i >= 0; i--) {
            if (chs[i] == '0') {
                continue;
            }
            if (chs[i] == '1') {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else if (chs[i] == '2') {
                if (i + 1 < chs.length && chs[i + 1] <= '6' && chs[i + 1] >= '0') {
                    dp[i] = dp[i + 1] + dp[i + 2];
                } else {
                    dp[i] = dp[i + 1];
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String s = "111110111111";
        System.out.println(getConditionCount(s));
        int conditionCountDp = getConditionCountDp(s.toCharArray(), 0);
        System.out.println("conditionCountDp = " + conditionCountDp);
    }
}
