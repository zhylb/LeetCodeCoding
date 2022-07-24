package com.lihd.part04;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/3 17:03
 */
public class MyHeap<T extends Comparable<T>>{

    int size;
    T[] arr;
    int heapSize;

    public MyHeap(int size) {
        this.size = size;
        arr = (T[]) new Comparable[size];
        this.heapSize = 0;
    }

    public void add(T ele){
        if(isEmpty()){
            arr[heapSize++] = ele;
        } else {
            arr[heapSize] = ele;
            floatUp();
            heapSize ++;
        }
    }
    public T poll(){
        T ret = arr[0];
        swap(0,--heapSize);
        sinkDown(0);
        return ret;
    }

    public void sinkDown(int nodeIndex){

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
            swap(maxIndex,nodeIndex);

            //重新赋值
            nodeIndex = maxIndex;
            leftIndex = leftSonIndex(nodeIndex);
        }
    }

    public void floatUp(){
        int index = heapSize;
        while(arr[index].compareTo(arr[fatherIndex(index)]) > 0 ){//子节点比父节点大
            swap(index,fatherIndex(index));
            index = fatherIndex(index);
        }
    }

    public int fatherIndex(int index){
        return (index - 1)/2;
    }

    public int leftSonIndex(int index){
        return index * 2 + 1;
    }
    public boolean isFull(){
        return heapSize == size;
    }
    public boolean isEmpty(){
        return heapSize == 0;
    }
    public void swap(int i, int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
