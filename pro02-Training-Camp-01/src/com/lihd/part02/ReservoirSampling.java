package com.lihd.part02;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/31 10:24
 */
public class ReservoirSampling {

    int addTimes;
    int[] arr;
    public ReservoirSampling(int size) {
        arr = new int[size];
        addTimes = 0;
    }

    public void add(int val) {
        //必须得先 ++ ，不能后 ++ addTime从 1 开始多顺
        addTimes ++;
        if (addTimes <= arr.length) {
            arr[addTimes - 1] = val;
        } else {
            if (randomVal() < arr.length) {
                int index = (int) (Math.random() * arr.length);
                arr[index] = val;
            }
        }
//        addTimes ++; //addTimes在这一行就错了 而且感觉很难改对

    }

    public int randomVal() {//[0, addTimes) 如果addTimes 那么结果刚好是 [0,arr.length]
        return (int) (Math.random() * addTimes);
    }

    public int[] getResultArr() {
        return arr;
    }


    public static void main(String[] args) {
        int addTimes = 100;
        int need = 10;
        int testTimes = 50_000;
        int[] ans = new int[addTimes];
        for (int i = 0; i < testTimes; i++) {
            ReservoirSampling sampling = new ReservoirSampling(need);
            for (int j = 0; j < addTimes; j++) {
                sampling.add(j);
            }
            for (int i1 : sampling.getResultArr()) {
                ans[i1] ++;
            }
        }

        for (int i = 0; i < ans.length; i++) {
            System.out.println(i + ":出现的次数为 :\t" + ans[i]);
        }

    }

}
