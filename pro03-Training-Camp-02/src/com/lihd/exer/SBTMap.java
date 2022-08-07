package com.lihd.exer;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 21:43
 */
public class SBTMap<K extends Comparable<K>, V> {
    private SBTNode<K, V> root;

    private SBTNode<K, V> rightRotate(SBTNode<K, V> node) {
        SBTNode<K, V> left = node.l;
        node.l = left.r;
        left.r = node;
        //调整节点个数
        left.size = node.size;
        node.size = 1 + (node.l == null ? 0 : node.l.size) + (node.r == null ? 0 : node.r.size);
        return left;
    }

    private SBTNode<K, V> leftRotate(SBTNode<K, V> node) {
        SBTNode<K, V> right = node.r;

        node.r = right.l;
        right.l = node;

        //调整节点个数
        right.size = node.size;
        node.size = 1 + (node.l == null ? 0 : node.l.size) + (node.r == null ? 0 : node.r.size);
        return right;
    }

    private SBTNode<K, V> maintain(SBTNode<K, V> node) {

        int l = 0;
        int r = 0;
        int ll = 0;
        int rr = 0;
        int lr = 0;
        int rl = 0;
        if (node.l != null) {
            l = node.l.size;
            if (node.l.l != null) {
                ll = node.l.l.size;
            }
            if (node.l.r != null) {
                lr = node.l.r.size;
            }
        }
        if (node.r != null) {
            r = node.r.size;
            if (node.r.l != null) {
                rl = node.r.l.size;
            }
            if (node.r.r != null) {
                rr = node.r.r.size;
            }
        }

        if (ll > r) {
            node = rightRotate(node);
            node.r = maintain(node.r);
            node = maintain(node);
        } else if (lr > r) {
            node.l = leftRotate(node.l);
            node = rightRotate(node);
            node.l = maintain(node.l);
            node.r = maintain(node.r);
            node = maintain(node);
        } else if (rr > l) {
            node = leftRotate(node);
            node.l = maintain(node.l);
            node = maintain(node);
        } else if (rl > l) {
            node.r = rightRotate(node.r);
            node = leftRotate(node);
            node.l = maintain(node.l);
            node.r = maintain(node.r);
            node = maintain(node);
        }
        return node;
    }

    private SBTNode<K, V> add(SBTNode<K, V> node, K key, V val) {
        if (node == null) {
            return new SBTNode<>(key, val);
        } else {
            node.size++;
            if (key.compareTo(node.key) < 0) {
                //当前key比较小
                node.l = add(node.l, key, val);
            } else {
                node.r = add(node.r, key, val);
            }
            return maintain(node);
        }
    }


    private SBTNode<K, V> delete(SBTNode<K, V> node, K key) {
        node.size --;
        //删除时 节点是一定存在的 否则不会调用此方法
        if (key.compareTo(node.key) < 0) {
            node.l = delete(node.l, key);
        } else if (key.compareTo(node.key) > 0) {
            node.r = delete(node.r, key);
        } else {
            //要删除的节点就是当前节点。
            if (node.r == null) {
                node = node.l;
            } else if (node.l == null) {
                node = node.r;
            } else {
                //有两个孩子
                SBTNode<K, V> successorNodePre = null;
                SBTNode<K, V> successorNode = node.r;
                successorNode.size --;
                while (successorNode.l != null) {
                    successorNodePre = successorNode;
                    successorNode = successorNode.l;
                    successorNode.size --;
                }
                if (successorNodePre != null) {
                    successorNodePre.l = successorNode.r;
                    successorNode.r = node.r;
                }
                successorNode.l = node.l;
                node = successorNode;
                node.size = 1 + (node.l.size) + (node.r == null ? 0 : node.r.size);
            }
        }
//        node = maintain(node);
        return node;
    }

    /**
     * 错误日志 ： 把下面的向左向有的判断写反了，导致全局错误。
     * @param key
     * @return com.lihd.exer.SBTNode<K,V>
     * @author lihd
     * @creed: 春春的飞舞
     * @date 2022/6/6 23:55
     */
    private SBTNode<K, V> getTheCorrectLocation(K key) {
        SBTNode<K, V> pre = root;
        SBTNode<K, V> cur = root;
        while (cur != null) {
            pre = cur;
            if (key.compareTo(cur.key) < 0) {
                cur = cur.l;
            } else if (key.compareTo(cur.key) > 0) {
                cur = cur.r;
            } else {
                return cur;
            }
        }
        return pre;

    }

    public boolean containsKey(K key) {
        SBTNode<K, V> node = getTheCorrectLocation(key);
        return node != null && node.key.compareTo(key) == 0;
    }

    public void put(K key, V val) {
        SBTNode<K, V> node = getTheCorrectLocation(key);
        if (node != null && node.key.compareTo(key) == 0) {
            node.val = val;
        } else {
            root = add(root, key, val);
        }
    }

    public void remove(K key) {
        if (containsKey(key)) {
            root = delete(root, key);
        }
    }

    public V get(K key) {
        SBTNode<K, V> node = getTheCorrectLocation(key);
        return node != null && node.key.compareTo(key) == 0 ? node.val : null;
    }



    public int size() {
        return root != null ? root.size : 0;
    }




}
