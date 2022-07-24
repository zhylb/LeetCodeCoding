package com.lihd.part02;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 使用队列结构模拟栈
 */
public class Code05_queueAsStack {

    @Test
    public void testQueueStack(){
        QueueStack<Integer> stack = new QueueStack<>();
        stack.push(15);
        stack.push(14);
        stack.push(13);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(12);
        stack.push(11);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }

    @Test
    public void testStackQueue(){
        StackQueue<Integer> queue = new StackQueue<>();
        queue.push(4);
        queue.push(5);
        queue.push(6);
        queue.push(7);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.poll());

    }


}
//使用两个队列 模拟栈
class QueueStack<T>{
    Queue<T> queue = new LinkedList<>();
    Queue<T> help = new LinkedList<>();
    private void changeRoles(){
        Queue<T> temp = queue;
        queue = help;
        help = temp;
    }
    public void push(T ele){
        queue.add(ele);
    }
    public T pop(){
        int size = queue.size();//下面只能写成 size - 1而不是  size()
        for (int i = 0; i < size - 1; i++) {
            help.add(queue.poll());
        }
        T ret = queue.poll();
        changeRoles();
        return ret;
    }
}
//使用栈模拟两个队列
class StackQueue<T>{
    Stack<T> stack = new Stack<>();
    Stack<T> help = new Stack<>();

    private void transform(){
        //当且仅当辅助栈中没有元素时 转换 转换时要把所有的全部放入
        if(help.isEmpty()){
            while(!stack.isEmpty()){
                help.push(stack.pop());
            }
        }
    }

    public void push(T ele){
        stack.push(ele);
    }

    public T poll(){
        transform();
        return help.pop();
    }

    public T peek(){
        transform();
        return help.peek();
    }
}