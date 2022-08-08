package com.lihd.part11;

import java.util.HashSet;

/**
 * Triad
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/18 16:53
 */
public class GraphGenerate {

    public static Graph<Integer, Integer> generateGraphByTriad(Integer[][] matrix) {
        Graph<Integer, Integer> graph = new Graph<>();
        for (Integer[] integers : matrix) {
            Integer weight = integers[0];
            Integer from = integers[1];
            Integer to = integers[2];
            if (!graph.nodes.containsKey(from)) {
                Node<Integer, Integer> node = new Node<>(from);
                graph.nodes.put(from, node);
            }
            if (!graph.nodes.containsKey(to)) {
                Node<Integer, Integer> node = new Node<>(to);
                graph.nodes.put(to, node);
            }
            // 此时 nodes中一定有from 和 to对应的节点
            Node<Integer, Integer> fromNode = graph.nodes.get(from);
            Node<Integer, Integer> toNode = graph.nodes.get(to);
            Edge<Integer, Integer> edge = new Edge<>(weight, fromNode, toNode);
            graph.edges.add(edge);
            //对 图的中的节点进行调整
            fromNode.nexts.add(toNode);
            fromNode.out++;
            fromNode.edges.add(edge);
            toNode.in++;
        }
        return graph;
    }
}
