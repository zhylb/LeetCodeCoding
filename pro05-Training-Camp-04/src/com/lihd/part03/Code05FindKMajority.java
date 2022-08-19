package com.lihd.part03;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/12 13:30
 */
public class Code05FindKMajority {

    /**
     * 打印 次数超过 数组长度一半的数，如果没有可以打印其他信息
     * @param arr 传入的数组
     * @author lihd
     * @date 2022/8/12 13:35
     */
    public static void printMoreThanHalf(int[] arr) {
        int curVal = 0;
        int hp = 0;
        for (int j : arr) {
            if (hp == 0) {
                curVal = j;
                hp = 1;
            } else if (curVal == j) {
                //值相同
                hp++;
            } else {
                hp--;
            }
        }

        //判断这个数字是不是 超过了半次
        //超过半次的数字 一定会是 curVal
        //curVal 不一定是超过半次的数字 比如【1,2,3,4,5】
        int time = 0;
        for (int i : arr) {
            if (curVal == i) {
                time++;
            }
        }
        if (time > arr.length / 2) {
            System.out.println(curVal);
        } else {
            System.out.println("没人比较水");
        }
    }

    public static void printMoreThanNDivK(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int size = k - 1;
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else if (map.size() != size) {
                //没满
                map.put(i, 1);
            } else {
                //满了，也不包含，说明都要删除一个
                for (Integer key : map.keySet()) {
                    Integer val = map.get(key);
                    if (val == 1) {
                        map.remove(key);
                    } else {
                        map.put(key, map.get(key) - 1);
                    }
                }
                //下面是每个值 - 1 的lambda表达式
//                map.replaceAll((k1, v) -> map.get(k1) - 1);
            }

        }
        //map中所有的 val 清 0
        map.replaceAll((k1,v) -> map.get(k1) - v);
        //重新统计所有个数 这样map的值就非常正确。
        for (int j : arr) {
            if (map.containsKey(j)) {
                map.put(j, 1 + map.get(j));
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > arr.length / k) {
                System.out.print(entry.getKey() + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 1, 2, 1 };
        printMoreThanHalf(arr);
        int K = 4;
        printMoreThanNDivK(arr, K);
    }
}
