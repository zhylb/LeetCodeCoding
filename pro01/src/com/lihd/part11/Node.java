package com.lihd.part11;

import java.util.ArrayList;

/**
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/18 16:37
 */
public class Node<N, E> {
    N val;
    int in;
    int out;
    ArrayList<Node<N,E>> nexts = new ArrayList<>();
    ArrayList<Edge<N,E>> edges = new ArrayList<>();
    public Node(N val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
