package com.lihd.part04;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/1 22:53
 */
public abstract class BaseSort {

    public void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (i != 0 && i % 20 == 0){
                System.out.println(arr[i]);
            }else {
                System.out.print(arr[i] + ", ");
            }
        }
        System.out.println("--------------------");
    }

    public abstract void sort(int[] arr);


    public int[] copyArray(int[] arr){
        int[] copyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, arr.length);
        return copyArr;
    }

    @Test
    public void testSort(){
        try{
            long begin = System.currentTimeMillis();
            BaseSort baseSort = this.getClass().newInstance();
            System.out.println(baseSort.getClass().getSimpleName());
            baseSort.test(100,100);
            long end = System.currentTimeMillis();
            System.out.println(" 共计花费了 " + (end - begin));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public void test(int times,int range){
        boolean flag = true;
        for (int i = 0; i < times; i++) {
            int[] arr = generateArray(range);
            int[] copyArr = copyArray(arr);
            int[] soutArr = copyArray(arr);

            sort(arr);
            logarithm(copyArr);


            if(!Arrays.equals(arr, copyArr)){
                printArray(soutArr);
                printArray(arr);
                printArray(copyArr);
                flag = false;
                break;
            }
        }
        if(flag){
            System.out.println("测试成功");
        }else {
            System.out.println("测试失败");
        }
    }


    public void logarithm(int[] arr){
        Arrays.sort(arr);
    }


    public int[] generateArray(int range){
        int[] arr = new int[getRandomValue(range)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getRandomValue(range) - getRandomValue(range);
        }
        return arr;
    }

    public int getRandomValue(int n){
        return (int)(Math.random() * n) + 1;
    }

    public void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }




}
