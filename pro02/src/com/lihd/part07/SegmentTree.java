package com.lihd.part07;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/1 11:24
 */
public class SegmentTree {
    private int size;
    private final int[] arr;
    private final int[] sum;
    private final int[] addLazy;
    private final int[] updateLazy;
    private final boolean[] isUpdateLazy;

    public SegmentTree(int[] a) {
        int size = a.length + 1;
        arr = new int[size];
        for (int i = 0; i < a.length; i++) {
            arr[i + 1] = a[i];
        }
        int extendSize = size << 2;
        sum = new int[extendSize];
        addLazy = new int[extendSize];
        updateLazy = new int[extendSize];
        isUpdateLazy = new boolean[extendSize];

    }

    public void build(int l, int r, int index) {
        if (l == r) {
            sum[index] = arr[l];
        } else {
            int mid = l + ((r - l) >> 1);
            build(l, mid, index << 1);
            build(mid + 1, r, index << 1 | 1);
            pushUp(index);
        }
    }

    public void pushUp(int index) {
        sum[index] = sum[index << 1] + sum[index << 1 | 1];
    }

    public void pushDown(int index, int leftNum, int rightNum) {
        if (isUpdateLazy[index]) {
            isUpdateLazy[index << 1] = true;
            isUpdateLazy[index << 1 | 1] = true;

            updateLazy[index << 1] = updateLazy[index];
            updateLazy[index << 1 | 1] = updateLazy[index];

            addLazy[index << 1] = 0;
            addLazy[index << 1 | 1] = 0;

            sum[index << 1] = leftNum * updateLazy[index];
            sum[index << 1 | 1] = rightNum * updateLazy[index];

//            updateLazy[index] = 0;
            isUpdateLazy[index] = false;
        }

        if (addLazy[index] != 0) {
            addLazy[index << 1] += addLazy[index];
            addLazy[index << 1 | 1] += addLazy[index];

            sum[index << 1] += addLazy[index] * leftNum;
            sum[index << 1 | 1] += addLazy[index] * rightNum;

            addLazy[index] = 0;
        }
    }


    public void add(int L, int R, int V, int l, int r, int index) {
        if (L <= l && r <= R) {
            sum[index] +=  V * (r - l + 1);
            addLazy[index] += V;
        } else {
            int mid = ((r - l) >> 1) + l;
            pushDown(index,mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, V, l, mid, index << 1);
            }
            if (R > mid) {
                add(L, R, V, mid + 1, r, index << 1 | 1);
            }
            pushUp(index);
        }
    }

    public void update(int L, int R, int V, int l, int r, int index) {
        if (L <= l && r <= R) {
            sum[index] = V * (r - l + 1);
            addLazy[index] = 0;
            isUpdateLazy[index] = true;
            updateLazy[index] = V;
        } else {
            int mid = ((r - l) >> 1) + l;
            pushDown(index,mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, V, l, mid, index << 1);
            }
            if (R > mid) {
                update(L, R, V, mid + 1, r, index << 1 | 1);
            }
            pushUp(index);
        }
    }

    public long query(int L, int R, int l, int r, int index) {
        if (L <= l && r <= R) {
            return sum[index];
        } else {
            int mid = ((r - l) >> 1) + l;
            pushDown(index,mid - l + 1, r - mid);
            long ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, index << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, index << 1 | 1);
            }
            //为什么汇总不需要 pushUp
            pushUp(index);
            return ans;
        }
    }

    public long query(int L, int R) {
        if (R > size || L > R || L < 0) {
            throw new RuntimeException("范围越界");
        }
        return query(L, R, 1, size - 1, 1);
    }

    public void add(int L, int R, int V) {
        if (R > size || L > R || L < 0) {
            throw new RuntimeException("范围越界");
        }
        add(L, R, V, 1, size - 1, 1);
    }

    public void update(int L, int R, int V) {
        if (R > size || L > R || L < 0) {
            throw new RuntimeException("范围越界");
        }
        update(L, R, V, 1, size - 1, 1);
    }
}
