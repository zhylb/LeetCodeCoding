package com.lihd.part02;

import org.junit.Test;

/**
 * 使用双向链表实现 队列和链表
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/2 12:38
 */
public class Code03_ByList {

    @Test
    public void testMyLinkedList(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addFromHead(1);
        list.addFromHead(2);
        System.out.println(list.getFromTail());
        System.out.println(list.getFromTail());

        list.addFromTail(3);
        list.addFromTail(4);
        System.out.println(list.getFromHead());
        System.out.println(list.getFromHead());
        System.out.println("-------------------------");

        list.addFromHead(9);
        list.addFromHead(8);
        list.addFromHead(7);
        list.addFromTail(6);
        list.addFromTail(5);
        list.addFromTail(4);

        //可以使用 debug 查看节点

        System.out.println(list.getFromHead());



    }

}


class LinkedListStack<T>{
    MyLinkedList<T> stack = new MyLinkedList<>();

    public void push(T ele){
        stack.addFromHead(ele);
    }

    public T pop(){
        return stack.getFromHead();
    }

}

class MyLinkedList<T>{
    DoublyNode<T> head = null;
    DoublyNode<T> tail = null;


    public void addFromHead(T ele){
        DoublyNode<T> node = new DoublyNode<>(ele);
        if(head == null){
            head = node;
            tail = node;
        }else{
            node.next = head;
            head.last = node;
            head = node;
        }
    }

    public void addFromTail(T ele){
        DoublyNode<T> node = new DoublyNode<>(ele);
        if(head == null){
            head = node;
            tail = node;
        }else {
            node.last = tail;
            tail.next = node;
            tail = node;
        }
    }

    public T getFromHead(){
        if(head == null){
            return null;
        }
        T ret = head.value;
        DoublyNode<T> node = head.next;
        if(node == null){
            head = null;
            tail = null;
        }else {
            node.last = null;
            head.next = null;
            head = node;
        }
        return ret;
    }

    public T getFromTail(){
        if(head == null){
            return null;
        }
        T ret = tail.value;
        DoublyNode<T> node = tail.last;
        if(node == null){
            head = null;
            tail = null;
        }else {
            node.next = null;
            tail.last = null;
            tail = node;
        }
        return ret;
    }

}
