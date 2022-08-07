package com.lihd.exer;

import java.util.ArrayList;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 20:44
 */
public class SkipListNode<K extends Comparable<K>, V> {

    K key;
    V val;

    ArrayList<SkipListNode<K, V>> next;

    public SkipListNode(K key, V val) {
        this.key = key;
        this.val = val;
        next = new ArrayList<>();
    }


    public boolean isLess(K key) {
        if (this.key == null || key == null) {
            return true;
        }
        return this.key.compareTo(key) < 0;
    }

    public boolean isEqual(K key) {
        if (this.key == null && key == null) {
            return true;
        }
        if (this.key == null ^ key == null) {
            return false;
        }
        return this.key.compareTo(key) == 0;
    }

}
