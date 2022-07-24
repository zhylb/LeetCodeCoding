package com.lihd.part04;

import org.junit.Test;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/3 23:25
 */
public class MyChangeableHeapTest {

    @Test
    public void test01(){

        //不能重写 equals 和 hashCode 你说这叫什么事
        Person p1 = new Person("李白", 19);
        Person p2 = new Person("张飞", 8);
        Person p3 = new Person("杜甫", 51);
        Person p4 = new Person("苏轼", 42);
        Person p5 = new Person("韩信", 12);

        MyChangeableHeap<Person> heap = new MyChangeableHeap<>(10);
        heap.add(p1);
        heap.add(p2);
        heap.add(p3);
        heap.add(p4);
        heap.add(p5);

        System.out.println(heap);

        p1.age = 88;

//        heap.refactor(p1);


//        Person oldVal = new Person("李白", 19);
//        heap.refactor(oldVal,p1);

        System.out.println(heap);


    }
}
