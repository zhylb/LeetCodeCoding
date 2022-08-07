//package com.lihd.part02;
//
//import com.lihd.ans.class03.Code02_ReservoirSampling;
//
///**
// * @author ：葬花吟留别1851053336@qq.com
// * @description：TODO
// * @date ：2022/5/31 10:20
// */
//public class Code03ReservoirSampling {
//
//    public static void main(String[] args) {
//        System.out.println("hello");
//        int all = 100;
//        int choose = 10;
//        int testTimes = 50000;
//        int[] counts = new int[all + 1];
//        for (int i = 0; i < testTimes; i++) {
//            ReservoirSampling box = new ReservoirSampling(choose);
//            for (int num = 1; num <= all; num++) {
//                box.add(num);
//            }
//            int[] ans = box.arr;
//            for (int j = 0; j < ans.length; j++) {
//                counts[ans[j]]++;
//            }
//        }
//
//        for (int i = 0; i < counts.length; i++) {
//            System.out.println(i + " times : " + counts[i]);
//        }
//
//    }
//
//}
