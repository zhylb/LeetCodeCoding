package com.lihd.part04;

import java.util.HashMap;

/**
 * 实现一个更新
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/3 20:48
 */
public class MyChangeableHeap<T extends Comparable<T>>{

    
    int size;
    int heapSize;
    T[] arr;
    HashMap<T,Integer> indexMap;

    public MyChangeableHeap(int size) {
        this.size = size;
        arr = (T[]) new Comparable[size];
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
    }


    public void add(T ele){
        arr[heapSize] = ele;
        indexMap.put(ele,heapSize);
        floatUp(heapSize);
        heapSize ++;
    }

    public T poll(){
        T ret = arr[0];
        indexMap.remove(ret);
        swap(0,heapSize);
        heapSize --;
        sinkDown(0);
        return ret;
    }


    public void floatUp(int nodeIndex){
        while(arr[nodeIndex].compareTo(arr[fatherIndex(nodeIndex)]) > 0){//哪怕是条件要求这里都不能带等号
            //子节点比父节点大
            swap(nodeIndex,fatherIndex(nodeIndex));
            //子节点变成父节点
            nodeIndex = fatherIndex(nodeIndex);
        }
    }

    public void sinkDown(int nodeIndex){

        int leftIndex = leftSonIndex(nodeIndex);
        while(leftIndex < heapSize){//这个heapSize应该是交换完 改变好边界
            //满足 有右孩子 并且右孩子大于左孩子 使用右孩子
            int maxIndex = leftIndex + 1 < heapSize && arr[leftIndex + 1].compareTo(arr[leftIndex]) > 0 ? leftIndex + 1: leftIndex;
            if(arr[maxIndex].compareTo(arr[nodeIndex]) <= 0){
                //最大的比不过 父节点 break
                break;
            }
            //交换真实的节点
            swap(maxIndex,nodeIndex);
            //重新赋值
            nodeIndex = maxIndex;
            leftIndex = leftSonIndex(nodeIndex);
        }

    }
    

    //ele 发生了改变 请你更新
    public void refactor(T ele){
        //元素都改 了 indexMap还能获取到吗 ?
        Integer index = indexMap.get(ele);
        floatUp(index);
        sinkDown(index);
    }

    public void refactor(T oldVal,T newVal){
        Integer removeIndex = indexMap.remove(oldVal);
        floatUp(removeIndex);
        sinkDown(removeIndex);
        indexMap.put(newVal,removeIndex);
    }

    public int fatherIndex(int nodeIndex){
        return (nodeIndex - 1) / 2;
    }
    public int leftSonIndex(int nodeIndex){
        return nodeIndex * 2 + 1;
    }

    //这里就别整花活了
    public void swap(int i, int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        //此时 arr[i] arr[j]已经交换
        indexMap.put(arr[i],i);
        indexMap.put(arr[j],j);
    }
}
