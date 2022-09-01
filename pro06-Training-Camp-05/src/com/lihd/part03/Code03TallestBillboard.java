package com.lihd.part03;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/24 22:41
 */
public class Code03TallestBillboard {
    /**
     * <a href="https://leetcode.com/problems/tallest-billboard/">链接</a>
     * 120 ms, faster than 30.59%
     * 43.5 MB, less than 91.76%
     * @param rods 木棍数组
     * @return int
     * @author lihd
     * @date 2022/8/24 23:13
     */
    public static int tallestBillboard(int[] rods) {
        // key表示高度差, value表示高度差相等的许多木棍组合中, 短木棍长度最大的 !!!
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int rod : rods) {
            HashMap<Integer, Integer> cur = new HashMap<>(map);
            int key;
            int val;
            for (Map.Entry<Integer, Integer> entry : cur.entrySet()) {
                // 两个木棍的高度差
                int dis = entry.getKey();
                // 短的木棍的长度
                int s = entry.getValue();
                // 长的木棍的长度
                int l = s + dis;
                // 每个新来的木棍有两种抉择

                // 放到短的上面
                // 当前木棍的高度差 abs(短的木棍 + 当前木棍 - 长的木棍)
                key = Math.abs(s + rod - l);
                // 当前木棍组和 中较小的那个 min(短的木棍 + 当前木棍 , 长的木棍)
                val = Math.min(s + rod, l);
                // 只有map中没有这个高度 或者这个高度比map中存着的高度高的时候才更新
                if (!map.containsKey(key) || val > map.get(key)) {
                    map.put(key, val);
                }

                // 放到长的上面 高度差变成 高度差 + 当前木棍
                key = dis + rod;
                // 短的木棍肯定还是短的
                val = s;
                if (!map.containsKey(key) || val > map.get(key)) {
                    map.put(key, val);
                }
            }
        }
        // 返回高度差为0的木棍组合即可
        return map.get(0);
    }

}
