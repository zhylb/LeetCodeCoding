package com.lihd.part01;

import org.junit.Test;

/**
 * 测试二分法
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/3 21:23
 */
public class  Dichotomy {

    @Test
    public void test01(){
        int[] arr = {1,2,3,3,4,4,5,6,6,6,6,6,7,7,7,7,7,7,7,8,8,9};
        arr = new int[]{1, 1, 1, 1, 1, 1 ,2};
        System.out.println(getLeft(arr,1));
        System.out.println(getRight(arr,1));
        System.out.println(get(arr,1));
        System.out.println(get(arr,2));
        System.out.println(get(arr,3));
        System.out.println(get(arr,4));
        System.out.println(get(arr,5));
        System.out.println(get(arr,6));
        System.out.println(get(arr,7));
    }

    @Test
    public void testLocalMinimum(){
        int[] arr = {100, 9, 11, 7, 8, 9, 10, 5, 7, 3, 100};
//        arr = new int[]{1,1,1,1,1};
        int i = localMinimum(arr);
        System.out.println(i);
    }


    //获取局部最小的数
    public int localMinimum(int[] arr){

        if(arr == null || arr.length == 0) return -1;
        if(arr.length == 1) return arr[0];

        //此时arr 至少有两个元素 先检测边界条件
        if(arr[0] < arr[1]) return 0;
        if(arr[arr.length - 1] < arr[arr.length - 2]) return arr.length - 1;

        //此时边界条件不满足 长度至少为3(如果数都一样就算了)
        int L = 1;
        int R = arr.length - 1 - 1;

        while(L < R){
            int M = L + ((R - L)>>2);
            //更通用 感觉效果好
            if(arr[M] < arr[M + 1] && arr[M] < arr[M - 1]){
                return M;
            }else if(arr[M] < arr[M + 1] && arr[M] > arr[M - 1]){
                //在左边分
                R = M - 1;
            }else{
                //再右边分
                L = M + 1;
            }
/*            if (arr[M] > arr[M - 1]) {
                R = M - 1;
            }else if(arr[M] > arr[M + 1]){
                L = M + 1;
            }else {
                return M;
            }*/
        }
        return -1;
    }


    //获取最右边 也需要借助一个变量
    public int getRight(int[] arr, int value){
        int index = -1;
        int L = 0;
        int R = arr.length - 1;
        while(L <= R){
            int M = L + ((R - L) >> 2);
            if(value >= arr[M]){
                L = M + 1;
                index = M;
            }else {
                R = M - 1;
            }
        }
        return index;
    }




    //获取最左边 需要借助一个变量
    public int getLeft(int[] arr, int value){
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while(L <= R){
            int M = L + ((R - L) >> 2);
            if(value > arr[M]){
                L = M + 1;
            }else {
                R = M - 1;
                index = M;
            }
        }
        return index;
    }
    //1 获取任意一个满足的索引
    public int get(int[] arr, int value){
        int L = 0;
        int R = arr.length - 1;
        while(L <= R){
            int M = L + ((R - L) >> 2);
            if(value > arr[M]){
                L = M + 1;
            }else if(value < arr[M]){
                R = M - 1;
            }else{
                return M;
            }
        }
        return -1;
    }
}
