package com.lihd.part05;

import java.util.HashMap;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/4 20:45
 */
public class Code04LRU {//Least Recently Used

    public static class Node<K, V>{
        K key;
        V val;
        Node<K, V> last;
        Node<K, V> next;


        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public static class DoublyLinkedList<K,V>{
        private Node<K, V> head;
        private Node<K, V> tail;

        public void add(Node<K, V> node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }
        //node 一定是 链表上存在的
        public void moveToTail(Node<K, V> node) {
            if (node == tail) {
                return;
            }
            if (node == head) {
                head = node.next;
                head.last = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            tail.next = node;
            node.last = tail;
            tail = node;
        }


        public Node<K, V> removeFromHead() {
            if (head == null) {
                return null;
            }

            Node<K, V> ans = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
                ans.next = null;
            }
            return ans;
        }

    }

    public static class LRU<K,V>{
        private int capacity;
        private HashMap<K, Node<K, V>> map = new HashMap<>();
        private DoublyLinkedList<K, V> linkedList = new DoublyLinkedList<>();

        public LRU(int capacity) {
            this.capacity = capacity;
        }

        public V get(K key) {
            if (map.containsKey(key)) {
                Node<K, V> node = map.get(key);
                linkedList.moveToTail(node);
                return node.val;
            }
            return null;

        }

        public void put(K key, V val) {
            if (map.containsKey(key)) {
                Node<K, V> node = map.get(key);
                linkedList.moveToTail(node);
                node.val = val;
            } else {
                //add 行为
                //如果达到容量，先删除
                if (map.size() == capacity) {
                    Node<K, V> node = linkedList.removeFromHead();
                    map.remove(node.key);
                }
                //添加
                Node<K, V> node = new Node<>(key, val);
                map.put(key, node);
                linkedList.add(node);
            }
        }
    }

    public static void main(String[] args) {
		LRU<String, Integer> testCache = new LRU<String, Integer>(3);
		testCache.put("A", 1);
		testCache.put("B", 2);
		testCache.put("C", 3);
		System.out.println(testCache.get("B"));
		System.out.println(testCache.get("A"));
		testCache.put("D", 4);
		System.out.println(testCache.get("D"));
		System.out.println(testCache.get("C"));

	}


}
