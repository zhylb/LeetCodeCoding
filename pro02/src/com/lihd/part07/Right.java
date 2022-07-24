package com.lihd.part07;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/1 12:39
 */
public class Right {


    public int[] arr;

    public Right(int[] a) {
        arr = new int[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            arr[i + 1] = a[i];
        }
    }

    public void add(int L, int R, int V) {
        for (int i = L; i <= R; i++) {
            arr[i] += V;
        }
    }

    public void update(int L, int R, int V) {
        for (int i = L; i <= R; i++) {
            arr[i] = V;
        }
    }

    public long query(int L, int R) {
        long ans = 0;
        for (int i = L; i <= R; i++) {
            ans += arr[i];
        }
        return ans;
    }

}
