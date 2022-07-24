package com.lihd.part05;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 19:27
 */
public class SkipTest {
    public static void main(String[] args) {
        SkipListMap<Integer, String> map = new SkipListMap<>();
        map.put(4,"abc");
        map.put(1,"abc");
        map.put(5,"abc");
        map.put(8,"abc");

        System.out.println(map.firstKey());
        map.remove(1);
        System.out.println(map.firstKey());
        System.out.println(map.lastKey());

        System.out.println(map.get(4));
    }
}
