package com.lihd.part03;

import org.junit.Test;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/2 20:58
 */
public class MergeSort extends BaseSort{
    public void mergeSort(int[] arr,int L,int R){
        if(L >= R) return;
        int mid = L + ((R - L)>>2);//记得 加上括号
        mergeSort(arr,L,mid);
        mergeSort(arr,mid + 1,R);
        merge(arr,L,R,mid);
    }
    //归并过程
    public void merge(int arr[],int L,int R,int M){
        int[] help = new int[R - L + 1];
        int leftI = L;
        int rightI = M + 1;
        int helpI = 0;
        while(leftI <= M && rightI <= R){
            help[helpI++] = arr[leftI] <= arr[rightI] ? arr[leftI ++] : arr[rightI ++];
        }
        //两个中有且仅有一个 越界
        //意思是下面都两个while 有且仅有一个会执行 因此两个的顺序随便写 因为只会执行一个
        while(leftI <= M){
            help[helpI++] = arr[leftI++];
        }
        while (rightI <= R){
            help[helpI++] = arr[rightI++];
        }
        //复制数组
        for (int i = 0; i < help.length; i++) {
            //记得 arr里面 + L
            arr[i + L] = help[i];
        }
    }

    @Override
    public void sort(int[] arr) {
        mergeSort(arr,0,arr.length - 1);
    }

    @Override
    @Test
    public void testSort() {
        super.testSort();
    }
}
