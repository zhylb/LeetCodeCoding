package com.lihd.part01;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/1 22:42
 */
public class Code005LongestPalindromicSubstr {
    /**
     * 复习一下manacher算法即可<br/>
     *
     * 13 ms, faster than 96.92%, 42.4 MB, less than 91.57%
     * @param s 要判断的字符串
     * @return java.lang.String
     * @author lihd
     * @date 2022/9/1 23:01
     */
    public String longestPalindrome(String s) {
        return manacher(s);
    }

    private String manacher(String s) {
        char[] chs = addAuxiliaryWords(s);
        int[] pArr = new int[chs.length];

        int c = 0;
        int r = 1;
        int i = 1;

        int maxIndex = 0;
        while (i < chs.length) {
            pArr[i] = i < r ? Math.min(pArr[(c << 1) - i], r - i) : 1;

            while (i - pArr[i] >= 0 && i + pArr[i] < chs.length && chs[i - pArr[i]] == chs[i + pArr[i]]) {
                pArr[i]++;
            }
            // 这里是等号也行
            if (i + pArr[i] > r) {
                r = i + pArr[i];
                c = i;
            }

            if (pArr[maxIndex] < pArr[i]) {
                maxIndex = i;
            }

            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = maxIndex - pArr[maxIndex] + 1; j <= maxIndex + pArr[maxIndex] - 1; j++) {
            if (chs[j] != 0) {
                sb.append(chs[j]);
            }
        }
        return sb.toString();
    }

    private char[] addAuxiliaryWords(String s) {
        char[] chs = new char[s.length() << 1 | 1];
        for (int i = 0; i < s.length(); i++) {
            chs[i << 1 | 1] = s.charAt(i);
        }
        return chs;
    }

    public static void main(String[] args) {
        Code005LongestPalindromicSubstr m = new Code005LongestPalindromicSubstr();
        System.out.println(m.manacher("aadafadbcc"));
    }

}
