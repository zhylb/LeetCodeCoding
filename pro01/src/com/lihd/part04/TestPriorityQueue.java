package com.lihd.part04;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/3 21:04
 */
public class TestPriorityQueue {

    //测试过了 没有问题
    @Test
    public void test01(){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(123);
        queue.add(12);
        queue.add(71);
        queue.add(67);
        queue.add(54);
        queue.add(23);
        queue.add(3);
        queue.add(88);

        System.out.println(queue);

        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}
