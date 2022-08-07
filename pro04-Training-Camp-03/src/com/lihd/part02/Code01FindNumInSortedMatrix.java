package com.lihd.part02;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/9 20:55
 */
public class Code01FindNumInSortedMatrix {
    public static boolean contains(int[][] matrix, int k) {

        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        int r = row;
        int c = 0;
        while (row >= 0 && c <= col) {
            if (matrix[r][c] == k) {
                return true;
            } else if (k < matrix[r][c]) {
                r--;
            } else {
                c++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
                { 10, 12, 13, 15, 16, 17, 18 },// 1
                { 23, 24, 25, 26, 27, 28, 29 },// 2
                { 44, 45, 46, 47, 48, 49, 50 },// 3
                { 65, 66, 67, 68, 69, 70, 71 },// 4
                { 96, 97, 98, 99, 100, 111, 122 },// 5
                { 166, 176, 186, 187, 190, 195, 200 },// 6
                { 233, 243, 321, 341, 356, 370, 380 } // 7
        };
        int K = 233;
        System.out.println(contains(matrix, K));
    }
}
