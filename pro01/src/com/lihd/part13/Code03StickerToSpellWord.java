package com.lihd.part13;

import com.sun.deploy.security.CredentialManager;

import java.util.HashMap;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/23 20:10
 */
public class Code03StickerToSpellWord {

    public static int minStickers(String[] stickers, String target) {

        int[][] s = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            s[i] = strToArr(stickers[i]);
        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put("", 0);
        return minStickers(s, target, map);
    }

    public static int[] strToArr(String s) {
        char[] chs = s.toCharArray();
        int[] ans = new int[26];
        for (char c : chs) {
            ans[c - 'a'] ++;
        }
        return ans;
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

    public static boolean haveSameElements(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] > 0 && b[i] > 0) {
                return true;
            }
        }
        return false;
    }

    public static String arrToStr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                sb.append((char)('a' + i));
            }
        }
        return sb.toString();
    }

    public static int minStickers(int[][] stickers, String target, HashMap<String, Integer> map) {
        if (map.containsKey(target)) {
            return map.get(target);
        }
//        if (target.equals("")) {
//            return 0;
//        }
        //还能继续
        int[] targetArr = strToArr(target);
        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            //先判断能不能消除一个
            if (!haveSameElements(sticker, targetArr)) {
                continue;
            }
            //可以继续递归
            targetArr = strToArr(target);
            arrSub(targetArr,sticker);
            String nextTarget = arrToStr(targetArr);

            int minStickers = minStickers(stickers, nextTarget, map);
            if (minStickers != -1) {
                min = Math.min(minStickers, min);
            }
        }
        map.put(target, min == Integer.MAX_VALUE ? -1 : min + 1);
        return min == Integer.MAX_VALUE ? -1 : min + 1;
    }

    public static void main(String[] args) {
        String[] arr = {"aaaa","bbaa","ccddd"};
        String str = "abcccccdddddbbbaaaaa";
        int i = minStickers(arr, str);
        System.out.println(i);
    }


}
