package com.lihd.part01;

import java.util.HashMap;

/**
 * 经过对数器验证了, 应该是没有问题的 是真难啊
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/31 17:20
 */
public class Code03LFU {
    private static class Node<K, V> {
        K key;
        V val;
        int times;
        Node<K, V> prev;
        Node<K, V> next;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            times = 1;
        }
    }

    private static class NodeList<K, V> {
        // 桶内部的 头 和 尾
        Node<K, V> head;
        Node<K, V> tail;
        // 桶之间的指针
        NodeList<K, V> prev;
        NodeList<K, V> next;

        public NodeList() {

        }
    }

    public static class LFUCache<K, V> {

        int capacity;
        int size;
        NodeList<K, V> head = new NodeList<>();
        HashMap<K, Node<K, V>> nodeMap = new HashMap<>();
        HashMap<Node<K, V>, NodeList<K, V>> bucketMap = new HashMap<>();

        public LFUCache(int capacity) {
            this.capacity = capacity;
        }

        /**
         * put方法, 公共的方法
         * @param key 键
         * @param val 值
         * @author lihd
         * @date 2022/8/31 21:19
         */
        public void put(K key, V val) {
            if (!nodeMap.containsKey(key)) {
                // 说明是添加
                if (size == capacity) {
                    // 满了
                    removeByLFU();
                }
                // 没满
                add(key, val);
            } else {
                // 说明是修改
                update(key, val);
            }
        }

        /**
         * 更新操作, 最复杂的方法
         * @param key 键
	     * @param val 值
         * @author lihd
         * @date 2022/8/31 21:19
         */
        private void update(K key, V val) {
            // 所在的位置
            Node<K, V> node = nodeMap.get(key);
            // 所处的桶
            NodeList<K, V> nodeList = bucketMap.get(node);


            // 提前准备好后面的环境
            NodeList<K, V> nextList = nodeList.next;
            if (nodeList.next == null || nodeList.next.head.times != node.times + 1) {
                // 说明我们需要先把下一个桶 建立出来 并且连接好
                nextList = new NodeList<>();
                nextList.next = nodeList.next;
                if (nodeList.next != null) {
                    nodeList.next.prev = nextList;
                }
                nodeList.next = nextList;
                nextList.prev = nodeList;
            }

            // 对这个桶进行调整
            removeNodeFromBucket(node, nodeList);
            node.val = val;
            node.times++;

            // 连接后面的环境
            // 在nextList 中把node加上就行了
            if (nextList.head == null) {
                // 说明是空桶
                nextList.head = node;
                nextList.tail = node;
            } else {
                // 说明有元素
                node.next = nextList.head;
                nextList.head.prev = node;
                nextList.head = node;
            }
            nodeMap.put(key, node);
            bucketMap.put(node, nextList);
        }

        /**
         * 从桶中删除指定的节点, 如果节点只有一个, 就删除桶并且把桶连接好
         * @param node
	     * @param nodeList
         * @author lihd
         * @date 2022/8/31 21:18
         */
        private void removeNodeFromBucket(Node<K, V> node, NodeList<K, V> nodeList) {
            nodeMap.remove(node.key);
            // 先把 这个元素 从桶中移除
            if (nodeList.head == nodeList.tail) {
                // 说明桶里面只有一个元素, 这个元素,就是我们要删除的元素
                // 因此要把这个桶删除
                nodeList.prev.next = nodeList.next;
                if (nodeList.next != null) {
                    nodeList.next.prev = nodeList.prev;
                }
                bucketMap.remove(node);
            } else {
                // 如果桶中有元素 说明最少两个元素
                // 这个元素 可能是头, 也可能是尾巴, 也可能在中间
                if (node == nodeList.head) {
                    // 说明是头
                    nodeList.head = node.next;
                    nodeList.head.prev = null;
                } else if (node == nodeList.tail) {
                    // 说明是尾
                    nodeList.tail = node.prev;
                    nodeList.tail.next = null;
                } else {
                    // 说明在中间
                    node.next.prev = node.prev;
                    node.prev.next = node.next;
                }

            }
        }

        /**
         * 添加元素的方法 , 只有在这里是添加元素
         * @param key 键
	     * @param val 值
         * @author lihd
         * @date 2022/8/31 21:17
         */
        private void add(K key, V val) {
            size++;
            Node<K, V> node = new Node<>(key, val);
            nodeMap.put(key, node);

            // 看看head桶下面是不是 head.
            if (head.next != null && head.next.head.times == node.times) {
                // 说明有这个桶 先找到这个桶，有桶就有元素
                NodeList<K, V> nodeList = head.next;
                // 把新来的元素添加到一个桶的头部
                node.next = nodeList.head;
                nodeList.head.prev = node;
                nodeList.head = node;

                bucketMap.put(node, nodeList);
            } else {
                // 说明没有 这个桶 由于是新增, 因此一定是第一个桶
                NodeList<K, V> newNodeList = new NodeList<>();
                if (head.next == null) {
                    head.next = newNodeList;
                    newNodeList.prev = head;
                } else {
                    head.next.prev = newNodeList;
                    newNodeList.next = head.next;
                    head.next = newNodeList;
                    newNodeList.prev = head;
                }
                newNodeList.head = node;
                newNodeList.tail = node;

                bucketMap.put(node, newNodeList);

            }
        }

        /**
         * 虽然是获取, 但是内部是update, 这是由于LFU置换算法导致的
         * @param key 键
         * @return V
         * @author lihd
         * @date 2022/8/31 21:16
         */
        public V get(K key) {
            if (!nodeMap.containsKey(key)) {
                return null;
            }
            // 说明真的有
            Node<K, V> node = nodeMap.get(key);
            // 这里调用更新, 只使用更新的副作用 : times ++;
            update(key, node.val);
            return node.val;
        }

        /**
         * 移除使用次数最少的, 使用次数一样少的, 移除最久没有使用的<br/>
         * 简单来说,就是删除第一个桶中的 tail
         *
         * @author lihd
         * @date 2022/8/31 17:26
         */
        private void removeByLFU() {
            // 既然满了, 那么下一个一定存在
            size--;
            NodeList<K, V> needToRemove = head.next;
            Node<K, V> tail = head.next.tail;
            removeNodeFromBucket(tail, head.next);
        }

    }


}
