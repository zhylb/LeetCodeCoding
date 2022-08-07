package com.lihd.exer;

import ans.class05.Code01_AVLTreeMap;
import ans.class05.Code01_SizeBalancedTreeMap;
import ans.class05.Code03_SkipListMap;

import java.util.Objects;
import java.util.TreeMap;

/**
 * 感觉测试这么多次，应该是对的。只不过测的方法比较少。
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 22:58
 */
public class OrderedListTest {

    public static SBTMap<Integer, Integer> sbtMap = new SBTMap<>();
    public static SkipListMap<Integer, Integer> slMap = new SkipListMap<>();
//    public static com.lihd.part05.SkipListMap<Integer, Integer> slMap = new com.lihd.part05.SkipListMap<>();
    public static TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public static final int maxVal = 50;

    public static int getRandomVal() {
        return (int) (Math.random() * maxVal) + 1;
    }

    public static void add() {
        int key = getRandomVal();
        int val = getRandomVal();
//        System.out.println("增加了 " + key + "  " + val);
        sbtMap.put(key,val);
        slMap.put(key,val);
        treeMap.put(key, val);
    }

    public static void remove() {
        int key = getRandomVal();
//        System.out.println("删除了 " + key + "  ");
        sbtMap.remove(key);
        slMap.remove(key);
        treeMap.remove(key);
    }

    public static void get() {
        int key = getRandomVal();
        Integer a = sbtMap.get(key);
        Integer b = slMap.get(key);
        Integer c = treeMap.get(key);
//        System.out.println("获取了 " + key + "   "+ a + "  " + b + "  " + c);
        if (!Objects.equals(a, b) || !Objects.equals(a, c)) {
            System.out.println(key + "  get 出错 ： " + a + "  " + b + "  " + c);
        }
    }

    public static void size() {
        int size1 = sbtMap.size();
        int size2 = slMap.size();
        int size3 = treeMap.size();
//        System.out.println("长度 "  + size1 + "  " + size2 + "  " + size3);
        if (size1 != size2 || size1 != size3) {
            System.out.println("size 出错 ： " + size1 + "  " + size2 + "  " + size3);
        }
    }


    public static void main(String[] args) {
        int testTimes = 500_0000;
        for (int i = 0; i < testTimes; i++) {
            add();
            get();
            remove();
            size();
        }
        System.out.println(((((((((((((((((((((((((((((((((((("我去"))))))))))))))))))))))))))))))))))));
        System.out.println("测试结束");
        performanceTest();
    }

    public static void performanceTest() {
        System.out.println("性能测试开始");
        TreeMap<Integer, Integer> treeMap;
        Code01_AVLTreeMap.AVLTreeMap<Integer, Integer> avl;
        long start;
        long end;
        int max = 1000000;
        treeMap = new TreeMap<>();
        avl = new Code01_AVLTreeMap.AVLTreeMap<>();
        sbtMap = new SBTMap<>();
        slMap = new SkipListMap<>();
        System.out.println("顺序递增加入测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            treeMap.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            avl.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("avl 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            sbtMap.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("sbt 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            slMap.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("skip 运行时间 : " + (end - start) + "ms");

        System.out.println("顺序递增删除测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            treeMap.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            avl.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("avl 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            sbtMap.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("sbt 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            slMap.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("skip 运行时间 : " + (end - start) + "ms");

        System.out.println("顺序递减加入测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            treeMap.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            avl.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("avl 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            sbtMap.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("sbt 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            slMap.put(i, i);
        }
        end = System.currentTimeMillis();
        System.out.println("skip 运行时间 : " + (end - start) + "ms");

        System.out.println("顺序递减删除测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            treeMap.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            avl.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("avl 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            sbtMap.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("sbt 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            slMap.remove(i);
        }
        end = System.currentTimeMillis();
        System.out.println("skip 运行时间 : " + (end - start) + "ms");

        System.out.println("随机加入测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            treeMap.put((int) (Math.random() * i), i);
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            avl.put((int) (Math.random() * i), i);
        }
        end = System.currentTimeMillis();
        System.out.println("avl 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            sbtMap.put((int) (Math.random() * i), i);
        }
        end = System.currentTimeMillis();
        System.out.println("sbt 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            slMap.put((int) (Math.random() * i), i);
        }
        end = System.currentTimeMillis();
        System.out.println("skip 运行时间 : " + (end - start) + "ms");

        System.out.println("随机删除测试，数据规模 : " + max);
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            treeMap.remove((int) (Math.random() * i));
        }
        end = System.currentTimeMillis();
        System.out.println("treeMap 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            avl.remove((int) (Math.random() * i));
        }
        end = System.currentTimeMillis();
        System.out.println("avl 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            sbtMap.remove((int) (Math.random() * i));
        }
        end = System.currentTimeMillis();
        System.out.println("sbt 运行时间 : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = max; i >= 0; i--) {
            slMap.remove((int) (Math.random() * i));
        }
        end = System.currentTimeMillis();
        System.out.println("skip 运行时间 : " + (end - start) + "ms");

        System.out.println("性能测试结束");
    }




}
