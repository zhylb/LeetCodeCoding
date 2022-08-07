package com.lihd.part05;

import java.util.Arrays;

/**
 * 给年龄排序 数值范围 0 - 200
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/4 17:01
 */
public class CountSort extends BaseSort{


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
    // 基数排序 对数值范围要求严格[本例是 0 - 200 ] 空间复杂度O(1) 时间复杂度 O(N)
    public void countSort(int[] arr){

        //可以理解为 help 数组的索引是 真实的数值 help存入的元素是 这个值出现了几次
        int[] help = new int[201];
        Arrays.fill(help,0);
        for (int i = 0; i < arr.length; i++) {
            help[arr[i]] = help[arr[i]] + 1;
        }
        int index = 0;
        //填充数组
        for (int i = 0; i < help.length; i++) {//help是一个定值 因此复杂度不是 O(N * N)
            for (int j = 0; j < help[i]; j++) {
                arr[index++] = i;
            }
        }
    }

    @Override
    public void sort(int[] arr) {
        countSort(arr);
    }

    @Override
    public void testSort() {
        super.testSort();
    }
}
