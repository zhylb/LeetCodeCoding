package com.lihd.part01;

import org.junit.Test;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/1 23:22
 */
public class BubbleSort extends BaseSort{
    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if(arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    @Override
    @Test
    public void testSort() {
        super.testSort();
    }



    /*
    错误的写法
    for (int i = 0; i < arr.length - 1; i++) {
        for (int j = i; j < arr.length - 1; j++) {
            if(arr[j] > arr[j+1])
                swap(arr,j,j+1);
        }
    }*/
}
