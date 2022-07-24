package com.lihd.exer;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 21:41
 */
public class SBTNode<K extends Comparable<K>, V> {
    K key;
    V val;
    int size;

    SBTNode<K, V> l;
    SBTNode<K, V> r;

    public SBTNode(K key, V val) {
        this.key = key;
        this.val = val;
        this.size = 1;
    }
}
