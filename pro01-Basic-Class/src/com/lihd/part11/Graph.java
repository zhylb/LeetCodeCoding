package com.lihd.part11;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/18 16:38
 */
public class Graph<N, E> {
    //图 由点集和边集组成
    HashMap<N, Node<N, E>> nodes = new HashMap<>();
    HashSet<Edge<N, E>> edges = new HashSet<>();
}
