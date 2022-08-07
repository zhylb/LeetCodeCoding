package com.lihd.part07;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/14 9:30
 */
public class Code03CountOfRangeSum {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        int ans = 0;
        long sum = 0;
        SizeBalanceTree<Node, Object> sbt = new SizeBalanceTree<>();
        Object obj = new Object();
        sbt.put(new Node(0, -1), 0);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            long a = sbt.lessKeyNum(new Node(sum - lower + 1, i - 1));
            long b = sbt.lessKeyNum(new Node( sum - upper, i - 1));
            ans += (a - b);
            sbt.put(new Node(sum, i), obj);
        }
        return ans;
    }


    public static class Node implements Comparable<Node> {
        long val;
        int index;
        public Node(long val, int index) {
            this.val = val;
            this.index = index;
        }
        @Override
        public int compareTo(Node o) {
            return val == o.val ? Integer.compare(index,o.index) : Long.compare(val,o.val);
        }
    }


    public static class SizeBalanceNode<K extends Comparable<K>, V> {
        K key;
        V val;
        SizeBalanceNode<K, V> l;
        SizeBalanceNode<K, V> r;
        int size = 1;

        public SizeBalanceNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }





    public static class SizeBalanceTree<K extends Comparable<K>, V> {
        private SizeBalanceNode<K, V> root;

        private void resizeNode(SizeBalanceNode<K, V> node) {
            node.size = 1 + (node.l != null ? node.l.size : 0) + (node.r != null ? node.r.size : 0);
        }


        private SizeBalanceNode<K, V> leftRotate(SizeBalanceNode<K, V> node) {
            SizeBalanceNode<K, V> right = node.r;
            node.r = right.l;
            right.l = node;
            right.size = node.size;
            resizeNode(node);
            return right;
        }

        private SizeBalanceNode<K, V> rightRotate(SizeBalanceNode<K, V> node) {
            SizeBalanceNode<K, V> left = node.l;
            node.l = left.r;
            left.r = node;
            left.size = node.size;
            resizeNode(node);
            return left;
        }

        private SizeBalanceNode<K, V> maintain(SizeBalanceNode<K, V> node) {
            int l = node.l != null ? node.l.size : 0;
            int r = node.r != null ? node.r.size : 0;
            int ll = node.l != null && node.l.l != null ? node.l.l.size : 0;
            int lr = node.l != null && node.l.r != null ? node.l.r.size : 0;
            int rl = node.r != null && node.r.l != null ? node.r.l.size : 0;
            int rr = node.r != null && node.r.r != null ? node.r.r.size : 0;
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

            } else if (rl > l) {
                node.r = rightRotate(node.r);
                node = leftRotate(node);
                node.l = maintain(node.l);
                node.r = maintain(node.r);
                node = maintain(node);
            } else if (rr > l) {
                node = leftRotate(node);
                node.l = maintain(node.l);
                node = maintain(node);
            }
            return node;
        }

        private SizeBalanceNode<K, V> add(SizeBalanceNode<K, V> node, K key, V val) {
            if (node == null) {
                node = new SizeBalanceNode<>(key, val);
            } else {
                node.size ++;
                if (key.compareTo(node.key) > 0) {
                    node.r = add(node.r, key, val);
                } else {
                    node.l = add(node.l, key, val);
                }
            }
            return maintain(node);
        }

        private SizeBalanceNode<K, V> delete(SizeBalanceNode<K, V> node, K key) {
            node.size --;
            if (key.compareTo(node.key) > 0) {
                node.r = delete(node.r, key);
            } else if (key.compareTo(node.key) < 0) {
                node.l = delete(node.l, key);
            } else {

                if (node.r == null) {
                    node = node.l;
                }else if(node.l == null){
                    node = node.r;
                } else {
                    //这就是要删除的节点
                    SizeBalanceNode<K, V> successorNodePre = null;
                    SizeBalanceNode<K, V> successorNode = node.r;
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
                    resizeNode(successorNode);
                    node = successorNode;
                }

            }
            return node;
        }

        private SizeBalanceNode<K, V> getTheCorrectLocation(K key) {
            SizeBalanceNode<K, V> pre = root;
            SizeBalanceNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                if (cur.key.compareTo(key) == 0) {
                    return cur;
                } else if (key.compareTo(cur.key) > 0) {
                    cur = cur.r;
                } else {
                    cur = cur.l;
                }
            }
            return pre;
        }


        public boolean containsKey(K key) {
            SizeBalanceNode<K, V> node = getTheCorrectLocation(key);
            return node != null && node.key.compareTo(key) == 0;
        }

        public void put(K key, V val) {
            if (containsKey(key)) {
                SizeBalanceNode<K, V> node = getTheCorrectLocation(key);
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
            if (containsKey(key)) {
                return getTheCorrectLocation(key).val;
            }
            return null;
        }

        public int size() {
            return root != null ? root.size : 0;
        }

        private SizeBalanceNode<K, V> getIndex(SizeBalanceNode<K, V> node, int k) {
            int l = node.l != null ? node.l.size : 0;
            if (k  == l + 1) {
                return node;
            } else if (k <= l) {
                return getIndex(node.l, k);
            } else {
                return getIndex(node.r, k - l - 1);
            }
        }

        public K getIndexKey( int k) {
            return getIndex(root, k + 1).key;
        }

        public long lessKeyNum(K key) {
            SizeBalanceNode<K, V> cur = root;
            long ans = 0;
            while (cur != null) {
                if (cur.key.compareTo(key) == 0) {
                    return cur.l != null ? cur.l.size : 0;
                } else if (cur.key.compareTo(key) < 0) {

                    ans += cur.r != null ? cur.size - cur.r.size : cur.size;
                    cur = cur.r;
                } else {
                    cur = cur.l;
                }
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        int[] arr = {-2,5,-1};
        int i = countRangeSum(arr, -2, 2);
        System.out.println(i);
    }

}
