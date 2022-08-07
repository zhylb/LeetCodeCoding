package com.lihd.part05;

import java.util.ArrayList;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 17:43
 */
public class SkipListNode <K extends Comparable<K>, V>{
    public K key;
    public V val;
    ArrayList<SkipListNode<K,V>> next;

    public SkipListNode(K key, V val) {
        this.key = key;
        this.val = val;
        next = new ArrayList<>();
    }

    public int compare(SkipListNode<K, V> other, int level) {
        if (other == null) {
            return -1;
        }
        return this.next.get(level).key.compareTo(other.next.get(level).key);
    }

    public int compareTo(K other) {
        if (other == null) {
            return -1;
        }
        return this.key.compareTo(other);
    }
}
