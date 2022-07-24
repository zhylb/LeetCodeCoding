package com.lihd.part11;

import java.util.HashMap;

/**
 * 实现优化后的迪杰斯特拉算法
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/19 22:04
 */
public class Code07Dijkstra {


    public static HashMap<Node<Integer, Integer>, Integer> dijkstra(Node<Integer, Integer> node) {
        HashMap<Node<Integer, Integer>, Integer> ans = new HashMap<>();
        NodeHeap heap = new NodeHeap(50);
        heap.addOrUpdateOrIgnore(node,0);
        while (!heap.isEmpty()) {
            NodeRecord record = heap.pop();
            int distance = record.distance;
            Node<Integer, Integer> popNode = record.node;
            for (Edge<Integer, Integer> edge : popNode.edges) {
                Node<Integer, Integer> to = edge.to;
                heap.addOrUpdateOrIgnore(to,distance + edge.weight);
            }
            ans.put(popNode,distance);
        }
        return ans;
    }


    //最小堆中的基本单元
    public static class NodeRecord {
        Node<Integer,Integer> node;
        int distance;
        public NodeRecord(Node<Integer, Integer> node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        int size;
        Node<Integer,Integer>[] arr;
        HashMap<Node<Integer, Integer>, Integer> distanceMap = new HashMap<>();
        HashMap<Node<Integer,Integer>, Integer> indexMap = new HashMap<>();
        public NodeHeap(int maxSize) {
            arr = new Node[maxSize];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void swap(int i, int j) {
            indexMap.put(arr[i], j);
            indexMap.put(arr[j], i);
            Node temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public void insertHeapify(int index) {
            while (distanceMap.get(arr[index])< distanceMap.get(arr[(index - 1) / 2])) {
                swap(index, (index - 1)/2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int min = left + 1 < size && distanceMap.get(arr[left + 1]) < distanceMap.get(arr[left]) ? left + 1 : left;
                if (distanceMap.get(arr[min]) >= distanceMap.get(arr[index])) {
                    return;
                }
                swap(min, index);
                index = min;
                left = index * 2 + 1;
            }
        }

        public NodeRecord pop() {
            if (isEmpty()) {
                return null;
            }
            Node<Integer, Integer> node = arr[0];
            NodeRecord record = new NodeRecord(node, distanceMap.get(node));
            distanceMap.remove(node);
            indexMap.put(node, -1);
            size --;
            swap(0,size);
            heapify(0,size);
            return record;
//            NodeRecord nodeRecord = new NodeRecord(arr[0], distanceMap.get(arr[0]));
//            swap(0, size - 1);
//            indexMap.put(arr[size - 1], -1);
//            distanceMap.remove(arr[size - 1]);
//            // free C++同学还要把原本堆顶节点析构，对java同学不必
//            arr[size - 1] = null;
//            heapify(0, --size);
//            return nodeRecord;
        }

        public boolean isHeap(Node<Integer,Integer> node) {
            return isEntered(node) && indexMap.get(node) != -1;
        }

        public boolean isEntered(Node<Integer,Integer> node) {
            return indexMap.containsKey(node);
        }

        public void addOrUpdateOrIgnore(Node<Integer,Integer> node, int distance) {
            if (isHeap(node)) {
                //修改
                Integer i = indexMap.get(node);
                distanceMap.put(node,Math.min(distanceMap.get(node), distance));
                insertHeapify(i);
            }
            if (!isEntered(node)) {
                //添加
                arr[size] = node;
                indexMap.put(node,size);
                distanceMap.put(node,distance);
                insertHeapify(size);
                size ++;
            }
            //忽略
        }
    }
    public static void main(String[] args) {
        Integer[][] martix = {{1,1,4},{6,1,3},{8,1,2},{1,2,5},{4,3,5},{8,4,5},{2,4,3},{2,3,2},};
        Graph<Integer, Integer> graph = GraphGenerate.generateGraphByTriad(martix);
        HashMap<Node<Integer, Integer>, Integer> ans = dijkstra(graph.nodes.get(1));
        ans.entrySet().forEach(System.out::println);
    }
}
