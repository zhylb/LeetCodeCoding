package com.lihd.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/18 21:45
 */
public class My {

    static int[] ans = new int[100];

    public static void getRange(int l, int r) {
        if (l == r) {
            ans[l]++;
        } else {
            for (int i = l + 1; i <= r; i++) {
                getRange(i, r);
            }
            for (int i = l; i < r; i++) {
                getRange(l, i);
            }
        }
    }

    public static HashMap<Integer, Integer> getMap(int l, int r) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Arrays.fill(ans, 0);
        getRange(l, r);
        for (int i = l; i <= r; i++) {
            map.put(i, ans[i]);
        }
        return map;
    }

    public static void print(int l, int r) {
        HashMap<Integer, Integer> map0 = getMap(l, r);
        HashMap<Integer, Integer> map1 = getMap(l + 1, r);
        HashMap<Integer, Integer> map2 = getMap(l, r - 1);
        HashMap<Integer, Integer> map3 = getMap(l + 1, r - 1);


        HashMap<Integer, Integer> map = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            map.put(entry.getKey(),2 * entry.getValue());
        }
        for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
            map.put(entry.getKey(), 2 * entry.getValue() +  map.getOrDefault(entry.getKey(), 0));
        }

        for (Map.Entry<Integer, Integer> entry : map3.entrySet()) {
            map.put(entry.getKey(), - 3 * entry.getValue() +  map.getOrDefault(entry.getKey(), 0));
        }



        System.out.println(map);
        System.out.println(map0);

    }

    public static void main(String[] args) {
        print(5,20);

    }
}
