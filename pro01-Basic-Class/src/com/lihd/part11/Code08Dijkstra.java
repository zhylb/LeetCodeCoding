package com.lihd.part11;

import java.util.HashMap;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/20 23:57
 */
public class Code08Dijkstra {


    public static class NodeRecord {
        Node<Integer, Integer> node;
        int distance;

        public NodeRecord(Node<Integer, Integer> node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap{

        Node<Integer, Integer>[] arr;
        HashMap<Node<Integer, Integer>, Integer> indexMap = new HashMap<>();
        HashMap<Node<Integer, Integer>, Integer> distanceMap = new HashMap<>();
        int size;

        public NodeHeap(int maxSize) {
            arr = new Node[maxSize];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        //在那个位置heapify
        public void heapify(int index) {
            while (distanceMap.get(arr[index])< distanceMap.get(arr[(index - 1) / 2])) {
                swap(index, (index - 1)/2);
                index = (index - 1) / 2;
            }
        }

        public void insertHeapify(int index, int heapSize) {
            int left = index * 2 + 1 ;
            while (left < heapSize) {
                int minIndex = left + 1 < heapSize && distanceMap.get(arr[left + 1]) < distanceMap.get(arr[left]) ? left + 1 : left;
                //父节点 比最小的都小 返回
//                if (distanceMap.get(index) < distanceMap.get(minIndex)) {
//                    return;
//                }
                if (distanceMap.get(arr[minIndex]) >= distanceMap.get(arr[index])) {
                    return;
                }
                //父节点和最小的孩子交换
                swap(minIndex, index);
                index = minIndex;
                left = index * 2 + 1 ;
            }
        }

        public NodeRecord poll() {
            if (isEmpty()) {
                return null;
            }
//

            Node<Integer, Integer> node = arr[0];
            NodeRecord record = new NodeRecord(node, distanceMap.get(node));
            distanceMap.remove(node);
            indexMap.put(node, -1);
            size --;
            swap(0,size);
            insertHeapify(0,size);
            return record;
//            NodeRecord nodeRecord = new NodeRecord(arr[0], distanceMap.get(arr[0]));
//            swap(0, size - 1);
//            indexMap.put(arr[size - 1], -1);
//            distanceMap.remove(arr[size - 1]);
//            // free C++同学还要把原本堆顶节点析构，对java同学不必
//            arr[size - 1] = null;
//            insertHeapify(0, --size);
//            return nodeRecord;
        }

        public boolean isEntered(Node<Integer,Integer> node) {
            return indexMap.containsKey(node);
        }

        public boolean inHeap(Node<Integer,Integer> node) {
            return isEntered(node) && indexMap.get(node) != -1;
        }

        public void addOrUpdateOrIgnore(Node<Integer,Integer> node, int distance) {
            if (inHeap(node)) {
                //在堆上 更新
                Integer index = indexMap.get(node);
                System.out.println(node);
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapify(index);
            }

            if (!isEntered(node)) {
                //没进来过
                arr[size] = node;
                indexMap.put(node, size);
                distanceMap.put(node, distance);
                heapify(size);
                size ++;
            }

            //被删除的忽略即可
        }

        public void swap(int i, int j) {
            indexMap.put(arr[i], j);
            indexMap.put(arr[j], i);
            Node<Integer,Integer> temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }

    public static HashMap<Node<Integer, Integer>, Integer> dijkstra(Node<Integer, Integer> node) {
        HashMap<Node<Integer, Integer>, Integer> ans = new HashMap<>();
        NodeHeap heap = new NodeHeap(50);
        heap.addOrUpdateOrIgnore(node, 0);
        while (!heap.isEmpty()) {
            NodeRecord record = heap.poll();
            int distance = record.distance;
            Node<Integer, Integer> pollNode = record.node;
            for (Edge<Integer, Integer> edge : pollNode.edges) {
                Node<Integer, Integer> to = edge.to;
                heap.addOrUpdateOrIgnore(to,distance + edge.weight);
            }
            ans.put(pollNode, distance);
        }
        return ans;
    }
    public static void main(String[] args) {
        Integer[][] martix = {{1,1,4},{6,1,3},{8,1,2},{1,2,5},{4,3,5},{8,4,5},{2,4,3},{2,3,2},};
        Graph<Integer, Integer> graph = GraphGenerate.generateGraphByTriad(martix);
        HashMap<Node<Integer, Integer>, Integer> ans = dijkstra(graph.nodes.get(1));
        ans.entrySet().forEach(System.out::println);
    }

}
