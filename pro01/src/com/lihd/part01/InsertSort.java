package com.lihd.part01;

import org.junit.Test;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/1 23:43
 */
public class InsertSort extends BaseSort{
    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length ; i++) {
            //之前这里写错了 因为写了一个静态的arr[i] < arr[j]
            for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j] ; j--) {
                swap(arr,j,j+1);
            }
        }
    }

    @Override
    @Test
    public void testSort() {
        super.testSort();
    }
}
