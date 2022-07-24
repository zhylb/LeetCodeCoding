package com.lihd.part01;

import org.junit.Test;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/1 23:07
 */

public class SelectSort extends BaseSort{

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            swap(arr,minIndex,i);
        }
    }
    @Override
    @Test
    public void testSort() {
        super.testSort();
    }
}
