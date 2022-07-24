package com.lihd.part01;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 10:19
 */
public class Code05PrintMatrixSpiralOrder {


    public static void printMatrixSpiralOrder(int[][] matrix) {
        int aRow = 0;
        int aCol = 0;
        int bRow = matrix.length - 1;
        int bCol = matrix[0].length - 1;

        while (aRow <= bRow && aCol <= bCol) {
            printOuterLayer(matrix, aRow, aCol, bRow, bCol);
            aRow ++;
            aCol ++;
            bRow --;
            bCol --;
        }


    }


    public static void printOuterLayer(int[][] matrix, int aRow, int aCol, int bRow, int bCol) {
        if (aRow == bRow) {
            for (int i = aCol; i <= bCol; i++) {
                System.out.print(matrix[aRow][i] + " ");
            }
        } else if (aCol == bCol) {
            for (int i = aRow; i <= bRow; i++) {
                System.out.print(matrix[i][aCol] + " ");
            }
        } else {
            //确认是以一个环状
            for (int i = aCol; i < bCol; i++) {
                System.out.print(matrix[aRow][i] + " ");
            }

            for (int i = aRow; i < bRow; i++) {
                System.out.print(matrix[i][bCol] + " ");
            }

            for (int i = bCol; i > aCol ; i--) {
                System.out.print(matrix[bRow][i] + " ");
            }

            for (int i = bRow; i > aRow; i--) {
                System.out.print(matrix[i][aCol] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printMatrixSpiralOrder(matrix);
    }

}
