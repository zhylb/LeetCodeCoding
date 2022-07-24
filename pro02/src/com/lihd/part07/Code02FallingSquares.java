package com.lihd.part07;

import java.util.*;

/**
 * 一次过，居然一点错也没出，看来编程能力在逐步提升
 * Runtime: 14 ms, faster than 96.35% of Java online submissions for Falling Squares.
 * Memory Usage: 42.8 MB, less than 98.96% of Java online submissions for Falling Squares.
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/1 16:27
 */
public class Code02FallingSquares {


    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> list = new ArrayList<>(positions.length);
        HashMap<Integer, Integer> map = compress(positions);
        SegmentTree segmentTree = new SegmentTree(map.size());

        int max = 0;
        for (int i = 0; i < positions.length; i++) {
            int L = map.get(positions[i][0]);
            int R = map.get(positions[i][1] + positions[i][0] - 1);
            int height = segmentTree.query(L, R) + positions[i][1];
            max = Math.max(height, max);
            list.add(max);
            segmentTree.update(L, R, height);
        }

        return list;
    }


    public static HashMap<Integer, Integer> compress(int[][] positions) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int[] arr : positions) {
            treeSet.add(arr[0]);
            treeSet.add(arr[1] + arr[0] - 1);
        }

        int index = 1;
        HashMap<Integer, Integer> map = new HashMap<>(positions.length);
        for (Integer integer : treeSet) {
            map.put(integer, index++);
        }
        return map;
    }

    public static class SegmentTree{
        int size;
        int[] max;
        int[] updateLazy;
        boolean[] isUpdateLazy;
        public SegmentTree(int size) {
            this.size = size +  1;
            int extendSize = size << 2;
            max = new int[extendSize];
            updateLazy = new int[extendSize];
            isUpdateLazy = new boolean[extendSize];
        }


        public void pushUp(int index) {
            max[index] = Math.max(max[index << 1], max[index << 1 | 1]);
        }

        public void pushDown(int index) {
            if (isUpdateLazy[index]) {
                int left = index << 1;
                int right = index << 1 | 1;
                isUpdateLazy[left] = true;
                isUpdateLazy[right] = true;
                updateLazy[left] = updateLazy[index];
                updateLazy[right] = updateLazy[index];
                max[left] = updateLazy[index];
                max[right] = updateLazy[index];
                isUpdateLazy[index] = false;
            }
        }


        public void update(int L, int R, int V, int l, int r, int index) {
            if (L <= l && r <= R) {
                isUpdateLazy[index] = true;
                updateLazy[index] = V;
                max[index] = V;
            } else {
                pushDown(index);
                int mid = ((r - l) >> 1) + l;
                if (L <= mid) {
                    update(L, R, V, l, mid, index << 1);
                }
                if (R > mid) {
                    update(L, R, V, mid + 1, r, index << 1 | 1);
                }
                pushUp(index);
            }
        }

        public int query(int L, int R, int l, int r, int index) {
            if (L <= l && r <= R) {
                return max[index];
            } else {
                pushDown(index);
                int mid = ((r - l) >> 1) + l;
                int max = 0;
                if (L <= mid) {
                    max = Math.max(query(L, R, l, mid, index << 1),max);
                }
                if (R > mid) {
                    max = Math.max(query(L, R, mid + 1, r, index << 1 | 1),max);
                }
                pushUp(index);
                return max;
            }
        }

        public int query(int L, int R) {
            return query(L, R, 1, size - 1, 1);
        }

        public void update(int L, int R, int V) {
            update(L, R, V, 1,size - 1, 1);
        }
    }


}
