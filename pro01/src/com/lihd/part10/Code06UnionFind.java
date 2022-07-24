package com.lihd.part10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集 一定要好好掌握
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/17 21:06
 */
public class Code06UnionFind {


    public static class Node<T> {
        T val;
        public Node(T val) {
            this.val = val;
        }
    }

    public static class UnionFind<T> {
        //根据 T 查找 Node<T> 不会改变
        HashMap<T, Node<T>> nodes = new HashMap<>();
        //根据 Node<T>查找 代表点
        HashMap<Node<T>, Node<T>> parentsMap = new HashMap<>();
        //代表点  的节点个数
        HashMap<Node<T>, Integer> pointSize = new HashMap<>();

        public void addListInfo(List<T> list) {
            for (T t : list) {
                addOneInfo(t);
            }
        }

        public void addOneInfo(T t) {
            Node<T> node = new Node<>(t);
            nodes.put(t, node);
            parentsMap.put(node, node);
            pointSize.put(node, 1);
        }


        public Node<T> getPoint(Node<T> node) {
            Node<T> cur = node;
            Stack<Node<T>> stack = new Stack<>();
            while (cur != parentsMap.get(cur)) {
                stack.push(node);
                cur = parentsMap.get(cur);
            }
            while (!stack.isEmpty()) {
                parentsMap.put(stack.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(T x, T y) {
            if (!nodes.containsKey(x) || !nodes.containsKey(y)) {
                //有一个没有注册就返回false
                return false;
            }
            return getPoint(nodes.get(x)) == getPoint(nodes.get(y));
        }

        public void union(T x, T y) {
            if (!nodes.containsKey(x) || !nodes.containsKey(y)) {
                //有一个没有注册就返回
                return;
            }
            Node<T> pointX = getPoint(nodes.get(x));
            Node<T> pointY = getPoint(nodes.get(y));
            if (pointX == pointY) {
                //如果相等说明union过 不用管直接返回
                return;
            }
            int xSize = pointSize.get(pointX);
            int ySize = pointSize.get(pointY);
            Node<T> big = xSize >= ySize ? pointX : pointY;
            Node<T> small = big == pointX ? pointY : pointX;
            parentsMap.put(small, big);
            pointSize.remove(small);
            pointSize.put(big, xSize + ySize);
        }


        public int getPointSizeMapLen() {
            return pointSize.size();
        }





    }

}
