package com.lihd.part03;

/**
 * 使用while循环 实现
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/2 21:56
 */
public class MergeSortIter extends BaseSort{

    //排序 一定要相信自己呀 连自己都不相信该怎么办
    public void mergeSort(int[] arr){
        int proportion = 1;
        int size = arr.length;
        while(proportion < size){
            int L = 0;
            while(L < size){
                int M = L + proportion - 1;
                if(M >= size) break;
                int R = Math.min(size - 1, M + proportion);
                merge(arr,L,R,M);
                L = R + 1;
            }
            if( size  >> 1 < proportion) break;
            proportion <<= 1;
        }


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
        mergeSort(arr);
    }

    @Override
    public void testSort() {
        super.testSort();
    }
}
