package com.lihd.part08;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/18 22:46
 */
public class Code01RegularExpressionMatch {


    public static boolean match(String str, String exp) {
        return process(0, 0, str.toCharArray(), exp.toCharArray());
    }

    public static boolean process(int si, int ei, char[] str, char[] exp) {
        if (ei == exp.length) {
            return si == str.length;
        }

        //下一个字符不是 *
        if (ei == exp.length - 1 || exp[ei + 1] != '*') {
            return si < str.length
                    && (str[si] == exp[ei] || exp[ei] == '.')
                    && process(si + 1, ei + 1, str, exp);
        }

        //下一个字符是 *
        while (si < str.length && (str[si] == exp[ei] || exp[ei] == '.')) {
            if (process(si, ei + 2, str, exp)) {
                return true;
            }
            si++;
        }
        return process(si, ei + 2, str, exp);

    }

    public static boolean matchDp(String str, String exp) {
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();

        boolean[][] dp = new boolean[s.length + 1][e.length + 1];

        // 最后一列
        dp[s.length][e.length] = true;

        // 倒数第二列
        dp[s.length - 1][e.length - 1] = e[e.length - 1] == '.' || e[e.length - 1] == s[s.length - 1];

        //倒数第一行
        for (int i = e.length - 1; i >= 1; i-= 2) {
            if (e[i] == '*') {
                dp[s.length][i - 1] = true;
            } else {
                break;
            }
        }

        for (int si = s.length - 1; si >= 0; si--) {
            for (int ei = e.length - 2; ei >= 0; ei--) {
                if (e[ei + 1] != '*') {
                    dp[si][ei] = si < s.length && (s[si] == e[ei] || e[ei] == '.') && dp[si + 1][ ei + 1];
                    continue;
                }
                //下一个字符是 *
                int i = si;
                while (i < s.length && (s[i] == e[ei] || e[ei] == '.')) {
                    if (dp[i][ei + 2]) {
                        dp[si][ei] = true;
                        break;
                    }
                    i++;
                }
                dp[si][ei] = dp[si][ei] || dp[i][ei + 2];
            }
        }
        return dp[0][0];
        
    }
    
    

    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(match(str, exp));
        System.out.println(matchDp(str,exp));
    }
}
