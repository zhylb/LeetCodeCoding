package com.lihd.part04;

import java.util.Comparator;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/3 18:48
 */
public class MyHeapSort{
    public static<T extends Comparable<T>> void sort(T[] arr){
        //复杂度O(N log N)
        for (int i = 0; i < arr.length; i++) {
            floatUp(arr,i);
        }
        //复杂度O(N log N)
        for (int i = arr.length - 1 ;i >= 0; i--) {
            swap(arr,0,i);
            sinkDown(arr,0,i);
        }
    }

    public static<T extends Comparable<T>> void improveSort(T[] arr){
        //复杂度 O(N) 可以通过数学证明
        for (int i = arr.length - 1 ;i >= 0; i--) {
            sinkDown(arr,i, arr.length);//我们先假设数组 全部是一个堆 倒着来 由于全部是一个堆 因此 heapSize = length 千万不要减一
        }
        //复杂度O(N log N)
        for (int i = arr.length - 1 ;i >= 0; i--) {
            swap(arr,0,i);
            sinkDown(arr,0,i);
        }
    }

    public static<T extends Comparable<T>> void improveSort(int[] arr){
        Integer[] copyArr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        improveSort(copyArr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = copyArr[i];
        }
    }


    public static<T extends Comparable<T>> void sinkDown(T[] arr,int nodeIndex,int heapSize){
        int leftIndex = leftSonIndex(nodeIndex);
        while(leftIndex < heapSize){
            //说明有左孩子
            //找到最大孩子的索引
            int maxIndex = leftIndex + 1 < heapSize && arr[leftIndex + 1].compareTo(arr[leftIndex]) > 0 ? leftIndex + 1 : leftIndex;
            if(arr[nodeIndex].compareTo(arr[maxIndex]) >= 0){
                //如果父节点比 两个孩子都大 或者相等 就停止
                break;
            }
            //交换具体数组中的值
            swap(arr,maxIndex,nodeIndex);
            //重新赋值
            nodeIndex = maxIndex;
            leftIndex = leftSonIndex(nodeIndex);
        }
    }
    public static<T extends Comparable<T>> void floatUp(T[] arr,int heapSize){
        int index = heapSize;
        while(arr[index].compareTo(arr[fatherIndex(index)]) > 0 ){//子节点比父节点大
            swap(arr,index,fatherIndex(index));
            index = fatherIndex(index);
        }
    }
    public static int fatherIndex(int index){
        return (index - 1)/2;
    }
    public static int leftSonIndex(int index){
        return index * 2 + 1;
    }
    public static<T extends Comparable<T>> void swap(T[] arr,int i, int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
