package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 18:46
 */
public class Code036ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] ceil = new boolean[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    ch = (char) (ch - '1');
                    int ceilIndex = (i / 3) * 3 + j / 3;
                    if (!row[i][ch] && !col[j][ch] && !ceil[ceilIndex][ch]){
                        row[i][ch] = true;
                        col[j][ch] = true;
                        ceil[ceilIndex][ch] = true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }


}
