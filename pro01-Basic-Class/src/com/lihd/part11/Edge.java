package com.lihd.part11;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/18 16:37
 */
public class Edge<N, E>{
    E weight;
    Node<N, E> from;
    Node<N, E> to;
    public Edge(E weight, Node<N, E> from, Node<N, E> to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
