package com.lihd.part03;

import org.junit.Test;

import java.util.Arrays;

/**
 * 大意是在一个数组中 如果满足 arr[i] > arr[j]  && i < j 称为 arr[i],arr[j]是一对逆序对
 * 比如 [1,3,4,2,5] 的结果为 2
 * 在1右边 比1小的数 0
 * 在3右边 比3小的数 2 1个
 * 在4右边 比4小的数 2 1个
 * 在2右边 比2小的数 0
 * 在5右边 比5小的数 0
 *
 *
 * 可以看出三个问题的相似之处
 * 由于数组最后是从小到大排列 因此 我们总要把问题简化为
 * "在左边或者右边查找 比我大的一个数 " 因为数组是从小到大排列 因此就能够 计算出有多少个值大于和小于这个值
 *
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/2 21:41
 */
public class MergeSortExtend02 extends BaseSort{


    public int process(int[] arr,int L, int R){
        if(L >= R) return 0;
        int mid = L + ((R - L) >> 2)/2;
        return process(arr,L,mid) + process(arr,mid + 1,R) + merge(arr,L,R,mid);
    }

    //归并过程
    public int merge(int[] arr, int L, int R, int M){
        int result = 0;
        int[] help = new int[R - L + 1];
        int leftI = L;
        int rightI = M + 1;
        int helpI = 0;
        while(leftI <= M && rightI <= R){
            result += arr[leftI] > arr[rightI] ? (M - leftI + 1) : 0;
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
        return result;
    }


    @Test
    public void testProcess(){
        int[] arr = {6,1, 3,8, 4, 2, 5};
        int res = process(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(res);
    }

    @Override
    public void sort(int[] arr) {

    }
}
