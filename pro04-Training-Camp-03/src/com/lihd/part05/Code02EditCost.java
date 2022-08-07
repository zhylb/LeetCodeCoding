package com.lihd.part05;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/4 21:32
 */
public class Code02EditCost {


    public static int editCost(String from, String to, int ic, int dc, int rc) {

        int n = from.length() + 1;
        int m = to.length() + 1;
        int[][] dp = new int[n][m];

        //第一列
        for (int i = 0; i < n; i++) {
            dp[i][0] = i * dc;
        }
        //第一行
        for (int i = 0; i < m; i++) {
            dp[0][i] = i * ic;
        }

        //普通位置

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (from.charAt(i - 1) == to.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
            }
        }
        return dp[n - 1][m - 1];
    }


//    public static int minCost(String from, String to, int ic, int dc, int rc) {
//
//        int n = from.length() + 1;
//        int m = to.length() + 1;
//
//
//        int[] dp = new int[m];
//        //按照行填入
//        for (int i = 0; i < m; i++) {
//            dp[i] = i * ic;
//        }
//
//        //按照行填入
//        for (int i = 1; i < n; i++) {
//            for (int j = 1; j < m; j++) {
//                if (from.charAt(i - 1) == to.charAt(j - 1)) {
//                    dp[j] = dp[j - 1];
//                } else {
//                    dp[j] = dp[j - 1] + rc;
//                }
//
//                dp[j] = Math.min(dp[j], )
//            }
//        }
//
//
//    }



    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(editCost(str1, str2, 5, 3, 2));

        str1 = "abcdf";
        str2 = "ab12cd3";
        System.out.println(editCost(str1, str2, 3, 2, 4));

        str1 = "";
        str2 = "ab12cd3";
        System.out.println(editCost(str1, str2, 1, 7, 5));

        str1 = "abcdf";
        str2 = "";
        System.out.println(editCost(str1, str2, 2, 9, 8));
    }


}
