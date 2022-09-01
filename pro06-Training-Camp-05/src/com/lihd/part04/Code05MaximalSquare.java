package com.lihd.part04;

/**
 * <a href="https://leetcode.cn/problems/maximal-square/submissions/">...</a>
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/31 21:26
 */
public class Code05MaximalSquare {
    /**
     * 9 ms, faster than 51.50%
     * 67.6 MB, less than 6.89%
     */
    public static int maximalSquare(char[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        int[][] dp = new int[r][c];

        int ans = 0;
        for (int i = 0; i < r; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            ans = Math.max(dp[i][0], ans);

        }

        for (int i = 0; i < c; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            ans = Math.max(dp[0][i], ans);

        }
        for (int row = 1; row < r; row++) {
            for (int col = 1; col < c; col++) {
                if (matrix[row][col] == '1') {
                    dp[row][col] = 1 + Math.min(dp[row - 1][col - 1], Math.min(dp[row - 1][col], dp[row][col - 1]));
                    ans = Math.max(dp[row][col], ans);
                }
            }
        }
        return ans * ans;
    }

    public static void main(String[] args) {
        char[][] m =
                {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'},
                        {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(maximalSquare(m));
    }
}
