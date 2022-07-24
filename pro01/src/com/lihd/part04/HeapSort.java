package com.lihd.part04;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/3 18:32
 */
public class HeapSort extends BaseSort {


    @Override
    public void sort(int[] arr) {
        MyHeapSort.improveSort(arr);
    }

    @Override
    public void testSort() {
        super.testSort();
    }
}
