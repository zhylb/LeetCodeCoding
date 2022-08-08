package com.lihd.part11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/19 11:34
 */
public class Code06Dijkstra {
    public static <N> Node<N, Integer> getNodeWithoutUsed(HashMap<Node<N, Integer>, Integer> ans, HashSet<Node<N, Integer>> used) {
        int min = Integer.MAX_VALUE;
        Node<N, Integer> minNode = null;
        for (Map.Entry<Node<N, Integer>, Integer> entry : ans.entrySet()) {
            if (!used.contains(entry.getKey()) && entry.getValue() < min) {
                min = entry.getValue();
                minNode = entry.getKey();
            }
        }
        return minNode;
    }
    public static <N> HashMap<Node<N, Integer>, Integer> dijkstra(Node<N, Integer> inNode) {
        HashMap<Node<N, Integer>, Integer> ans = new HashMap<>();
        HashSet<Node<N, Integer>> used = new HashSet<>();
        ans.put(inNode, 0);
        Node<N, Integer> node = getNodeWithoutUsed(ans, used);
        while (node != null) {
            Integer distance = ans.get(node);
            for (Edge<N, Integer> edge : node.edges) {
                if (ans.containsKey(edge.to)) {
                    //包含
                    ans.put(edge.to,Math.min(ans.get(edge.to), distance + edge.weight));
                } else {
                    //不包含
                    ans.put(edge.to, distance + edge.weight);
                }
            }
            used.add(node);//添加到黑名单
            node = getNodeWithoutUsed(ans, used);
        }
        return ans;
    }
}
