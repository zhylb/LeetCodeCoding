package com.lihd.part05;

import java.awt.color.ColorSpace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/4 22:02
 */
public class Code01DeleteMinCost {

    //**************** 方式 一 ***************************
    public static int minDeleteCost(String s1, String s2) {
        List<String> ans = new ArrayList<>();
        getSubSequence(s2,0,"", ans);
        Collections.sort(ans, (a,b) -> b.length() - a.length());
        for (String s : ans) {
            if (s1.contains(s)) {
                return s2.length() - s.length();
            }
        }
        return -1;
    }
    public static void getSubSequence(String str, int index, String prefix, List<String> ans) {
        if (index == str.length()) {
            ans.add(prefix);
        } else {
            getSubSequence(str, index + 1, prefix, ans);
            getSubSequence(str, index + 1, prefix + str.charAt(index), ans);
        }
    }

    //**************** 方式 二 ***************************
    public static int minDeleteCost2(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }

        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();

        //行
        int m = chs2.length;
        //列
        int n = chs1.length;

        int ans = m;

        int[][] dp = new int[m][n];


        for (int colBegin = 0; colBegin < n; colBegin++) {

            //填好第一个值
            dp[0][colBegin] = chs1[colBegin] == chs2[0] ? 0 : m;

            //填好第一列的值
            for (int row = 1; row < m; row++) {
                dp[row][colBegin] = (chs1[colBegin] == chs2[row] || dp[row - 1][colBegin] != m) ? row : m;
            }
            ans = Math.min(ans, dp[m - 1][colBegin]);

            //填好其他列
            for (int colEnd = colBegin + 1; colEnd < n && colEnd - colBegin + 1 <= m; colEnd++) {

                //firstRow前面的都不用管
                int firstRow = colEnd - colBegin;
                dp[firstRow][colEnd] = (dp[firstRow - 1][colEnd - 1] == 0 && chs2[firstRow] == chs1[colEnd]) ? 0 : m;
                //填好 firstRow后面的值
                for (int row = firstRow + 1; row < m; row++) {
                    //真正的一般情况
                    dp[row][colEnd] = m;
//                    if (chs1[colEnd] == chs2[row]) {
//                        dp[row][colEnd] = dp[row - 1][colEnd - 1];
//                    }
//                    dp[row][colEnd] = Math.min(dp[row][colEnd], dp[row - 1][colEnd] + 1);


                    if (dp[row - 1][colEnd] != m) {
                        dp[row][colEnd] = Math.min(dp[row][colEnd], dp[row - 1][colEnd] + 1);
                    }

                    if (dp[row - 1][colEnd - 1] != m && chs1[colEnd] == chs2[row]) {
                        dp[row][colEnd] = Math.min(dp[row][colEnd], dp[row - 1][colEnd - 1]);
                    }


                }
                ans = Math.min(ans, dp[m - 1][colEnd]);

            }
        }
        return ans;

    }

    public static void main(String[] args) {
        int i = minDeleteCost("abcde", "axbcc");
        int i2 = minDeleteCost2("abcde", "axbcc");
        System.out.println("i = " + i);
        System.out.println("i2 = " + i2);
    }
}
