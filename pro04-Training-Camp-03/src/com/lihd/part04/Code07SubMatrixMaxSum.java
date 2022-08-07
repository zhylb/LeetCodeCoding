package com.lihd.part04;

import com.sun.istack.internal.NotNull;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/4 13:09
 */
public class Code07SubMatrixMaxSum {

    public static int subMatrixMaxSum(@NotNull int[][] matrix) {
        int ans = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            int[] arr = new int[col];
            for (int j = i; j < row; j++) {
                // i - j 遍历所有的行
                //准备好数组
                // 这里的参数 别传错了 是 j 不是 i
                arrSum(arr, matrix[j]);
                int sum = 0;
                int max = Integer.MIN_VALUE;
                for (Integer val : arr) {
                    sum += val;
                    max = Math.max(max, sum);
                    sum = Math.max(sum, 0);
                }
                ans = Math.max(ans, max);
            }
        }
        return ans;
    }

    private static void arrSum(int[] arr, int[] add) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] + add[i];
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
        int i = subMatrixMaxSum(matrix);
        System.out.println(i);
    }




}
