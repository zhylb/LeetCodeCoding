package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/8 16:04
 */
public class Code028FindTheIndexOfTheFirstOccurrenceInAString {
    /**
     * 暴力方法 时间复杂度 O(N * M)<br/>
     * 我就用了三个变量, 空间复杂度 不至于说 这么高吧 <br/>
     * 1 ms, faster than 64.52%, 42.1 MB, less than 48.45%
     * @param haystack
	 * @param needle
     * @return int
     * @author lihd
     * @date 2022/9/8 16:16
     */
    public int strStr(String haystack, String needle) {
        int index = 0;
        int i;
        int j;
        while (index <= haystack.length() - needle.length()) {
            j = 0;
            i = index;
            while (j < needle.length() && haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            if (j == needle.length() ) {
                return index;
            }
            index ++;
        }
        return -1;
    }
}
