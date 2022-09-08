package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/5 21:07
 */
public class Code073SetMatrixZeroes {

    /**
     * 自己想的, 还是很有意思的 <br/>
     * 就是不要使用额外空间, 想法是利用第一行 <br/>
     * 假设第一行没有0, 我们把其他位置涂成0时候, 行全部涂成0, 列的话只把第一行的值涂成0<br/>
     * 处理完 所有0后, 遍历第一行, 把列为0对应的列 全部涂成0<br/>
     * 至于第一行有没有0, 使用一个变量记住即可<br/>
     * 1 ms, faster than 97.67%, 44 MB, less than 91.40%
     * @param matrix 矩阵
     * @author lihd
     * @date 2022/9/5 21:54
     */
    public void setZeroes(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;

        // 先看看第一行是否有0
        boolean zeroRowContains0 = false;
        for (int j = 0; j < C; j++) {
            if (matrix[0][j] == 0) {
                zeroRowContains0 = true;
                break;
            }
        }

        // 从第二行开始,填完全部
        // fillRow方法会把第一行改变
        // 这样, 所有要变成0的列全部记录在第一行
        for (int i = 1; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    fillRow(i, C, matrix);
                    break;
                }
            }
        }

        // 把所有列的值改为1
        for (int j = 0; j < C; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < R; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 如果第一行有0, 把所有值改成0
        if(zeroRowContains0){
            for (int j = 0; j < C; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    // 把这一行全都变成0, 本来是0的, 把这一列的 第一行设置为0 (map[0][j] = 0)
    private void fillRow(int row, int C, int[][] map) {
        for (int j = 0; j < C; j++) {
            if (map[row][j] == 0) {
                map[0][j] = 0;
            }
            map[row][j] = 0;
        }
    }


}
