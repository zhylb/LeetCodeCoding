package com.lihd.part01;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/1 20:12
 */
public class Code013RomanToInteger {
    /**
     * 罗马数字转整数, 没什么算法可以学到
     * 5 ms, faster than 93.99%, 45.3 MB, less than 66.91%
     * @param s 罗马数字
     * @return int 整数
     * @author lihd
     * @date 2022/9/1 20:16
     */
    public int romanToInt(String s) {
        int[] arr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'I':
                    arr[i] = 1;
                    break;
                case 'V':
                    arr[i] = 5;
                    break;
                case 'X':
                    arr[i] = 10;
                    break;
                case 'L':
                    arr[i] = 50;
                    break;
                case 'C':
                    arr[i] = 100;
                    break;
                case 'D':
                    arr[i] = 500;
                    break;
                case 'M':
                    arr[i] = 1000;
                    break;
            }
        }
        int ans = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                ans -= arr[i];
            } else {
                ans += arr[i];
            }
        }
        ans += arr[arr.length - 1];
        return ans;
    }
}
