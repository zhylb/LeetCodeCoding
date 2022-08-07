package com.lihd.exer01;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/25 20:46
 */
public class Matrix {
















    //========= 解方程的代码


    public static int[] getResultInteger(int[][] matrix) {
        double[][] m = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = matrix[i][j];
            }
        }
        double[] result = getResult(m);
        int[] ans = new int[result.length];
        for (int i = 0; i < result.length; i++) {
            ans[i] = (int) Math.round(result[i]);
        }
        return ans;
    }
    public static double[] getResult( double[][] matrix) {
        int n = matrix.length;
        simple(n, matrix);
        double[] result = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double temp = matrix[i][n];
            for (int j = n - 1; j >= 0; j--) {
                if (i < j && matrix[i][j] != 0) {
                    temp = temp - result[j] * matrix[i][j];
                }
            }
            temp /= matrix[i][i];
            result[i] = temp;
        }
//        for (int k = 0; k < result.length; k++) {
//            System.out.println("X" + (k + 1) + " = " + result[k]);
//        }
        return result;
    }

    private static void simple(int n, double[][] matrix) {
        for (int k = 0; k < n; k++) {
            if (matrix[k][k] == 0) {
                changeRow(n, k, matrix);
            }

            for (int i = 0; i < n; i++) {
                // 记录对角线元素，作为除数
                double temp = matrix[i][k];
                for (int j = 0; j < n + 1; j++) {
                    // i<k时,i行已经计算完成
                    if (i < k)
                        break;
                    if (temp == 0)
                        continue;
                    if (temp != 1) {
                        matrix[i][j] /= temp;
                    }

                    if (i > k)
                        matrix[i][j] -= matrix[k][j];
                }
            }
        }

    }

    private static void changeRow(int n, int k, double[][] matrix) {
        double[] temp = new double[n + 1];
        // if()
        for (int i = k; i < n; i++) {
            // 已到最后一列,不能继续交换
            if (i + 1 == n && matrix[k][k] == 0) {
                System.out.println("无解或有不唯一解！");
                System.exit(1);
            }

            for (int j = 0; j < n + 1; j++) {
                temp[j] = matrix[k][j];
                matrix[k][j] = matrix[i + 1][j];
                matrix[i + 1][j] = temp[j];
            }
            if (matrix[k][k] != 0)
                return;
        }
    }

}
