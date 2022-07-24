package com.lihd.part11;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/18 19:16
 */
public class Code05Prim {

    public static <N> Set<Edge<N, Integer>> prim(Graph<N, Integer> graph) {

        HashSet<Node<N, Integer>> activeNode = new HashSet<>();
        HashSet<Edge<N, Integer>> usedSet = new HashSet<>();
        PriorityQueue<Edge<N, Integer>> heap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        Set<Edge<N, Integer>> ans = new HashSet<>();
        for (Node<N, Integer> node : graph.nodes.values()) {
            if (activeNode.contains(node)) {
                continue;
            }
            activeNode.add(node);
            for (Edge<N, Integer> edge : node.edges) {
                if (!usedSet.contains(edge)) {
                    heap.add(edge);
                    usedSet.add(edge);
                }
            }
            while (!heap.isEmpty()) {
                Edge<N, Integer> poll = heap.poll();
                if (!activeNode.contains(poll.to)) {
                    //可以进行操作 因为之前没有被激活
                    ans.add(poll);//确定是没有激活的节点后再加入
                    activeNode.add(poll.to);
                    for (Edge<N, Integer> edge : poll.to.edges) {
                        if (!usedSet.contains(edge)) {
                            heap.add(edge);
                            usedSet.add(edge);
                        }
                    }
                }
            }
        }
        return ans;
    }

}
