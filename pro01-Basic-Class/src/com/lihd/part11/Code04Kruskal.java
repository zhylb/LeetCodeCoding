package com.lihd.part11;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/18 18:48
 */
public class Code04Kruskal {
    public static <N> Set<Edge<N, Integer>> kruskal(Graph<N, Integer> graph) {
        //使用并查集
        UnionFind<N, Integer> unionFind = new UnionFind<>();
        //并查集初始化
        unionFind.addAll(graph.nodes.values());
        //edge小根堆
        PriorityQueue<Edge<N, Integer>> heap = new PriorityQueue<>((a,b) -> a.weight - b.weight);
        heap.addAll(graph.edges);
        //要返回的东西
        HashSet<Edge<N,Integer>> ansSet = new HashSet<>();
        while (!heap.isEmpty()) {
            Edge<N, Integer> poll = heap.poll();
            //如果不是一个集合
            if (!unionFind.isSameSet(poll.from, poll.to)) {
                ansSet.add(poll);
                unionFind.union(poll.from, poll.to);
            }
        }
        return ansSet;
    }
}
