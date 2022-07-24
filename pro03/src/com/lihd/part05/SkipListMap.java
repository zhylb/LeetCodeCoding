package com.lihd.part05;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 17:47
 */
public class SkipListMap<K extends Comparable<K>, V > {

    private static final double POSSIBILITY = 0.5;

    private SkipListNode<K, V> head;

    private int maxLevel;

    private int size;

    public SkipListMap() {
        this.head = new SkipListNode<>(null,null);
        head.next.add(null);
        this.maxLevel = 0;
        this.size = 0;
    }


    public void remove(K key) {
        if (containsKey(key)) {
            size --;
            SkipListNode<K,V> cur = head;
            int level = this.maxLevel;
            while (level >= 0) {
                cur = findTheCurrentLevelRNode(cur, key, level);

                if (cur.next.get(level) != null && cur.next.get(level).compareTo(key) == 0) {
                    cur.next.set(level, cur.next.get(level).next.get(level));
                }

                if (level != 0 && cur == head && cur.next.get(level) == null) {
                    head.next.remove(level);
                    this.maxLevel--;
                }
                level --;
            }

        }
    }


    public V get(K key) {
        if (containsKey(key)) {//有重复计算就不管了
            return findTheRNodeAtLevel0(key).next.get(0).val;
        }
        return null;
    }


    public void put(K key, V val) {
        if (containsKey(key)) {
            //说明是修改
            SkipListNode<K, V> find = findTheRNodeAtLevel0(key);
            find.next.get(0).val = val;
        } else {
            //说明是添加
            size ++;
            int addLevel = 0;
            while (Math.random() < POSSIBILITY) {
                addLevel++;
            }
            //看一看全局用不用更新
            while (addLevel > this.maxLevel) {
                head.next.add(null);
                this.maxLevel++;
            }

            SkipListNode<K, V> addNode = new SkipListNode<>(key, val);

            for (int i = 0; i <= addLevel; i++) {
                addNode.next.add(null);
            }

            SkipListNode<K,V> cur = head;
            int level = maxLevel;
            while (level >= 0) {
                cur = findTheCurrentLevelRNode(cur, key, level);
                if (level <= addLevel) {
                    addNode.next.set(level, cur.next.get(level));
                    cur.next.set(level, addNode);
                }
                level--;
            }
        }
    }

    public boolean containsKey(K key) {
        SkipListNode<K, V> find = findTheRNodeAtLevel0(key);
        return find.next.get(0) != null && find.next.get(0).compareTo(key) == 0;
    }

    private SkipListNode<K,V> findTheRNodeAtLevel0(K key) {
        SkipListNode<K,V> cur = head;
        int level = this.maxLevel;
        while (level >= 0) {
            cur = findTheCurrentLevelRNode(cur, key, level);
            level --;
        }
        return cur;
    }


    private SkipListNode<K,V> findTheCurrentLevelRNode(SkipListNode<K,V> cur, K key, int level) {
        SkipListNode<K,V> last = cur;
        SkipListNode<K, V> next = last.next.get(level);
        while (next != null && next.compareTo(key) < 0) {
            last = next;
            next = next.next.get(level);
        }
        return last;
    }

    public int size() {
        return this.size;
    }

    public K firstKey() {
        return head.next.get(0) != null ? head.next.get(0).key : null;
    }

    public K lastKey() {

        return findTheRNodeAtLevel0(null).key;
    }




}
