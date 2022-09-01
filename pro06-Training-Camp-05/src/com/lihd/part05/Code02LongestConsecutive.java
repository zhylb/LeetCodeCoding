package com.lihd.part05;

import java.util.HashMap;
import java.util.Map;

/**
 * 这个东西是自己写的, 不过写的过程有点费劲
 * 不过好在写出来了 真不错
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/25 20:27
 */
public class Code02LongestConsecutive {

    /**
     * 懂下面的方法的话, 这个方法很容易理解
     * 千万不要删除 冗余数据, 这是重要的过滤条件
     * 46 ms, faster than 71.40%
     * 60 MB, less than 90.93%
     * @param nums
     * @return int
     * @author lihd
     * @date 2022/8/25 21:59
     */
    public static int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            map.put(num, 1);
            if (map.containsKey(num - 1)) {
                int len = map.get(num - 1);
                int begin = num - 1 - len + 1;
                int myLen = 1;
                map.put(begin, len + myLen);
                map.put(num, len + myLen);
            }

            if (map.containsKey(num + 1)) {
                int len = map.get(num + 1);
                int end = num + 1 + len - 1;
                int myLen = map.get(num);
                map.put(end, myLen + len);
                map.put(num - myLen + 1, myLen + len);
            }
        }


        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans = Math.max(entry.getValue(), ans);
        }

        return ans;
    }

    /**
     *  69 ms, faster than 51.98%
     *  62.3 MB, less than 86.49%
     * @param nums
     * @return int
     * @author lihd
     * @date 2022/8/25 21:45
     */
    public static int longestConsecutive1(int[] nums) {
        HashMap<Integer, Integer> headMap = new HashMap<>();
        HashMap<Integer, Integer> tailMap = new HashMap<>();

        for (int num : nums) {
            if (headMap.containsKey(num) || tailMap.containsKey(num)) {
                continue;
            }
            headMap.put(num, 1);
            tailMap.put(num, 1);
            if (tailMap.containsKey(num - 1)) {
                // 之前的总长度
                int len = tailMap.get(num - 1);
                // 之前的开始位置 (比如 3,3 可知 开始位置是 4 - 1 - 3 + 1
                int begin = (num - 1) - len + 1;
                // 我的长度
                int myLen = 1;
                // 我的长度变化
                tailMap.put(num, len + myLen);
                // 之前的开始位置 变化
                headMap.put(begin, len + myLen);

                //删除两个没用的
                tailMap.remove(num - 1);
                headMap.remove(num);

            }

            if (headMap.containsKey(num + 1)) {
                // 后面的总长度
                int len = headMap.get(num + 1);
                // 后面的结束位置
                int end = num + 1 + len - 1;
                // 我的长度
                int myLen = tailMap.get(num);
                // 后面结束位置的长度变化
                tailMap.put(end, len + myLen);
                // 我之前的长度变化
                headMap.put(num - myLen + 1, len + myLen);
                // 删除两个没用的
                headMap.remove(num + 1);
                tailMap.remove(num);
            }
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : headMap.entrySet()) {
            ans = Math.max(entry.getValue(), ans);
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] arr = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutive(arr));
    }

}
