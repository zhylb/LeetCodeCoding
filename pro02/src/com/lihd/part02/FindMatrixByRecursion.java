package com.lihd.part02;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/25 16:53
 */
public class FindMatrixByRecursion {

    public static void main(String[] args) {
        int[] inits = {1, 2};
        int[] params = {1, 1};
        System.out.println(f(25, inits, params));
    }

    //F(N) = 10F(N - 1) + 8F(N - 3) - 7F(N - 6)
    //init = 1
    //params = [10,0,8,0,0,-7]
    //num = you need return arr length !
    public static int[] createNums(int init, int[] params, int num) {
        int[] ans = new int[num];
        ans[0] = init;
        for (int i = 1; i < num; i++) {
            for (int j = 0; j < params.length; j++) {
                if (i > j) {
                    // 表示参数的值可用
                    // i = N + 1 i 的意义是 第 N + 1项 因为N在函数中是从1开始的, 而数组是从0开始的, 因此要 + 1
                    // j = N - j - 1  j 的意义是 第 N - j - 1 是否存在, 比如j= 0 表示 第N - 1项
                    // j = 1表示 第 N - 2项
                    //而 要进行计算 必须满足 这是第 i项时 , j所在的项存在
                    ans[i] += params[j] * ans[i - j - 1];
                }
            }
        }
        return ans;
    }

    public static int[] createNums(int[] inits, int[] params, int num) {
        int[] ans = new int[num];
        System.arraycopy(inits, 0, ans, 0, inits.length);
        for (int i = inits.length; i < num; i++) {
            for (int j = 0; j < params.length; j++) {
                ans[i] += params[j] * ans[i - j - 1];
            }
        }
        return ans;
    }



    public static int f(int n, int[] inits, int[] params) {
        //baseCase
        if (n < 1) {
            return 0;
        }
        //baseCase
        for (int i = 0; i < inits.length; i++) {
            if (n == i + 1) {
                return inits[i];
            }
        }
        int[][] transitionMatrix = getTheTransitionMatrix(inits, params);
        int[][] power = matrixPower(transitionMatrix, n - params.length);
        int ans = 0;
        //这里是前行乘以后列, 注意 inits数组的顺序是倒序
        //一定要处处细心
        for (int i = 0; i < inits.length; i++) {
            ans += power[i][0] * inits[inits.length - i -  1];
        }
        return ans;
    }

    public static int[][] matrixPower(int[][] matrix, int n) {
        int[][] ans = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            ans[i][i] = 1;
        }
        int[][] temp = matrix;
        while (n != 0) {
            if ((n & 1) != 0) {
                ans = matrixMul(temp, ans);
            }
            temp = matrixMul(temp, temp);
            n >>= 1;
        }
        return ans;
    }


    public static int[][] getTheTransitionMatrix(int[] inits, int[] params) {
        int[] f = createNums(inits, params, params.length * 2);
        int[][] matrixResultReverse = getMatrixResultReverse(f, params.length);
        squareMatrixReverse(matrixResultReverse);
        return matrixResultReverse;
    }

    public static int[][] getMatrixResultReverse(int[] f, int len) {
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            matrix[i] = solvingEquations(f, len, i);
        }
        return matrix;
    }

    /**
     *
     * @param f   前n项的值 组成的数组
     * @param len 矩阵大小[len][len + 1] 每一行最后一个位置放
     * @param col 第几列 代表这是第几列行列式 算法内部会减去这个值
     * @return int[] 方程的解
     * @author lihd
     * @creed: 春春的飞舞
     * @date 2022/5/25 21:11
     */
    public static int[] solvingEquations(int[] f, int len, int col) {
        int[][] result = new int[len][len + 1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                result[i][j] = f[len + i - j - 1];
            }
            result[i][len] = f[len + i - col];
        }
        return Matrix.getResultInteger(result);
    }

    public static void squareMatrixReverse(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                swapMatrixEle(matrix, i, j);
            }
        }
    }

    public static void swapMatrixEle(int[][] matrix, int i, int j) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }

    public static int[][] matrixMul(int[][] m1, int[][] m2) {
        int[][] ans = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    ans[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return ans;
    }
}
