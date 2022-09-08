package com.lihd.top;

import javafx.scene.paint.Stop;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/6 17:51
 */
public class Code091DecodeWays {

    /**
     * 就是问一个方法数, 记得之前讲过 <br/>
     * 这里提一下老师的做法, 就是不做那个判断 就是 int a = (chs[i] - '0') * 10 + chs[i - 1] - '0' <br/>
     * 然后看 a是不是 <= 26的即可, 我是(chs[i] == '1' || chs[i] == '2' && chs[i + 1] <= '6') <br/>
     * 有趣的是 第一次忘记加括号, 导致 || 和 && 的运算顺序不是我想要的, 于是出错, 因此注意加括号<br/>
     * 1 ms, faster than 98.71%, 42.2 MB, less than 61.72%
     * @param s 字符串
     * @return int
     * @author lihd
     * @date 2022/9/6 20:03
     */
    public int numDecodings(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (chs[i] == '0') {
                dp[i] = 0;
            } else {
                int p2 = 0;
                if (i + 1 < chs.length && (chs[i] == '1' || chs[i] == '2' && chs[i + 1] <= '6')) {
                    p2 = dp[i + 2];
                }
                dp[i] = dp[i +1] + p2;
            }
        }
        return dp[0];
    }

    public int numDecodings1(String s) {
        return process(0, s.toCharArray());
    }

    // 从i位置到最后 可以的解码方式
    private static int process(int i, char[] chs) {
        if (i == chs.length) {
            return 1;
        }
        if (chs[i] == '0') {
            return 0;
        }
        int p1 = process(i + 1, chs);
        int p2 = 0;
        if (i + 1 < chs.length && (chs[i] == '1' || chs[i] == '2' && chs[i + 1] <= '6')) {
            p2 = process(i + 2, chs);
        }
        return p1 + p2;
    }

    public static void main(String[] args) {
        String s = "12";
        System.out.println(process(0, s.toCharArray()));

    }

}
