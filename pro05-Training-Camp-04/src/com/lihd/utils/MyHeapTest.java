package com.lihd.utils;

import javax.xml.ws.spi.http.HttpHandler;
import java.util.PriorityQueue;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/18 13:34
 */
public class MyHeapTest {

    public static void main(String[] args) {
//        MyHeap<Integer> heap = new MyHeap<>(10);
//        heap.add(8);
//        heap.add(3);
//        heap.add(4);
//        heap.add(7);
//        System.out.println(heap.poll());
//        System.out.println(heap.poll());
//        System.out.println(heap.poll());
//        System.out.println(heap.poll());

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(100);
        heap.add(1);
        System.out.println(heap.poll());
    }
}
