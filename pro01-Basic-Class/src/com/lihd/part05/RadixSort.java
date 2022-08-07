package com.lihd.part05;

import org.junit.Test;

import java.util.Arrays;

/**
 * 位
 * 基数排序 比较恐怖
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/4 17:11
 */
public class RadixSort extends BaseSort{
    //重写父类的方法 严格产生数字的范围
    @Override
    public int[] generateArray(int range) {
        int[] arr = new int[getRandomValue(range)];
        for (int i = 0; i < arr.length; i++) {
            //生成的数字是 0-200的
            arr[i] = getRandomValue(200);
        }
        return arr;
    }
    public void radixSort(int[] arr){
        int maxValue = getMaxValue(arr);
        int bitNum = getBitNum(maxValue);
        radixSort(arr,0,arr.length - 1,bitNum);
    }
    public void radixSort(int[] arr,int L, int R,int bit){
        int[] help = new int[10];//记录个位
        int[] copyArr = new int[R - L + 1];
        //进行五次循环 就可以
        for (int i = 1; i <= bit; i++) {
            Arrays.fill(help,0);
            for (int j = L; j <= R; j++) {
                int bitNum = getBit(arr[j], i);
                help[bitNum] = help[bitNum] + 1;
            }
            //重构这个数组
            for (int j = 1; j < help.length; j++) {
                help[j] = help[j] + help[j - 1];
            }
            for (int j = R; j >= L; j--) {
                int bitNum = getBit(arr[j], i);
                copyArr[help[bitNum] - 1] = arr[j];
                help[bitNum] = help[bitNum] - 1;
            }
            for (int j = 0 , k = L ; k <= R; j++, k++) {
                arr[k] = copyArr[j];
            }
        }
    }


    public int getMaxValue(int[] arr){
        if(arr == null || arr.length == 0) return Integer.MIN_VALUE;
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    public int getBitNum(int num){
        int result = 0;
        while(num != 0){
            num = num/10;
            result ++;
        }
        return result;
    }
    //获取第 n 位的数字是 几
    public int getBit(int num,int place){
        for (int i = 1; i < place; i++) {
            num = num/10;
        }
        return num % 10;
    }




    @Test
    public void testGetBit(){
        System.out.println(getBit(843296,4));
        System.out.println(getBit(1000,4));
        System.out.println(getBit(23,4));
    }

    @Override
    public void sort(int[] arr) {
        radixSort(arr);
    }

    @Override
    @Test
    public void testSort() {
        super.testSort();
    }
}
