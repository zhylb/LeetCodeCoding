package com.lihd.part06;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/7 10:34
 */
public class Code06StringCross {

    public static boolean isStringCross(String s1, String s2, String target) {

        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        char[] chs3 = target.toCharArray();
        int row = chs1.length + 1;
        int col = chs2.length + 1;

        boolean[][] dp = new boolean[row][col];
        dp[0][0] = true;
        for (int i = 1; i < row; i++) {
            dp[i][0] = (dp[i - 1][0] && chs1[i - 1] == chs3[i - 1]);
        }
        for (int i = 1; i < col; i++) {
            dp[0][i] = (dp[0][i - 1] && chs2[i - 1] == chs3[i - 1]);
        }


        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                if(dp[r - 1][c] && chs1[r - 1] == chs3[r + c - 1]){
                    dp[r][c] = true;
                }

                if (dp[r][c - 1] && chs2[c - 1] == chs3[r + c - 1]) {
                    dp[r][c] = true;
                }

            }
        }

        return dp[col - 1][row - 1];


    }

    public static void main(String[] args) {
        String str1 = "1234";
        String str2 = "abcd";
        String aim = "1a23bcd4";
        boolean cross = isStringCross(str1, str2, aim);
        System.out.println(cross);
    }

}
