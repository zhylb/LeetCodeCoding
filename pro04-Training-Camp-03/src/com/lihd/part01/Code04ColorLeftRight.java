package com.lihd.part01;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/9 21:31
 */
public class Code04ColorLeftRight {


    public static int colorMin(String s) {

        char[] chs = s.toCharArray();
        int redCount = 0;
        for (char ch : chs) {
            if (ch == 'R') {
                redCount++;
            }
        }
        int greenCount = 0;
        int ans = redCount;
        for (char ch : chs) {
            if (ch == 'G') {
                greenCount++;
            } else {
                redCount--;
            }
            ans = Math.min(ans, redCount + greenCount);
        }
//        ans = Math.min(ans, greenCount);
        return ans;
    }

    public static void main(String[] args) {
        String canCanNeed = "GGGGGR";
        System.out.println(colorMin(canCanNeed));

    }

}
