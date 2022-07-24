package com.lihd.part03;

import java.util.HashMap;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/16 15:35
 */
public class Code07TopKTimesRealTime {


    public static class Node{
        String str;
        int times;

        public Node(String str, int times) {
            this.str = str;
            this.times = times;
        }
    }

    public static class TopKRecord{
        private Node[] heap;
        private int size;
        private int maxSize;
        private HashMap<String, Node> timesMap;
        private HashMap<Node, Integer> indexMap;
        public TopKRecord(int K) {
            heap = new Node[K];
            size = 0;
            maxSize = K;
            timesMap = new HashMap<>();
            indexMap = new HashMap<>();
        }

        public void add(String s) {
            if (timesMap.containsKey(s)) {
                //之前进来过
                Node node = timesMap.get(s);
                node.times ++;//次数 + 1
                Integer index = indexMap.get(node);
                //这个index可能不在堆上面。




            } else {
                //没有进来过
                Node node = new Node(s, 1);
                timesMap.put(s, node);
                indexMap.put(node, -1);//表示不在堆上面
                //一定不在堆上。



            }
        }

        public void swap(int i, int j) {
            indexMap.put(heap[i], j);
            indexMap.put(heap[j], i);
            Node temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }








    }


}
