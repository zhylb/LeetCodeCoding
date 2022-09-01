package com.lihd.part03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/bricks-falling-when-hit/submissions/">leetcode链接</a>
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/24 21:25
 */
public class Code02BricksFallingWhenHit {

    /**
     *
     * <a href="https://leetcode.com/problems/bricks-falling-when-hit/submissions/">leetcode链接</a>
     * 175 ms, faster than 7.41%
     * 53.1 MB, less than 99.66%
     * @param grid
     * @param hits
     * @return int[]
     * @author lihd
     * @date 2022/8/24 22:39
     */
    public static int[] hitBricks(int[][] grid, int[][] hits) {
        // 首先 被炮击的位置改成 2
        for (int[] hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) {
                grid[hit[0]][hit[1]] = 2;
            }
        }
        UnionFind unionFind = new UnionFind(grid);
        int[] ans = new int[hits.length];
        for (int i = hits.length - 1;  i >= 0; i--) {
            if (grid[hits[i][0]][hits[i][1]] == 2) {
                ans[i] = unionFind.calc(hits[i][0], hits[i][1]);
            }
        }
        return ans;
    }


    private static class UnionFind{


        private int[][] grid;
        private Dot[][] dots;
        int ceilingOne;
        int R;
        int C;
        HashSet<Dot> ceilingSet = new HashSet<>();
        HashMap<Dot, Dot> fatherMap = new HashMap<>();
        HashMap<Dot, Integer> sizeMap = new HashMap<>();

        public UnionFind(int[][] grid) {
            init(grid);
            connect();
        }

        private void init(int[][] grid) {
            this.grid = grid;
            R = grid.length;
            C = grid[0].length;
            dots = new Dot[R][C];
            ceilingOne = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (grid[i][j] == 1) {
                        dots[i][j] = new Dot();
                        fatherMap.put(dots[i][j], dots[i][j]);
                        sizeMap.put(dots[i][j], 1);
                        if (i == 0) {
                            ceilingSet.add(dots[i][j]);
                            ceilingOne++;
                        }
                    }
                }
            }
        }

        private void connect() {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    union(i,j, i + 1, j);
                    union(i,j, i - 1, j);
                    union(i,j, i, j + 1);
                    union(i,j, i, j - 1);
                }
            }
        }

        private Dot findFather(int r1, int c1) {
            Dot dot = dots[r1][c1];

            Stack<Dot> stack = new Stack<>();
            while (dot != fatherMap.get(dot)) {
                stack.push(dot);
                dot = fatherMap.get(dot);
            }
            // 现在dot就是父节点
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), dot);
            }
            return dot;
        }

        public int calc(int r, int c) {
            int beforeCeilingOne = ceilingOne;
            grid[r][c] = 1;
            dots[r][c] = new Dot();
            sizeMap.put(dots[r][c], 1);
            fatherMap.put(dots[r][c], dots[r][c]);
            if (r == 0) {
                ceilingSet.add(dots[r][c]);
                ceilingOne++;
            }
            union(r,c, r + 1, c);
            union(r,c, r - 1, c);
            union(r,c, r, c + 1);
            union(r,c, r, c - 1);

            int afterCeilingOne = ceilingOne;
            return afterCeilingOne == beforeCeilingOne ? 0 : afterCeilingOne - beforeCeilingOne - 1;
        }

        public void union(int r1, int c1, int r2, int c2) {
            if (!isValid(r1, c1) || !isValid(r2, c2)) {
                return;
            }
            // 全部有效
            Dot father1 = findFather(r1, c1);
            Dot father2 = findFather(r2, c2);
            if (father1 == father2) {
                return;
            }
            int size1 = sizeMap.get(father1);
            int size2 = sizeMap.get(father2);
            Dot large = size1 >= size2 ? father1 : father2;
            Dot small = father1 == large ? father2 : father1;
            boolean status1 = ceilingSet.contains(father1);
            boolean status2 = ceilingSet.contains(father2);
            fatherMap.put(small, large);
            sizeMap.put(large, size1 + size2);
            sizeMap.remove(small);

            if (status1 ^ status2) {
                ceilingOne += status1 ? size2 : size1;

                ceilingSet.add(large);
                ceilingSet.remove(small);
            }


        }
        private boolean isValid(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c< C && grid[r][c] == 1;
        }
        private static class Dot{}
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 1}, {1, 1, 1}};
        int[][] hits = {{0, 0}, {0, 2}, {1, 1}};
        int[] ans = hitBricks(grid, hits);
        System.out.println(Arrays.toString(ans));
    }


}
