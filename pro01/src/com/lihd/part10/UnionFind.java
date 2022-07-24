package com.lihd.part10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 再次写一遍并查集
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/17 23:38
 */
public class UnionFind<T> {

    HashMap<T, Node<T>> nodesInfo = new HashMap<>();
    HashMap<Node<T>, Node<T>> pointMap = new HashMap<>();
    HashMap<Node<T>, Integer> pointNodesNumMap = new HashMap<>();

    public void add(T t) {
        Node<T> node = new Node<>(t);
        //注册node
        nodesInfo.put(t, node);
        //一个元素一个集合
        pointMap.put(node, node);
        pointNodesNumMap.put(node, 1);
    }

    public void addAll(List<T> all) {
        for (T t : all) {
            add(t);
        }
    }

    public boolean isRegister(T t) {
        return nodesInfo.containsKey(t);
    }

    public Node<T> getPoint(Node<T> node) {
        Stack<Node<T>> stack = new Stack<>();
        while (node != pointMap.get(node)) {
            stack.add(node);
            node = pointMap.get(node);
        }
        //扁平化处理
        while (!stack.isEmpty()) {
            pointMap.put(stack.pop(), node);//put : 更新
        }
        return node;
    }

    public boolean isSameSet(T x, T y) {
        if (!isRegister(x) || !isRegister(y)) {
            return false;
        }
        return getPoint(nodesInfo.get(x)) == getPoint(nodesInfo.get(y));
    }

    public int getPointNodesNumMapLen() {
        return pointNodesNumMap.size();
    }

    public void union(T x, T y) {
        if (!isRegister(x) || !isRegister(y)) {
            return;
        }
        //两个肯定不一样
        Node<T> xInfo = getPoint(nodesInfo.get(x));
        Node<T> yInfo = getPoint(nodesInfo.get(y));
        if (xInfo == yInfo) {
            return;
        }
        int xNum = pointNodesNumMap.get(xInfo);
        int yNum = pointNodesNumMap.get(yInfo);

        Node<T> large = xNum >= yNum ? xInfo : yInfo;
        Node<T> small = xInfo == large ? yInfo : xInfo;


        pointMap.put(small, large);
        pointNodesNumMap.remove(small);
        pointNodesNumMap.put(large, xNum + yNum);
    }


    //====== 静态的类 放到最下面得了 ==============

    public static class Node<T> {
        T val;

        public Node(T val) {
            this.val = val;
        }
    }


}
