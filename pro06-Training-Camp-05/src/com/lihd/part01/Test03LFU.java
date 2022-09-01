package com.lihd.part01;

import ans.class01.Problem03_LFU;

import java.util.Objects;
import java.util.Random;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/31 18:46
 */
public class Test03LFU {

    public static void main(String[] args) {
        Code03LFU.LFUCache<Integer, Integer> me = new Code03LFU.LFUCache<>(5);
        Problem03_LFU.LFUCache tea = new Problem03_LFU.LFUCache(5);
        // 开始随机测试
        Random r = new Random();
        for (int i = 0; i < 1000_0000; i++) {
            int ra = r.nextInt(3);
            if (ra != 0) {
                int key = r.nextInt(10);
                int val = r.nextInt(10);
                me.put(key, val);
                tea.put(key, val);

            } else {
                int key = r.nextInt(10);
                Integer meGet = me.get(key);
                Integer teaGet = tea.get(key);
                if (!Objects.equals(meGet, teaGet)) {
                    System.out.println(meGet);
                    System.out.println(teaGet);
                    System.out.println("----");
                    break;
                }

            }
        }
    }
}
