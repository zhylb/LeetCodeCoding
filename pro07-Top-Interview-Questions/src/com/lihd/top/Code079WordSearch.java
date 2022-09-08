package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/6 16:54
 */
public class Code079WordSearch {
    /**
     * 一个dfs即可, 注意现场控制与现场恢复<br/>
     * <b>这个题目不能改成动态规划, 因为board是变化的</b><br/>
     * 122 ms, faster than 86.56% , 41.9 MB, less than 76.72%<br/>
     * @param board 棋盘
	 * @param word 要找的单词
     * @return boolean
     * @author lihd
     * @date 2022/9/6 17:13
     */
    public static boolean exist(char[][] board, String word) {
        char[] chs = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, 0, chs)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, int row, int col, int index, char[] chs) {

        if (board[row][col] != chs[index]) {
            return false;
        }
        // 最后一个字符匹配成功, 返回真
        if (index == chs.length - 1) {
            return true;
        }
        // 当前位置匹配成功
        char temp = board[row][col];
        // 记录现场
        board[row][col] = 0;
        boolean ans = false;
        if (row + 1 < board.length && board[row + 1][col] != 0) {
            ans = dfs(board, row + 1, col, index + 1, chs);
        }
        if (row - 1 >= 0 && board[row - 1][col] != 0) {
            ans = ans || dfs(board, row - 1, col, index + 1, chs);
        }
        if (col + 1 < board[0].length && board[row][col + 1] != 0) {
            ans = ans || dfs(board, row, col + 1, index + 1, chs);
        }
        if (col - 1 >= 0 && board[row][col - 1] != 0) {
            ans = ans || dfs(board, row, col - 1, index + 1, chs);
        }
        // 恢复现场 一定要保证现场恢复前, 不要有任何一个分支return, 都是泪啊
        board[row][col] = temp;
        return ans;
    }

    public static void main(String[] args) {
        char[][] board = {{'a'}};
        boolean exist = exist(board, "a");
        System.out.println(exist);

    }
}
