package com.lihd.exer01;

import com.lihd.part04.MyHeap;

import java.net.Inet4Address;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/19 22:37
 */
public class HeapTest {
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(20);
        heap.add(100);
        heap.add(23);
        heap.add(75);
        heap.add(88);
        heap.add(16);
        heap.add(99);
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
        System.out.println(heap);
    }
}
