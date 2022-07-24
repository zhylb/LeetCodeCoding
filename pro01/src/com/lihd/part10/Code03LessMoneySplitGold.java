package com.lihd.part10;

import java.util.*;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/17 23:17
 */
public class Code03LessMoneySplitGold {

    public static void splitAll(List<Integer> golds, int curMoney, List<Integer> ans) {
        if (golds.size() == 1) {
            ans.add(curMoney);
            return;
        }
        int size = golds.size();
        int l = 0;
        for (Integer gold : golds) {
            l += gold;
        }
        for (int i = 0; i < size; i++) {
            Integer remove = golds.remove(i);
            splitAll(golds, curMoney + l,ans);
            golds.add(i,remove);
        }
    }

    public static int splitGold2(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        splitAll(list, 0,ans);
        Collections.sort(ans);
        return ans.get(0);
    }


    public static int splitGold(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }
        int ans = 0;
        int cur;
        while (heap.size() > 1) {
            cur = heap.poll() + heap.poll();
            ans += cur;
            heap.add(cur);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] golds = {1,2,3,4,5};
//        int[] golds = {30,10,20,60};
        int splitGold = splitGold(golds);
        int splitGold2 = splitGold2(golds);
        System.out.println("splitGold = " + splitGold);
        System.out.println("splitGold2 = " + splitGold2);
    }

}
