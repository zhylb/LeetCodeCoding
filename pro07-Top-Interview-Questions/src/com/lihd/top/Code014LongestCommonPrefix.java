package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/1 23:04
 */
public class Code014LongestCommonPrefix {
    /**
     * 3 ms, faster than 35.85%, 41.7 MB, less than 76.55%
     * @param strs 一堆字符串
     * @return java.lang.String 公共前缀
     * @author lihd
     * @date 2022/9/1 23:20
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        char ch = 0;
        int len = strs[0].length();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < strs.length; j++) {
                if (i < strs[j].length()) {
                    if (j == 0) {
                        ch = strs[j].charAt(i);
                    } else {
                        if (strs[j].charAt(i) != ch) {
                            return sb.toString();
                        }
                    }
                } else {
                    return sb.toString();
                }
            }
            sb.append(ch);
        }
        return sb.toString();

    }


}
