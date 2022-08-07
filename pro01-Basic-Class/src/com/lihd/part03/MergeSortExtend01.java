package com.lihd.part03;

import org.junit.Test;

import java.util.Arrays;

/**
 * 扩展问题 1 求数组小数和
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/2 21:18
 */
public class MergeSortExtend01 extends BaseSort{


    public int process(int[] arr, int L, int R){
        if( L >= R) return 0;
        int mid = L + ((R - L) >> 2);
        return process(arr,L, mid) + process(arr,mid + 1,R) + merge(arr,L,R,mid);
    }

    @Test
    public void testMerge(){
        int[] arr = {1, 3, 4, 2, 5};
        int res = process(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(res);
    }

    //归并过程
    public int merge(int[] arr, int L, int R, int M){
        int result = 0;
        int[] help = new int[R - L + 1];
        int leftI = L;
        int rightI = M + 1;
        int helpI = 0;
        while(leftI <= M && rightI <= R){
            result += arr[leftI] < arr[rightI] ? (R - rightI + 1) * arr[leftI] : 0;
            help[helpI++] = arr[leftI] < arr[rightI] ? arr[leftI ++] : arr[rightI ++];
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
        return result;
    }
    @Override
    public void sort(int[] arr) {
        process(arr,0, arr.length);
    }


}
