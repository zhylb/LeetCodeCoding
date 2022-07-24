package com.lihd.part07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/5 16:42
 */
public class TreeTraverseIter {

    //使用迭代的方式实现

    //栈中不要放空的元素
    public void pre(Node node){
        if(node == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()){
            Node pop = stack.pop();
            System.out.print(pop.value + ", ");
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    public void in(Node node){
        if(node == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty() || node != null){
            if(node != null){
                node = node.left;
            }else{
                Node pop = stack.pop();
                System.out.print(pop.value + ", ");
                node = pop.right;
            }
        }
    }

    public void post(Node node){
        if(node == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        Node lastProcess = node;
        while(!stack.isEmpty()){
            node = stack.peek();//看一下
            if(node.left != null && node.left != lastProcess && node.right != lastProcess){
                stack.push(node.left);
            }else if(node.right != null && node.right != lastProcess){
                stack.push(node.right);
            }else{
                System.out.println(stack.pop().value + ", ");
                lastProcess = node;
            }
        }
    }

    public void level(Node node){
        if(node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.value + ", ");
            if(poll.left != null){
                queue.add(poll.left);
            }
            if(poll.right != null){
                queue.add(poll.right);
            }
        }
    }


}
