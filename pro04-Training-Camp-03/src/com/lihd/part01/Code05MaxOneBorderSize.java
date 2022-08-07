package com.lihd.part01;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/9 21:42
 */
public class Code05MaxOneBorderSize {

    public static int getMaxSquare(int[][] matrix) {
        int n = matrix.length;
        int r = matrix[0].length;

        int[][] rightMaxOne = new int[n][r];
        int[][] downMaxOne = new int[n][r];

        //填好边界
        for (int i = 0; i < n; i++) {
            rightMaxOne[i][r - 1] = matrix[i][r - 1];
        }
        for (int i = 0; i < r; i++) {
            downMaxOne[n - 1][i] = matrix[n - 1][i];
        }

        //填好其他

        //行 -》  列 《-
        for (int i = 0; i < n; i++) {
            for (int j = r - 2; j >= 0; j--) {
                rightMaxOne[i][j] = matrix[i][j] == 0 ? 0 : 1 + rightMaxOne[i][j + 1];
            }
        }

        //i 代表 row, j 代表 col
        //行倒着填，列正向填
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < r; j++) {
                downMaxOne[i][j] = matrix[i][j] == 0 ? 0 : 1 + downMaxOne[i + 1][j];
            }
        }
        int ans = 0;
        //枚举所有点
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                for (int sl = 1; sl < n + r; sl++) {
                    if (i + sl <= n && j + sl <= r) {
                        if (downMaxOne[i][j] >= sl && rightMaxOne[i][j] >= sl && downMaxOne[i][j + sl - 1] >= sl && rightMaxOne[i + sl - 1][j] >= sl) {
                            ans = Math.max(ans, sl);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }



}
