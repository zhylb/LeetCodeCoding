package com.lihd.part07;

import java.util.HashSet;

/**
 * Runtime: 386 ms, faster than 27.67% of Java online submissions for Count of Range Sum.
 * Memory Usage: 146.1 MB, less than 11.42% of Java online submissions for Count of Range Sum.
 * 这个勾八题是真恶心
 * 妈的 写一写错误日记
 * 首先我认为不需要那个set,然后就发现如果这个值增加过，add（）方法无脑让其size + 1是不可取的，这个set似乎是必须的
 * 然后就是左旋右旋小细节写错了 ，多些了个.l, .r
 * 最后是 all值的更新，天真的认为all的更新是左孩子 + 右孩子 + 1，但是这怎么可能吗！
 * 思路不是自己的算了，里面的细节还非常复杂，如果粗心指定是不行了。
 * 改结构并不是容易的事情。或许可以改造
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/8 17:18
 */
public class Code01CountOfRangeSum {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        int ans = 0;
        long sum = 0;
        SBTSet treeSet = new SBTSet();
        treeSet.add(0);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // [lower, upper]
            //[sum - upper, sum - lower]
            long a = treeSet.countOfLessNum(sum - lower + 1);
            long b = treeSet.countOfLessNum(sum - upper);
            ans += (a - b);
            treeSet.add(sum);
        }

        return ans;
    }

    public static class SBTNode {
        long key;
        SBTNode l;
        SBTNode r;
        long size = 1;
        long all = 1;

        public SBTNode(long key) {
            this.key = key;
        }
    }

    public static class SBTSet {
        private SBTNode root;
        private HashSet<Long> containsSet = new HashSet<>();

        private void resizeNodeSize(SBTNode node) {
            node.size =  1 + (node.l == null ? 0 : node.l.size) + (node.r == null ? 0 : node.r.size);
        }

        private void resizeNodeAll(SBTNode node) {
            node.all =  (node.l == null ? 0 : node.l.all) + (node.r == null ? 0 : node.r.all);
        }

        private SBTNode rightRotate(SBTNode node) {
            long same = node.all - (node.l == null ? 0 : node.l.all) - (node.r == null ? 0 : node.r.all);
            SBTNode left = node.l;
            node.l = left.r;
            left.r = node;
            left.size = node.size;
            left.all = node.all;
            resizeNodeAll(node);
            resizeNodeSize(node);
            node.all += same;
            return left;
        }

        private SBTNode leftRotate(SBTNode node) {
            long same = node.all - (node.l == null ? 0 : node.l.all) - (node.r == null ? 0 : node.r.all);

            SBTNode right = node.r;
            node.r = right.l;
            right.l = node;
            right.size = node.size;
            right.all = node.all;
            resizeNodeAll(node);
            resizeNodeSize(node);
            node.all += same;
            return right;
        }

        private SBTNode maintain(SBTNode node) {
            long l = node.l != null ? node.l.size : 0;
            long r = node.r != null ? node.r.size : 0;
            long ll = node.l != null && node.l.l != null ? node.l.l.size : 0;
            long lr = node.l != null && node.l.r != null ? node.l.r.size : 0;
            long rl = node.r != null && node.r.l != null ? node.r.l.size : 0;
            long rr = node.r != null && node.r.r != null ? node.r.r.size : 0;
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

        private SBTNode add(SBTNode node, long key, boolean contains) {
            if (node == null) {
                node = new SBTNode(key);
            } else {
                node.all ++;

                if (key == node.key ) {
                    return node;
                }
                if (!contains) {
                    node.size ++;
                }

                if (key < node.key) {
                    node.l = add(node.l, key, contains);
                } else {
                    node.r = add(node.r, key, contains);
                }
            }
            return maintain(node);
        }

        public void add(long key) {
            boolean contains = containsSet.contains(key);
            root = add(root, key, contains);
            containsSet.add(key);
        }


        public long countOfLessNum(long key) {
            SBTNode cur = root;
            long ans = 0;
            while (cur != null) {
                if (key == cur.key) {
                    return ans + (cur.l != null ? cur.l.all : 0);
                } else if (key < cur.key) {
                    cur = cur.l;
                } else {
                    ans += cur.all - (cur.r != null ? cur.r.all : 0);
                    cur = cur.r;
                }
            }
            return ans;
        }

    }


    public static void main(String[] args) {
        //[2147483647,-2147483648,-1,0]
        //-1
        //0
//        SBTSet sbtSet = new SBTSet();
//        sbtSet.add(2147483647);
//        sbtSet.add(-2147483648);
//        sbtSet.add(-1);
//        sbtSet.add(0);
//
//        System.out.println(sbtSet.countOfLessNum(6));
//        System.out.println(sbtSet.countOfLessNum(7));
//        System.out.println(sbtSet.countOfLessNum(8));
//        System.out.println(sbtSet.countOfLessNum(9));
//        System.out.println(sbtSet.countOfLessNum(10));

        int[] arr = {-2,2,-2,-3,2,-2};
        int i = countRangeSum(arr, -3, 1);
        System.out.println(i);
    }





}
