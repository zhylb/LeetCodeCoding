package com.lihd.part13;

import java.util.Arrays;
import java.util.HashMap;

/**
 * leetCode 691 题
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/21 23:30
 */
public class Code02StickerToSpellWord {



    public static int minStickers(String[] stickers, String target) {

        int[][] st = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            st[i] = strToIndexArr(stickers[i]);
        }
        int[] t = strToIndexArr(target);
        HashMap<int[], Integer> map = new HashMap<>();
        return minStickers(st, t, map);
    }


    public static boolean isEmpty(int[] arr) {
        for (int j : arr) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }


    public static boolean check(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] > 0 && b[i] > 0) {
                return true;
            }
        }
        return false;
    }

    public static int[] copy(int[] arr) {
        int[] ans = new int[26];

        for (int i = 0; i < 26; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }


    public static int minStickers(int[][] stickers, int[] target, HashMap<int[],Integer> map) {
        if (map.containsKey(target)) {
            return map.get(target);
        }
        if (isEmpty(target)) {
            map.put(target, 0);
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (!check(target, sticker)) {
                continue;
            }
            int[] copy = copy(target);
            arrSub(target,sticker);
            int minStickersDp = minStickers(stickers, target, map);
            target = copy;
            if (minStickersDp != -1) {
                min = Math.min(minStickersDp, min);
            }
        }
        map.put(target, min == Integer.MAX_VALUE ? -1 : min + 1);
        return map.get(target);
    }


    public static int minStickers(int[][] stickers, int[] target) {
        //判断是否成功
        if (isEmpty(target)) {
            return 0;
        }
        //如果没成功
        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (!check(target,sticker)) {
                continue;
            }
            int[] copy = copy(target);
            arrSub(target,sticker);
            int minRec = minStickers(stickers, target);
            target = copy;
            if (minRec != -1) {
                min = Math.min(min, minRec);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min + 1;
    }

    public static void arrSub(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] <= b[i]) {
                a[i] = 0;
            } else {
                a[i] -= b[i];
            }
        }
    }

    public static int[] strToIndexArr(String s) {
        char[] chs = s.toCharArray();
        int[] arr = new int[26];
        for (char ch : chs) {
            arr[ch - 'a']++;
        }
        return arr;
    }

    public static void main(String[] args) {
        String[] arr = {"aaaa","bbaa","ccddd"};
        String str = "abcccccdddddbbbaaaaa";
        int i = minStickers(arr, str);
        System.out.println(i);
    }

}
