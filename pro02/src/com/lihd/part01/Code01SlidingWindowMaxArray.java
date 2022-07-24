package com.lihd.part01;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/26 10:54
 */
public class Code01SlidingWindowMaxArray {
    public static int[] getMaxArray(int[] arr, int W) {
        if (arr == null || arr.length < W) {
            return null;
        }
        int[] ans = new int[arr.length - W + 1];
        SlidingWindow window = new SlidingWindow(arr);
        for (int i = 0; i < W; i++) {
            window.rightMove();
        }
        window.leftMove();
        for (int i = 0; i < ans.length; i++) {
            ans[i] = window.getMax();
            window.leftMove();
            window.rightMove();
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int W = 3;
        for (int i : getMaxArray(arr, W)) {
            System.out.println(i);
        }
    }
}
