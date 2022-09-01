package com.lihd.part07;


/**
 * 下面的代码通过了 左程云老师的 对数器, 每种方法都通过了
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/26 18:53
 */
public class Code01TSP {

    public static int tspForce(int[][] map) {
        int n = map.length;
        boolean[] visited = new boolean[n];
        return tspForce(visited, 0, map);
    }

    private static int tspForce(boolean[] visited, int visit, int[][] map) {
        int unVisitedCount = 0;
        for (boolean b : visited) {
            unVisitedCount += b ? 0 : 1;
        }
        if (unVisitedCount == 1) {
            // 说明还有最后一座城市没有访问
            return map[visit][0];
        }
        //这个城市被访问了
        visited[visit] = true;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] ) {
                // 没有访问过
                int dis = map[visit][i] + tspForce(visited, i, map);
                ans = Math.min(ans, dis);
            }
        }
        visited[visit] = false;
        return ans;
    }

    public static int tspBit(int[][] map) {
        return tspBit(0, 0, map);
    }

    private static int tspBit(int visited, int visit, int[][] map) {
        int unVisitedCount = 0;
        int val = (visited + 1) | visited;
        if (val == ((1 << map.length) - 1)) {
            // 说明只有 1 个 0
            return map[visit][0];
        }
        // 这个城市被访问了, 二进制位改成1
        visited |= 1 << visit;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < map.length; i++) {
            // 说明这个位置是0
            if ((visited & (1 << i)) == 0) {
                // 没有访问过 也就是二进制上的位是 0
                int dis = map[visit][i] + tspBit(visited, i, map);
                ans = Math.min(ans, dis);
            }
        }
        return ans;
    }

    public static int tspDp(int[][] map) {
        int n = map.length;
        int[][] dp = new int[1 << n][n];
        for (int visited = (1 << n) - 1; visited >= 0; visited--) {
            for (int visit = 0; visit < n; visit++) {
                // visited 的 visited 位上一定要是0
                if ((visited & (1 << visit)) == 0) {

                    int val = (visited + 1) | visited;
                    if (val == ((1 << map.length) - 1)) {
                        // 说明只有 1 个 0
                        dp[visited][visit] =  map[visit][0];
                    } else {
                        // 这个城市被访问了, 二进制位改成1
                        int v = visited;

                        v |= 1 << visit;
                        int ans = Integer.MAX_VALUE;
                        for (int i = 0; i < map.length; i++) {
                            // 说明这个位置是0
                            if ((v & (1 << i)) == 0) {
                                // 没有访问过 也就是二进制上的位是 0
                                int dis = map[visit][i] + dp[v][i];
                                ans = Math.min(ans, dis);
                            }
                        }
                        dp[visited][visit] = ans;
                    }
                }
            }
        }
        return dp[0][0];
    }

    public static int tspMemory(int[][] map) {
        int[][] memory = new int[1 << map.length][map.length];
        return tspMemory(0, 0, map, memory);
    }

    private static int tspMemory(int visited, int visit, int[][] map, int[][] memory) {
        if (memory[visited][visit] != 0) {
            return memory[visited][visit];
        }
        int unVisitedCount = 0;
        int val = (visited + 1) | visited;
        if (val == ((1 << map.length) - 1)) {
            // 说明只有 1 个 0
            memory[visited][visit] = map[visit][0];
            return map[visit][0];
        }
        // 这个城市被访问了, 二进制位改成1
        visited |= 1 << visit;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < map.length; i++) {
            // 说明这个位置是0
            if ((visited & (1 << i)) == 0) {
                // 没有访问过 也就是二进制上的位是 0
                int dis = map[visit][i] + tspMemory(visited, i, map, memory);
                ans = Math.min(ans, dis);
            }
        }
        memory[visited][visit] = ans;
        return ans;
    }








}
