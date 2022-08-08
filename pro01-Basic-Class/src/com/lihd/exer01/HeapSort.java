package com.lihd.exer01;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/19 23:05
 */
public class HeapSort {
    public static void heapSort(Integer[] arr) {
        Heap<Integer> heap = new Heap<>(arr.length);

//        for (Integer i : arr) {
//            heap.add(i);
//        }
        heap.addAll(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.poll();
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {23,4,1,8,9,3,4,2,74,6};
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
