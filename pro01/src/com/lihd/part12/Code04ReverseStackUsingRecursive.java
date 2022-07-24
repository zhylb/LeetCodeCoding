package com.lihd.part12;

import java.util.Stack;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/19 11:35
 */
public class Code04ReverseStackUsingRecursive {

    //只使用递归完成 stack的反转
    public static<T> void reverse(Stack<T> stack) {
        if (!stack.isEmpty()) {
            T bottom = popFromBottom(stack);
            reverse(stack);
            stack.add(bottom);
        }
    }
    //只使用递归完成 stack的反转
    public static<T> T popFromBottom(Stack<T> stack) {
        T pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        } else {
            T ans = popFromBottom(stack);
            stack.add(pop);
            return ans;
        }
    }
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        reverse(stack);
        System.out.println(stack);
    }
}
