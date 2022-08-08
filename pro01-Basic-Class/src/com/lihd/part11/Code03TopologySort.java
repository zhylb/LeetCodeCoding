package com.lihd.part11;

import java.util.*;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/18 17:13
 */
public class Code03TopologySort {


    public static <N, E> List<Node<N, E>> sortedTopology(Graph<N, E> graph) {
        ArrayList<Node<N, E>> list = new ArrayList<>();

        Queue<Node<N, E>> zeroInQueue = new LinkedList<>();
        HashMap<Node<N, E>, Integer> inMap = new HashMap<>();

        for (Node<N, E> node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }

        while (!zeroInQueue.isEmpty()) {
            Node<N, E> poll = zeroInQueue.poll();
            list.add(poll);
            for (Node<N, E> next : poll.nexts) {
//                next.in --; 不应该改变图的结构
                inMap.put(next, inMap.get(next));
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return list;
    }

}
