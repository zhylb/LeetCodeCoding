package com.lihd.part01;

import org.junit.Test;

import java.util.Arrays;

/**
 * num & (num - 1) 删除二进制形式最右面的1
 * num & ((^num) + 1) 仅获取二进制形式最右边的1
 *
 * 测试异或的面试题
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/2 23:29
 */
public class Code02_XORTest {

    @Test
    public void testGetBit1Num(){
        int a = -15;
        for (int i = 0; i < 10; i++) {
            System.out.println(getBit1Num(a));
            System.out.println(getBit1Num2(a));
            System.out.println();
            a ++;
        }

    }


    public int getBit1Num2(int num){
        int count = 0;
        while(num != 0){
            //在数为负数的情况下可能是错的 我实验了都是对的
            //这就怪了 他说是错的 他的建议是 使用异或
//            num ^= num & ((~num) + 1);//他的建议
            num -= num & ((~num) + 1);
            count ++;
        }
        return count;
    }

    //问题四 :
    public int getBit1Num(int num){
        int count = 0;
        while(num != 0){
//            num -= num & ((~num) + 1); //在数为负数的情况下可能是错的 我实验了都是对的
            num = num & (num - 1); //删除二进制最右边的1
            count ++;
        }
        return count;
    }

    @Test
    public void testOnlyTwoSingle(){
        int[] arr = {1,3,3,6,6,4,4,1,8,6,6,7,7,5,9,9};
        onlyTwoSingle(arr);
    }


    //问题三 : 一个数组中有两种数出现了 奇数次 剩下都出现了偶数次 找出打印这个两个数
    public void onlyTwoSingle(int[] arr){

        int xor = 0;
        int xorHalf = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }
        //假设唯一的两个数 为 a,b 则 xor = a ^ b
        //找到 xor 最右侧的一
        int last1 = xor & ((~xor) + 1);

        for (int i = 0; i < arr.length; i++) {
            if((arr[i] & last1) != 0){
                xorHalf ^= arr[i];
            }
        }
        int xorOtherHalf = xor ^ xorHalf;
        System.out.println("xorHalf = " + xorHalf);
        System.out.println("xorOtherHalf = " + xorOtherHalf);
    }

    //问题二 : 在数组中有一组数 其中有且仅有一个数是单独出现 其他全部是成对出现
    @Test
    public void testOnlyOnce(){
        int[] arr = {1,3,3,6,6,4,4,1,8,6,6,7,7};
        System.out.println(onlyOnce(arr));
    }

    public int onlyOnce(int[] arr){
        int ret = 0;
        for (int i = 0; i < arr.length; i++) {
            ret ^= arr[i];
        }
        return ret;
    }


    //问题一 不用额外变量交换两个值

    @Test
    public void testSwapValue(){
        int a = 123;
        int b = 456;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        int[] arr = new int[]{12,34};
        swap(arr,0,1);
        System.out.println(Arrays.toString(arr));
    }

    public void swap(int[] arr,int i, int j){
        //这种交换模式 i != j 否则 会成为0
        if(i == j) return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    @Test
    public void test01(){
        int a =(0 - 1)/2;
        System.out.println(a);
    }

}
