package com.lihd.part11;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/18 18:52
 */
public class UnionFind<N, E>{

    HashMap<Node<N, E>, Node<N, E>> pointHead = new HashMap<>();
    HashMap<Node<N, E>, Integer> pointNodeNums = new HashMap<>();

    public void addAll(Collection<Node<N,E>> nodes) {
        for (Node<N, E> node : nodes) {
            add(node);
        }
    }

    public void add(Node<N, E> node) {
        pointHead.put(node, node);
        pointNodeNums.put(node, 1);
    }

    public Node<N, E> getPoint(Node<N, E> node) {
        Stack<Node<N, E>> stack = new Stack<>();
        while (node != pointHead.get(node)) {
            stack.push(node);
            node = pointHead.get(node);
        }
        //扁平化处理
        while (!stack.isEmpty()) {
            pointHead.put(stack.pop(), node);
        }
        return node;
    }

    public boolean isSameSet(Node<N, E> x, Node<N, E> y) {
        return getPoint(x) == getPoint(y);
    }


    public void union(Node<N, E> x, Node<N, E> y) {
        if (isSameSet(x, y)) {
            return;
        }

        Node<N, E> pointX = getPoint(x);
        Node<N, E> pointY = getPoint(y);

        Integer pointXNum = pointNodeNums.get(pointX);
        Integer pointYNum = pointNodeNums.get(pointY);

        Node<N, E> large = pointXNum >= pointYNum ? pointX : pointY;
        Node<N, E> small = pointX == large ? pointY : pointX;

        pointHead.put(small, large);

        pointNodeNums.put(large, pointXNum + pointYNum);
        pointNodeNums.remove(small);
    }




}
