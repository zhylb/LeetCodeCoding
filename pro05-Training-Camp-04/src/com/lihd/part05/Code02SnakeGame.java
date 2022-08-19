package com.lihd.part05;

import ans.class05.Code02_SnakeGame;
import com.lihd.utils.ArrayUtils;
import static org.junit.Assert.*;

import java.lang.reflect.Array;

/**
 * 代码通过断言验证通过
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/13 19:48
 */
public class Code02SnakeGame {


    public static int maxLenForce(int[][] map) {
        int R = map.length;
        int C = map[0].length;
        int ans = 0;
        //一定要枚举每个位置
        //因为递归的含义就是 在那个位置结束的含义
        //蛇蛇的长度最的位置 可能是任意位置,因此务必要枚举
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int[] ints = maxLenForce(map, i, j);
                ans = Math.max(ans, Math.max(ints[0], ints[1]));
            }
        }
        return ans;
    }

    /**
     * 从 第一列某个位置 出发, 必须到达 row, col 这里的row, col是终点
     * 一定不要认为是从 row,col出发到 终点的最好情况
     * @param map 地图
	 * @param row 目标行
	 * @param col 目标列
     * @return int[] 只有两个值,第一个代表没使用, 第二个代表之前使用过
     * @author lihd
     * @date 2022/8/13 20:27
     */
    public static int[] maxLenForce(int[][] map, int row, int col) {
        if (col == 0) {
            //刚开始 第一个位置
            return new int[]{map[row][col], -map[row][col]};
        }
        int R = map.length;
        int C = map[0].length;
        int[] before = maxLenForce(map, row, col - 1);
        int unused = before[0];
        int used = before[1];
        if (row > 0) {
            before = maxLenForce(map, row - 1, col - 1);
            unused = Math.max(unused, before[0]);
            used = Math.max(used, before[1]);
        }
        if (row + 1 < R) {
            before = maxLenForce(map, row + 1, col - 1);
            unused = Math.max(unused, before[0]);
            used = Math.max(used, before[1]);
        }

        //至此 unused 和 used的最大值更新正确

        int no = -1;
        int yes = -1;
        if (unused >= 0) {
            //有讨论的必要
            no = map[row][col] + unused;
            yes = - map[row][col] + unused;
        }
        if (used >= 0) {
            //有讨论的必要
            yes = Math.max(yes, map[row][col] + used);
        }
        return new int[]{no, yes};
    }
    //修改成动态规划
    public static int maxLenDp(int[][] map) {
        int R = map.length;
        int C = map[0].length;
        int[][] noDp = new int[R][C];
        int[][] yesDp = new int[R][C];
        for (int row = 0; row < R; row++) {
            noDp[row][0] = map[row][0];
            yesDp[row][0] = -map[row][0];
        }
        for (int col = 1; col < C; col++) {
            for (int row = 0; row < R; row++) {

                int unused = noDp[row][col - 1];
                int used = yesDp[row][col - 1];
                if (row > 0) {
                    unused = Math.max(unused, noDp[row - 1][col - 1]);
                    used = Math.max(used, yesDp[row - 1][col - 1]);
                }
                if (row + 1 < R) {
                    unused = Math.max(unused, noDp[row + 1][col - 1]);
                    used = Math.max(used, yesDp[row + 1][col - 1]);
                }
                int no = -1;
                int yes = -1;
                if (unused >= 0) {
                    //有讨论的必要
                    no = map[row][col] + unused;
                    yes = - map[row][col] + unused;
                }
                if (used >= 0) {
                    //有讨论的必要
                    yes = Math.max(yes, map[row][col] + used);
                }
                noDp[row][col] = no;
                yesDp[row][col] = yes;
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans = Math.max(ans, Math.max(noDp[i][j], yesDp[i][j]));
            }
        }
        return ans;
    }

    public static void main(String[] args) {


        int testTimes = 10000;
        int rb = 5;
        int re = 10;
        int numB = -20;
        int numE = 40;

        for (int i = 0; i < testTimes; i++) {
            int[][] map = ArrayUtils.randomNotNullMatrix(rb, re, rb, re, numB, numE);
            int ansMe = maxLenDp(map);
            int ansTea = Code02_SnakeGame.walk2(map);
            assertEquals(ansMe, ansTea);
        }



    }






}
