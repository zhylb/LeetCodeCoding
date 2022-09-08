package com.lihd.top;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 21:14
 */
public class Code037SudokuSolver {
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] ceil = new boolean[9][9];
        init(board, row, col, ceil);
        fill(board, 0, 0, row, col, ceil);
    }


    private boolean fill(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] ceil) {
        if (i == board.length) {
            return true;
        }
        // i, j位置是合法位置
        int nextI = j == board[0].length - 1 ? i + 1 : i;
        int nextJ = j == board[0].length - 1 ? 0 : j + 1;

        // 当前位置是 . 一定要填了
        if (board[i][j] == '.') {
            for (int k = 1; k <= 9; k++) {
                if (!row[i][k - 1] && !col[j][k - 1] && !ceil[i / 3 * 3 + j / 3][k - 1]) {
                    row[i][k - 1] = true;
                    col[j][k - 1] = true;
                    ceil[i / 3 * 3 + j / 3][k - 1] = true;
                    board[i][j] = (char) (k + '0');
                    if (fill(board, nextI, nextJ, row, col, ceil)) {
                        return true;
                    }
                    row[i][k - 1] = false;
                    col[j][k - 1] = false;
                    ceil[i / 3 * 3 + j / 3][k - 1] = false;
                    board[i][j] = '.';
                }
            }
            return false;
        }
        return fill(board, nextI, nextJ, row, col, ceil);

    }


    private void init(char[][] board, boolean[][] row, boolean[][] col, boolean[][] ceil) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    ch = (char) (ch - '1');
                    int ceilIndex = (i / 3) * 3 + j / 3;
                    row[i][ch] = true;
                    col[j][ch] = true;
                    ceil[ceilIndex][ch] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] m =
                {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        Code037SudokuSolver solver = new Code037SudokuSolver();
        solver.solveSudoku(m);
        System.out.println(Arrays.deepToString(m));
    }
}
