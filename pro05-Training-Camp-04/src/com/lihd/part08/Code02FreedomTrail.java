package com.lihd.part08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/18 16:43
 */
public class Code02FreedomTrail {


    public static int findRotateSteps(String ring, String key) {
        HashMap<Character, List<Integer>> map = new HashMap<>();
        char[] chs = ring.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (map.containsKey(chs[i])) {
                map.get(chs[i]).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(chs[i], list);
            }
        }
        int[][] dp = new int[key.length()][ring.length()];
        return dfsDp(0, key.toCharArray(), 0, chs.length, map, dp);
    }

    public static int dfs(int ki, char[] key, int ri, int rLen, HashMap<Character, List<Integer>> map) {
        if (ki == key.length) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (Integer k : map.get(key[ki])) {
            int val = dial(k, ri, rLen) + 1 + dfs(ki + 1, key, k, rLen, map);
            ans = Math.min(ans, val);
        }
        return ans;

    }

    public static int dfsDp(int ki, char[] key, int ri, int rLen, HashMap<Character, List<Integer>> map, int[][] dp) {
        if (ki == key.length) {
            return 0;
        }

        if (dp[ki][ri] != 0) {
            return dp[ki][ri];
        }

        int ans = Integer.MAX_VALUE;
        for (Integer k : map.get(key[ki])) {
            int val = dial(k, ri, rLen) + 1 + dfsDp(ki + 1, key, k, rLen, map, dp);
            ans = Math.min(ans, val);
        }
        dp[ki][ri] = ans;
        return ans;

    }


    private static int dial(int from, int to, int len) {
        //从小到大顺着转
        int mov = Math.abs(from - to);
        return Math.min(mov, len - mov);
    }

}
