package com.lihd.part04;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/3 17:37
 */
public class MyHeapTest {

    @Test
    public void test01(){
        MyHeap<Integer> heap = new MyHeap<>(10);
        heap.add(12);
        heap.add(15);
        heap.add(6);
        heap.add(3);
        heap.add(19);
        heap.add(9);

        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
    }

    @Test
    public void testMyHeapSort(){
        Integer[] arr = {7,5,3,6,7,1,2,9,4,3};
//        MyHeapSort.sort(arr);
        MyHeapSort.improveSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
