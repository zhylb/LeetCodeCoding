package com.lihd.part02;

import java.util.Random;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/10 22:23
 */
public class Code06CherryPickup {


    public static int all;
    public static int hit;

    /**
     * 暴力递归 调度函数
     * @param map 地图
     * @return int
     * @author lihd
     * @date 2022/8/10 23:15
     */
    public static int maxPickUpForce(int[][] map) {
        return maxPickUpForce(map, 0, 0, 0);
    }

    /**
     * 暴力递归
     * @param map 地图
	 * @param aR 人物a当前来到的 row
	 * @param aC 人物a当前来到的 col
	 * @param bR 人物b当前来到的 row
     * @return int
     * @author lihd
     * @date 2022/8/10 23:15
     */
    public static int maxPickUpForce(int[][] map, int aR, int aC, int bR) {
        int R = map.length;
        int C = map[0].length;
        int bC = aR + aC - bR;
        if (aR >= R || aC >= C || bR >= R || bC >= C) {
            //检验位置是否有效，无效返回最小值。
            return 0;
        }
        if (aR == R - 1 && aC == C - 1) {
            //说明两个点都来到了最后的位置
            return map[aR][aC];
        }
        int ans = 0;
        if (aR == bR) {
            //代表着两点重合。
            ans += map[aR][aC];
        } else {
            //两个点不重合
            ans += map[aR][aC] + map[bR][bC];
        }
        //所有可能的情况
        //p1 : a 向左 ，b 向左
        //p2 : a 向左 ，b 向下
        //p3 : a 向下 ，b 向左
        //p4 : a 向下 ，b 向下
        int p1 = maxPickUpForce(map, aR + 1, aC, bR);
        int p2 = maxPickUpForce(map, aR + 1, aC, bR + 1);
        int p3 = maxPickUpForce(map, aR, aC + 1, bR);
        int p4 = maxPickUpForce(map, aR, aC + 1, bR + 1);
        int best = Math.max(Math.max(p1, p2), Math.max(p3, p4));
        return ans + best;
    }

    public static int maxPickUpMemory(int[][] map) {
        int[][][] dp = new int[map.length][map[0].length][map.length];
        return maxPickUpMemory(map, 0, 0, 0, dp);
    }
    public static int maxPickUpMemory(int[][] map, int aR, int aC, int bR, int[][][] dp) {
        int R = map.length;
        int C = map[0].length;
        int bC = aR + aC - bR;
        if (aR >= R || aC >= C || bR >= R || bC >= C) {
            //检验位置是否有效，无效返回最小值。
            return 0;
        }
        all++;
        //先去缓存中查看
        if (dp[aR][aC][bR] != 0) {
            hit++;
            return dp[aR][aC][bR];
        }

        if (aR == R - 1 && aC == C - 1 ) {
            //说明两个点都来到了最后的位置
            dp[aR][aC][bR] = map[aR][aC];
            return map[aR][aC];
        }
        int ans = 0;
        if (aR == bR) {
            //代表着两点重合。
            ans += map[aR][aC];
        } else {
            //两个点不重合
            ans += map[aR][aC] + map[bR][bC];
        }
        //所有可能的情况
        //p1 : a 向左 ，b 向左
        //p2 : a 向左 ，b 向下
        //p3 : a 向下 ，b 向左
        //p4 : a 向下 ，b 向下
        int p1 = maxPickUpMemory(map, aR + 1, aC, bR, dp);
        int p2 = maxPickUpMemory(map, aR + 1, aC, bR + 1, dp);
        int p3 = maxPickUpMemory(map, aR, aC + 1, bR, dp);
        int p4 = maxPickUpMemory(map, aR, aC + 1, bR + 1, dp);
        int best = Math.max(Math.max(p1, p2), Math.max(p3, p4));
        dp[aR][aC][bR] = ans + best;
        return ans + best;
    }


    public static int maxPickUpDp(int[][] map) {
        int R = map.length;
        int C = map[0].length;

        int[][][] dp = new int[R + 1][C + 1][R + 1];
        dp[R - 1][C - 1][R - 1] = map[R - 1][C - 1];

        for (int aR = R - 1; aR >= 0; aR--) {
            for (int aC = C - 1; aC >= 0; aC--) {
                for (int bR = R - 1; bR >= 0; bR--) {
                    int bC = aR + aC - bR;
                    if (bC >= C || bC < 0) {
                        continue;
                    }
                    int p1 = dp[aR + 1][aC][bR];
                    int p2 = dp[aR + 1][aC][bR + 1];
                    int p3 = dp[aR][aC + 1][bR];
                    int p4 = dp[aR][aC + 1][bR + 1];
                    int best = Math.max(Math.max(p1, p2), Math.max(p3, p4));

                    int ans = 0;
                    if (aR == bR) {
                        //代表着两点重合。
                        ans += map[aR][aC];
                    } else {
                        //两个点不重合
                        ans += map[aR][aC] + map[bR][bC];
                    }
                    dp[aR][aC][bR] = ans + best;
                }
            }
        }
        return dp[0][0][0];

    }

    public static int[][] randomMap(int m, int n) {
        Random random= new Random();
        int r = random.nextInt(m) + 3;
        int c = random.nextInt(n) + 3;
        int[][] map = new int[r][c];
        for (int[] one : map) {
            for (int i = 0; i < one.length; i++) {
                one[i] = random.nextInt(10);
            }
        }
        return map;
    }

    public static void main(String[] args) {
//        int[][] map = {
//                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
//                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1}};
        int[][] map = randomMap(10, 10);
        int maxPickUpForce = maxPickUpForce(map);
        int maxPickUpMemory = maxPickUpMemory(map);
        int maxPickUpDp = maxPickUpDp(map);
        System.out.println("maxPickUpForce = " + maxPickUpForce);
        System.out.println("maxPickUpMemory = " + maxPickUpMemory);
        System.out.println("maxPickUpDp = " + maxPickUpDp);
        System.out.println("hit = " + hit);
        System.out.println("all = " + all);
    }


}
