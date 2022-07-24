package com.lihd.part02;

import org.junit.Test;

import java.util.Arrays;

/**
 * 使用长度固定数组实现 队列
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/2 12:38
 */
public class Code04_ByArray {

    @Test
    public void testArrayQueue(){
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        queue.put(5);
        queue.put(4);
        queue.put(3);
        queue.put(2);

        Integer poll = queue.poll();

        queue.put(1);
        queue.put(8);

        Integer poll1 = queue.poll();
        Integer poll2 = queue.poll();

        queue.put(6);
        //queue.put(9);

        System.out.println(queue);

    }


    @Test
    public void testArrayStack(){
        //可以使用断点 检查正确性
        ArrayStack<Integer> stack = new ArrayStack<>(5);
        stack.push(1);
        stack.pop();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.pop();
        stack.pop();

        stack.push(6);

    }


}

class ArrayQueue<T>{
    private int len;
    private T[] arr;
    private int size;
    private int next;
    private int last;

    public ArrayQueue(int len){
        this.len = len;
        arr = (T[]) new Object[len];
    }

    public void put(T ele){
        if(isFull()) throw new RuntimeException("队列已满");
        size ++;
        arr[next] = ele;
        next = nextValue(next);
    }

    public T poll(){
        if(isEmpty()) throw new RuntimeException("队列为空");
        size --;
        T ele = arr[last];
        last = nextValue(last);
        return ele;
    }

    public T peek(){
        if(isEmpty()) throw new RuntimeException("队列为空");size --;
        return arr[last];
    }

    public boolean isFull(){
        return size == len;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int nextValue(int num){
        return num == len - 1 ? 0 : num + 1;
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }
}

class ArrayStack<T>{

    int size;
    T[] arr;
    int next;

    public ArrayStack(int size) {
        this.size = size;
        arr = (T[]) new Object[size];
    }

    public void push(T ele){
        if(next == size) throw new RuntimeException("栈满");
        arr[next ++] = ele;
    }

    public T pop(){
        if(isFull()) throw new RuntimeException("栈空");
        return arr[--next];
    }

    public T peek(){
        if(isEmpty()) throw new RuntimeException("栈空");
        return arr[next - 1];
    }

    public boolean isFull(){
        return next == size;
    }

    public boolean isEmpty(){
        return next == 0;
    }



}

