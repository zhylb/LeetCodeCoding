package com.lihd.part06;

import java.util.Queue;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/7 13:03
 */
public class Code02ExpressionNumber {

    public static int expressionNumber(String express, boolean desired) {
        if (!isValid(express)) {
            return 0;
        }
        int n = express.length();
        int[][] trueDp = new int[n][n];
        int[][] falseDp = new int[n][n];
        char[] chs = express.toCharArray();

        for (int i = 0; i < n; i++) {
            trueDp[i][i] = chs[i] == '1' ? 1 : 0;
            falseDp[i][i] = chs[i] == '0' ? 1 : 0;
        }

        for (int l = n - 3; l >= 0; l -= 2) {
            for (int r = l + 2; r < n; r += 2) {
                for (int i = l + 1; i < r; i+= 2) {
                    //枚举每个分割符
                    if (chs[i] == '&') {
                        trueDp[l][r] += trueDp[l][i - 1] * trueDp[i + 1][r];
                        falseDp[l][r] += trueDp[l][i - 1] * falseDp[i + 1][r];
                        falseDp[l][r] += falseDp[l][i - 1] * trueDp[i + 1][r];
                        falseDp[l][r] += falseDp[l][i - 1] * falseDp[i + 1][r];

                    } else if (chs[i] == '|') {
                        trueDp[l][r] += trueDp[l][i - 1] * trueDp[i + 1][r];
                        trueDp[l][r] += falseDp[l][i - 1] * trueDp[i + 1][r];
                        trueDp[l][r] += trueDp[l][i - 1] * falseDp[i + 1][r];

                        falseDp[l][r] += falseDp[l][i - 1] * falseDp[i + 1][r];
                    } else {
                        trueDp[l][r] += trueDp[l][i - 1] * falseDp[i + 1][r];
                        trueDp[l][r] += falseDp[l][i - 1] * trueDp[i + 1][r];

                        falseDp[l][r] += trueDp[l][i - 1] * trueDp[i + 1][r];
                        falseDp[l][r] += falseDp[l][i - 1] * falseDp[i + 1][r];
                    }

                }
            }


        }
        return desired ? trueDp[0][n - 1] : falseDp[0][n - 1];

    }

    public static boolean isValid(String express) {
        if (express == null || express.length() % 2 == 0) {
            //是偶数个是不行的
            return false;
        }
        char[] chs = express.toCharArray();
        for (int i = 0; i < chs.length; i += 2) {
            if (chs[i] != '1' && chs[i] != '0') {
                return false;
            }
        }
        for (int i = 1; i < chs.length; i += 2) {
            if (chs[i] != '^' && chs[i] != '|' && chs[i] != '&') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String express = "1^0&0|1&1^0&0^1|0|1&1";
        boolean desired = true;
        System.out.println(expressionNumber(express, desired));
    }

}
