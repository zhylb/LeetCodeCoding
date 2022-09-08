package com.lihd.top;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/1 12:16
 */
public class Code003LongestSubstringWithoutRepeatingCharacters {


    /**
     * 枚举必须以i位置作为结尾的情况, 子串,子序列常见枚举方法 <br/>
     * 来到 i 位置, 限制其 长度有两个值 <br/>
     * 1 是上一次出现 s[i] 的情况 <br/>
     * 2 是i - 1 的边界 <br/>
     * i位置的边界就是这两个边界的最大值, 也就是最靠近i的边界 <br/>
     * 由于边界代表第一个不能到达的值, 因此 i - 边界 就是i位置结尾的最长长度, 不用 + 1 <br/>
     * 4 ms, faster than 97.45%, 43.8 MB, less than 81.16% <br/>
     * @param s 字符串
     * @return int
     * @author lihd
     * @date 2022/9/1 12:21
     */
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        Arrays.fill(map, -1);
        int pre = -1;
        int ans = 0;
        // pre代表的边界是上一次的边界, map中的边界是 当前位置不要重复字符的边界
        for (int i = 0; i < s.length(); i++) {
            pre = Math.max(pre, map[s.charAt(i)]);
            // i - pre 是现在位置 的答案
            ans = Math.max(ans, i - pre);
            map[s.charAt(i)] = i;
        }
        return ans;
    }
}
