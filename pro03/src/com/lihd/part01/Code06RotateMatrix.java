package com.lihd.part01;

import com.sun.rowset.internal.Row;

import javax.xml.transform.sax.SAXTransformerFactory;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 10:33
 */
public class Code06RotateMatrix {

    public static void leftRotateMatrix(int[][] matrix) {
        int aRow = 0;
        int aCol = 0;
        int bRow = matrix.length - 1;
        int bCol = matrix[0].length - 1;
        while (aRow < bRow) {
            leftSwapOuterLayer(matrix, aRow, aCol, bRow, bCol);
            aRow ++;
            aCol ++;
            bRow --;
            bCol --;
        }
    }

    public static void rightRotateMatrix(int[][] matrix) {
        int aRow = 0;
        int aCol = 0;
        int bRow = matrix.length - 1;
        int bCol = matrix[0].length - 1;
        while (aRow < bRow) {
            rightSwapOutLayer(matrix, aRow, aCol, bRow, bCol);
            aRow ++;
            aCol ++;
            bRow --;
            bCol --;
        }
    }




    public static void leftSwapOuterLayer(int[][] matrix, int aRow, int aCol, int bRow, int bCol) {
        // i 只控制次数
        for (int i = 0; i < bRow - aRow; i++) {
            int temp = matrix[aRow][aCol + i];
            matrix[aRow][aCol + i] = matrix[aRow + i][bCol];
            matrix[aRow + i][bCol] = matrix[bRow][bCol - i];
            matrix[bRow][bCol - i] = matrix[bRow - i][aCol];
            matrix[bRow - i][aCol] = temp;
        }

    }

    public static void rightSwapOutLayer(int[][] matrix, int aRow, int aCol, int bRow, int bCol) {
        for (int i = 0; i < bRow - aRow; i++) {

            int temp = matrix[aRow][aCol + i];
            matrix[aRow][aCol + i] = matrix[bRow - i][aCol];
            matrix[bRow - i][aCol] = matrix[bRow][bCol - i];
            matrix[bRow][bCol - i] = matrix[aRow + i][bCol];
            matrix[aRow + i][bCol] = temp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        printMatrix(matrix);


        System.out.println("=========");
        rightRotateMatrix(matrix);
        printMatrix(matrix);

        System.out.println("=========");
        leftRotateMatrix(matrix);
        printMatrix(matrix);
    }



}
