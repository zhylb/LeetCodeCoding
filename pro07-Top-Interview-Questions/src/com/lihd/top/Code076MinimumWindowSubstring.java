package com.lihd.top;

import java.util.HashMap;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/5 22:13
 */
public class Code076MinimumWindowSubstring {

    /**
     * 再做一个小优化 <br/>
     * 6 ms, faster than 88.64%, 44.1 MB, less than 68.61%<br/>
     * @param s
	 * @param t
     * @return java.lang.String
     * @author lihd
     * @date 2022/9/8 22:59
     */
    public String minWindow(String s, String t) {

        int[] map = new int[128];
        int all = t.length();
        // 记录好欠账表
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
        }

        // [l, r) 为当前的窗口
        int l = 0;
        int r = 0;
        int ansL = -1;
        int ansR = -1;
        while (r < s.length()) {
            // 先减
            map[s.charAt(r)]--;
            if (map[s.charAt(r)] >= 0) {
                // 说明是有效还款
                all --;
            }
            if (all == 0) {
                while (map[s.charAt(l)] < 0) {
                    map[s.charAt(l++)]++;
                }
                // 现在 来到最小合法开头
                // [l,r] 就是以l开头的最小区间
                if (ansL == -1 || (r - l < ansR - ansL)) {
                    ansR = r;
                    ansL = l;
                }
                all = 1;
                map[s.charAt(l++)]++;
            }
            r ++;
        }
        if (ansL == -1) {
            return "";
        }
        return s.substring(ansL, ansR + 1);
    }



    /*
     *                  ___====-_  _-====___
     *            _--^^^#####//      \\#####^^^--_
     *         _-^##########// (    ) \\##########^-_
     *        -############//  |\^^/|  \\############-
     *      _/############//   (@::@)   \\############\_
     *     /#############((     \\//     ))#############\
     *    -###############\\    (oo)    //###############-
     *   -#################\\  / VV \  //#################-
     *  -###################\\/      \//###################-
     * _#/|##########/\######(   /\   )######/\##########|\#_
     * |/ |#/\#/\#/\/  \#/\##\  |  |  /##/\#/  \/\#/\#/\#| \|
     * `  |/  V  V  `   V  \#\| |  | |/#/  V   '  V  V  \|  '
     *    `   `  `      `   / | |  | | \   '      '  '   '
     *                     (  | |  | |  )
     *                    __\ | |  | | /__
     *                   (vvv(VVV)(VVV)vvv)
     *                      Talk is cheap
     *                    Show me the code
     */

    /**
     * 难啊, 太难了, 学过的东西写不出来 <br/>
     * 5 ms, faster than 91.67%, 44.6 MB, less than 55.23%<br/>
     * @param s
	 * @param t
     * @return java.lang.String
     * @author lihd
     * @date 2022/9/5 23:19
     */
    public String minWindow1(String s, String t) {
        int[] map = new int[128];
        int all = 0;
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
            all++;
        }
        int l = 0;
        int r = 0;
        int len = Integer.MAX_VALUE;
        String ans = "";
        while (r < s.length()) {
            map[s.charAt(r)]--;
            if (map[s.charAt(r)] >= 0) {
                // 有效
                all--;
            }
            if (all == 0) {
                // 说明不欠账, l  可以右移
                while (map[s.charAt(l)] < 0) {
                    map[s.charAt(l++)]++;
                }
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ans = s.substring(l, r + 1);
                }
                all = 1;
                map[s.charAt(l++)] = 1;
            }
            r ++;
        }
        return ans;
    }




    public static void main(String[] args) {
        Code076MinimumWindowSubstring substring = new Code076MinimumWindowSubstring();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(substring.minWindow1(s, t));
    }


}
