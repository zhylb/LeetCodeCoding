package com.lihd.part03;

/**
 * <a href="https://leetcode.com/problems/number-of-islands/submissions/">https://leetcode.com/problems/number-of-islands/submissions/</a>
 * Runtime: 4 ms, faster than 68.49% of Java online submissions for Number of Islands.
 * Memory Usage: 57.7 MB, less than 28.48% of Java online submissions for Number of Islands.
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/4 20:58
 */
public class Code01Islands {
    public static int numIslands(char[][] grid) {
        int nums = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    nums ++;
                    infect(grid, i, j);
                }
            }
        }
        return nums;
    }


    public static void infect(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return ;
        }
        if (grid[row][col] == '1') {
            grid[row][col] = '2';
            infect(grid, row, col + 1);
            infect(grid, row, col - 1);
            infect(grid, row + 1, col);
            infect(grid, row - 1, col);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        int i = numIslands(grid);
        System.out.println(i);
    }

}
