package com.lihd.exer01;

import java.util.Comparator;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/19 22:21
 */
public class Heap<T extends Comparable<T>> {

    T[] arr;
    int size;

    public Heap(int maxSize) {
        arr = (T[]) new Comparable[maxSize];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int i , int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void insertHeapify(int index) {
        //满足当前节点比 父节点小
        while (arr[index].compareTo(arr[(index - 1) / 2]) < 0) {
            swap(index, (index - 1)/2);
            index = (index - 1)/2;
        }
    }

    public void add(T t) {
        arr[size] = t;
        insertHeapify(size);
        size ++;
    }

    public void addAll(T[] arr) {
        if (!isEmpty()) {
            throw new RuntimeException("addAll方法需要满足 里面没有元素");
        }

        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
        size = arr.length;

        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(i, arr.length);
        }

    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        size --;
        T ans = arr[0];
        swap(0, size);
        heapify(0, size);
        return ans;
    }


    private void heapify(int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            //有左孩子
            int minIndex = left + 1 < size && arr[left].compareTo(arr[left + 1]) > 0 ? left + 1 : left;
            //最小的孩子比我大
            if (arr[minIndex].compareTo(arr[index]) >= 0) {
                break;
            }
            //最小的孩子比我小 和这个孩子交换
            swap(index, minIndex);
            index = minIndex;
            left = index * 2 + 1;
        }
    }

}
