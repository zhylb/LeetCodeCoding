package com.lihd.part11;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/18 16:46
 */
public class Code01BFS {
    //宽度优先遍历
    public static <N, E> void BFS(Node<N, E> node) {
        if (node == null) {
            return;
        }
        HashSet<Node<N, E>> flag = new HashSet<>();
        Queue<Node<N, E>> queue = new LinkedList<>();
        queue.add(node);
        flag.add(node);
        while (!queue.isEmpty()) {
            Node<N, E> poll = queue.poll();
            System.out.println(poll.val);
            for (Node<N, E> next : poll.nexts) {
                if (!flag.contains(next)) {
                    flag.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
