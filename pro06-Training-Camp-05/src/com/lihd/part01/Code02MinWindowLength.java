package com.lihd.part01;

import java.util.Stack;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/20 22:14
 */
public class Code02MinWindowLength {

    //返回
    public static int minLen(String s1, String s2) {
        int[] map = new int[26];
        int all = s2.length();
        for (int i = 0; i < s2.length(); i++) {
            map[s2.charAt(i) - 'a']++;
        }
        //开始创建滑动窗口
        int l = 0;
        int r = 0;
        int ans = Integer.MAX_VALUE;
        while (r < s1.length()) {
            map[s1.charAt(r) - 'a']--;
            if (map[s1.charAt(r) - 'a'] >= 0) {
                //说明是有效还款
                all--;
            }
            if (all == 0) {
                //l 可以移动了
                while (map[s1.charAt(l) - 'a'] < 0) {
                    map[s1.charAt(l++) - 'a']++;
                }
                //此时 还没有 map[?] = 1, l来到第一个不满足的位置
                all = 1;
                ans = Math.min(ans, r - l + 1);
                map[s1.charAt(l++) - 'a'] = 1;
            }
            r++;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        String str1 = "adabbca";
        String str2 = "acb";
        System.out.println(minLen(str1, str2));
    }




}
