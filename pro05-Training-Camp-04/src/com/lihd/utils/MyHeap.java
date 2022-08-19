package com.lihd.utils;

import java.util.HashMap;

/**
 * 大根堆
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/18 13:20
 */
public class MyHeap<T extends Comparable<T>> {

    T [] arr;
    HashMap<T, Integer> indexMap;
    int size;

    // 初始化
    public MyHeap(int maxSize) {
        arr = (T[]) new Comparable[maxSize];
        indexMap = new HashMap<>();
        size = 0;
    }

    public void add(T ele) {
        arr[size] = ele;
        indexMap.put(ele, size);
        heapfiy(size);
        size ++;
    }

    public T poll() {
        T ret = arr[0];
        swap(0, size - 1);
        arr[size--] = null;
        indexMap.remove(ret);
        heapInsert(0);
        return ret;
    }


    private void heapfiy(int index) {
        int fatherIndex = (index - 1) / 2;
        // 当子节点比父节点 大 , 需要调整
        while (arr[index].compareTo(arr[fatherIndex]) > 0) {
            swap(index, fatherIndex);
            index = fatherIndex;
            fatherIndex = (index - 1) / 2;
        }
    }

    private void heapInsert(int index) {
        int leftIndex = (index * 2) + 1;
        while (leftIndex < size) {
            //最大的一个孩子的索引
            int maxIndex = leftIndex + 1 < size && arr[leftIndex + 1].compareTo(arr[leftIndex]) > 0 ? leftIndex + 1 : leftIndex;
            if (arr[maxIndex].compareTo(arr[index]) <= 0) {
                // 最大的都比不过父亲
                break;
            }
            swap(maxIndex, index);
            index = maxIndex;
            leftIndex = index * 2 + 1;
        }
    }


    private void swap(int i, int j) {



        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;


        indexMap.put(arr[i], i);
        indexMap.put(arr[j], j);
    }











}
