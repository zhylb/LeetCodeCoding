package com.lihd.exer;

import java.util.logging.Level;

/**
 * 错误笔记 1 比较isEqual时 在contains 和 get时 少些了 .next.get(0) 导致错误
 * 错误笔记 2 put时，修改的值应该是 getTheRightNodeOfTheZeroLevel 正确的应该是修改他的下一个节点
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 20:48
 */
public class SkipListMap<K extends Comparable<K> , V> {

    private static final double POSSIBILITY = 0.5;
    private final SkipListNode<K, V> head;
    private int size;
    private int maxLevel;

    public SkipListMap() {
        this.head = new SkipListNode<>(null, null);
        //添加这个null 必不可少
        head.next.add(null);
    }


    public V remove(K key) {
        V ans = null;
        if (containsKey(key)) {
            size --;
            int level = maxLevel;
            SkipListNode<K,V> cur = head;
            while (level >= 0) {
                cur = getTheRightNodeOfTheCurrentLevel(cur, key, level);
                if (cur.next.get(level) != null && cur.next.get(level).isEqual(key)) {
                    //不能是 cur是空的时候删除，因为那样就没办法删除了
                    ans = cur.next.get(level).val;
                    cur.next.set(level, cur.next.get(level).next.get(level));
                }

                if (level != 0 && cur == head && cur.isEqual(null)) {
                    head.next.remove(level);
                    maxLevel --;
                }
                level --;
            }
        }
        return ans;
    }


    public void put(K key, V val) {
        if (containsKey(key)) {
            SkipListNode<K, V> node = getTheRightNodeOfTheZeroLevel(key);
            node.next.get(0).val = val;
        } else {
            //新增节点
            size ++;
            int addLevel = 0;
            while (Math.random() < POSSIBILITY) {
                addLevel ++;
            }

            while (addLevel > maxLevel) {
                head.next.add(null);
                maxLevel ++;
            }

            SkipListNode<K, V> addNode = new SkipListNode<>(key, val);
            for (int i = 0; i <= addLevel; i++) {
                addNode.next.add(null);
            }

            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                cur = getTheRightNodeOfTheCurrentLevel(cur, key, level);
                if (addLevel >= level) {
                    addNode.next.set(level, cur.next.get(level));
                    cur.next.set(level, addNode);
                }
                level --;
            }
        }
    }

    public V get(K key) {
        SkipListNode<K, V> node = getTheRightNodeOfTheZeroLevel(key);
        return node.next.get(0) != null && node.next.get(0).isEqual(key) ? node.next.get(0).val : null;
    }


    public boolean containsKey(K key) {
        SkipListNode<K, V> node = getTheRightNodeOfTheZeroLevel(key);
        return node.next.get(0) != null && node.next.get(0).isEqual(key);
    }

    public SkipListNode<K, V> getTheRightNodeOfTheZeroLevel(K key) {
        SkipListNode<K, V> cur = head;
        int level = maxLevel;
        while (level >= 0) {
            cur = getTheRightNodeOfTheCurrentLevel(cur, key, level);
            level --;
        }
        return cur;
    }

    public SkipListNode<K, V> getTheRightNodeOfTheCurrentLevel(SkipListNode<K, V> cur, K key, int level) {
        SkipListNode<K, V> next = cur.next.get(level);
        while (next != null && next.isLess(key)) {
            cur = next;
            next = next.next.get(level);
        }
        return cur;
    }

    public K firstKey() {
        return head.next.get(0) != null ? head.next.get(0).key : null;
    }

    public K lastKey() {
        return getTheRightNodeOfTheZeroLevel(null).key;
    }

    public K ceilingKey(K key) {
        SkipListNode<K, V> node = getTheRightNodeOfTheZeroLevel(key);
        SkipListNode<K, V> next = node.next.get(0);
        if (next == null ) {
            return null;
        }
        return next.key;
    }

    public K floorKey(K key) {
        SkipListNode<K, V> node = getTheRightNodeOfTheZeroLevel(key);
        SkipListNode<K, V> next = node.next.get(0);
        if (next == null ) {
            return node.key;
        }
        if (node.isEqual(next.key)) {
            return next.key;
        }
        return node.key;
    }

    public int size() {
        return size;
    }


}
