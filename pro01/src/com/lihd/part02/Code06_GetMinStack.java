package com.lihd.part02;

import org.junit.Test;

import java.util.Comparator;
import java.util.Stack;

/**
 * 设计一个可以获取最小 值的栈
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/2 22:40
 */
public class Code06_GetMinStack {

    @Test
    public void testGetMinStack(){
        GetMinStack<Integer> stack = new GetMinStack<>();
        stack.push(8);
        stack.push(7);
        stack.push(7);
        stack.push(9);
        stack.push(2);
        stack.push(2);
        //使用debug 看 更方便

        System.out.println(stack.getMin());
    }

}

class GetMinStack<T extends Comparable>{
    Stack<T> stack = new Stack<>();
    Stack<T> help = new Stack<>();

    public void push(T ele){
        if(stack.isEmpty()){
            help.push(ele);
        }else{
            T peek = help.peek();
            if(ele.compareTo(peek) < 0){ // ele < peek
                help.push(ele);
            }else {
                help.push(peek);
            }
        }
        stack.push(ele);
    }

    public T pop(){
        help.pop();
        return stack.pop();
    }

    public T peek(){
        return help.peek();
    }

    public T getMin(){
        return help.peek();
    }


}
