package com.lihd.part01;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/2 14:44
 */
public class Code04ZigZagPrintMatrix {


    public static void zigZagPrint(int[][] matrix) {

        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        int aRow = 0;
        int aCol = 0;
        int bRow = 0;
        int bCol = 0;
        boolean isDown = false;
        while (aRow <= row && bCol <= col) {
            linePrint(matrix, aRow, aCol, bRow, bCol, isDown);
            aRow = aCol == col ? aRow + 1 : aRow;
            aCol = aCol == col ? aCol : aCol + 1;
            bCol = bRow == row ? bCol + 1 : bCol;
            bRow = bRow == row ? bRow : bRow + 1;
            isDown = !isDown;

        }
        System.out.println();

    }
    /**
     *
     * @param matrix 传入的数组
	 * @param aRow 左上角的所在行
	 * @param aCol 左上角的所在列
	 * @param bRow 右下角的所在行
	 * @param bCol 右下角的所在列
     * @param isDown 打印的方向
     * @author lihd
     * @creed: 春春的飞舞
     * @date 2022/6/2 14:49
     */
    public static void linePrint(int[][] matrix, int aRow, int aCol, int bRow, int bCol, boolean isDown) {
        if (isDown) {
            while (aRow <= bRow) {
                System.out.print(matrix[aRow++][aCol--] + " ");
            }
        } else {
            while (bCol <= aCol) {//aCol <= bCol
                System.out.print(matrix[bRow--][bCol++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        zigZagPrint(matrix);
    }

}
