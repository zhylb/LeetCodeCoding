package com.lihd.part01;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/9 21:01
 */
public class Code01CordCoverMaxPoint {
    public static int maxPoint(int[] arr, int k) {
        int l = 0;
        int r = 0;
        int ans = 0;
        //范围是 [L, R)
        //于是判断越界是 R < arr.length即可
        while (r < arr.length) {
            if (arr[r] - arr[l] <= k) {
                r++;
                ans = Math.max(ans, r - l);
            } else {
                l++;
            }
        }
        return ans;
    }
}
