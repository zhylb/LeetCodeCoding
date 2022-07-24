package com.lihd.part03;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/2 20:12
 */
public class QuickSort extends BaseSort{


    @Test
    public void testPartition(){
        int[] array = generateArray(20);
        printArray(array);
        partition(array,0,array.length - 1);
        printArray(array);
    }

    //分割为两份  一个数左边都小于等于这个数 这个数的右边都大于这个数
    public int partition(int[] arr, int L, int R){
        if(L > R) return -1;
        if(L == R) return L;
        //小于的范围
        int less = L - 1;
        int i = L;
        while(i < R){
            if(arr[i] > arr[R]){//这里是 > 代表 如果 i 大于value ,就直接后移
                i ++;
            }else{
                swap(arr,++less,i++);
            }
        }
        swap(arr,R,less + 1);
        return less + 1;
    }
    @Test
    public void testThreeColor(){
        int[] array = generateArray(20);
        printArray(array);
        int[] ints = threeColor(array, 0, array.length - 1);
        printArray(array);
        System.out.println(Arrays.toString(ints));
    }

    //分割成三份 三色问题
    public int[] threeColor(int[] arr, int L, int R){
        if(L > R) return new int[]{-1,-1};
        if(L == R) return new int[]{L,R};

        int less = L - 1;
        int more = R;
        int i = L;

        while(i < more){
            if(arr[i] == arr[R]){
               i ++;
            }else if(arr[i] < arr[R]){//less区 右移
                swap(arr,++less,i ++);
            }else{//more区 左移
                swap(arr,--more,i);//i 不要变 因为这个值是新的
            }
        }
        swap(arr,more,R);
        //less 应该 - 1 ,more不应该 + 1
        return new int[]{less + 1, more};
    }

    // 快排 version1.0
    public void process1(int[] arr,int L, int R){
        if(L >= R) return;
        int partition = partition(arr, L, R);
        process1(arr,L,partition - 1);
        process1(arr,partition + 1,R);
    }

    // 快排 version2.0
    public void process2(int[] arr,int L, int R){
        if(L >= R) return;
        int[] threeColor = threeColor(arr, L, R);
        process2(arr,L,threeColor[0] - 1);
        process2(arr,threeColor[1] + 1,R);
    }

    //快排 version3.0
    public void process3(int[] arr,int L, int R){
        if(L >= R) return;
        int random = getRandom(L, R);
        swap(arr,random,R);
        int[] threeColor = threeColor(arr, L, R);
        process3(arr,L,threeColor[0] - 1);
        process3(arr,threeColor[1] + 1,R);
    }

    public int getRandom(int L, int R){
        //这是经过我数学推导的 会生成 [L,R]左闭右闭的空间 中的一个随机数
        return (int) ((R + 1 -L) * Math.random() + L);
    }


    @Override
    public void sort(int[] arr) {
        //快排方式一 测试成功 复杂度 O(N 2)
        //process1(arr,0,arr.length - 1);
        //快排方式二 测试成功 复杂度 O(N 2)
        //process2(arr,0,arr.length - 1);
        //快排方式三 测试成功 复杂度 O(N log N)

        //为什么在方式二的基础上 添加一个随机数就能算出复杂度是 N log N
        //这是数学方式计算得出的 由于是随机 就无法一直模拟最差的情况 而上面两个兄弟可以 因此 他们复杂度高
        //而这是随机的 数学期望经过 计算 就是 N log N
        process3(arr,0,arr.length - 1);

    }

    @Override
    @Test
    public void testSort() {
        super.testSort();
    }
}
