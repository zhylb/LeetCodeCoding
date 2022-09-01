package com.lihd.part03;

/**
 *
 * <a href="https://leetcode.com/problems/distinct-subsequences-ii">...</a>
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/24 23:27
 */
public class Code04DistinctSubsequencesII {

    /**
     * 5 ms, faster than 86.84%
     * 42 MB, less than 88.49%
     * @param s 字符串
     * @return int
     * @author lihd
     * @date 2022/8/24 23:41
     */
    public static int distinctSubseqII(String s) {
        int[] map = new int[26];
        int all = 1;
        int mod = (int) (1e9 + 7);
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            int dis = (all - map[index] + mod) % mod;
            all = (all % mod + dis % mod) % mod;
            map[index] = (map[index] % mod+ dis % mod) % mod;
        }
        return all - 1;
    }

    public static void main(String[] args) {
        String s = "blljuffdyfrkqtwfyfztpdiyktrhftgtabxxoibcclbjvirnqyynkyaqlxgyybkgyzvcahmytjdqqtctirnxfjpktxmjkojlvvrr";
        System.out.println(distinctSubseqII(s));
    }
}
