package com.lihd.part11;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/18 17:07
 */
public class Code02DFS {


    public static <N, E> void DFS(Node<N, E> node) {
        if (node == null) {
            return;
        }
        //记录那个节点被访问过
        HashSet<Node<N, E>> flag = new HashSet<>();
        //记录深度优先的路径
        Stack<Node<N, E>> stack = new Stack<>();
        flag.add(node);
        System.out.println(node.val);
        stack.add(node);
        while (!stack.isEmpty()) {
            Node<N, E> pop = stack.pop();
            for (Node<N, E> next : pop.nexts) {
                if (!flag.contains(next)) {
                    stack.add(pop);
                    stack.add(next);
                    flag.add(next);
                    System.out.println(next.val);
                    break;
                }
            }
        }
    }

}
