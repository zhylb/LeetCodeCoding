package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 10:56
 */
public class Code038CountAndSay {

    /**
     * 这个题目很简单, 理解题目即可<br/>
     * 2 ms, faster than 99.57%, 39.5 MB, less than 99.63%<br/>
     * @param n
     * @return java.lang.String
     * @author lihd
     * @date 2022/9/4 11:03
     */
    public String countAndSay(int n) {
        String s = "1";
        for (int i = 0; i < n - 1; i++) {
            s = say(s);
        }
        return s;
    }

    private String say(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            int same = 1;
            while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                i ++;
                same ++;
            }

            sb.append(same).append(s.charAt(i));
            i ++;
        }
        return sb.toString();
    }




}
