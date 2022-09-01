package com.lihd.part05;

import java.util.*;

/**
 * 这个代码是相当失败的, 全是泪啊
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/25 22:55
 */
public class Code01PalindromePairsFail {

    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        for (int strIndex = 0; strIndex < words.length; strIndex++) {
            char[] chs = words[strIndex].toCharArray();
            int n = chs.length;

            String re = reverse(chs, 0, n - 1);
            Integer em = map.get(re);
            if (em != null && em != strIndex && re.equals(words[strIndex])) {
                addToAns(em, strIndex, ans);
                addToAns(strIndex, em, ans);
            }

//            if (!words[strIndex].equals("") && isP(words[strIndex]) && map.containsKey("")) {
//                addToAns(strIndex, map.get(""), ans);
//                addToAns(map.get(""), strIndex, ans);
//            }

            for (int i = 1; i <= n - 1; i++) {
                String c;
                String p;

                c = reverse(chs, 0, i - 1);
                p = value(chs, i, n - 1);
                if (map.containsKey(c) && isP(p)) {
                    addToAns(strIndex, map.get(c), ans);
                }

                c= reverse(chs, i, n - 1);
                p = value(chs, 0, i - 1);

                if (map.containsKey(c) && isP(p)) {
                    addToAns(map.get(c),strIndex, ans);
                }

            }

//            String reverse = reverse(chs, 0, n - 1);
//            if (map.containsKey(reverse)) {
//                addToAns(strIndex, map.get(reverse), ans);
//            }
        }
        return ans;
    }

    private static void addToAns(int a, int b, List<List<Integer>> ans) {
        if (a == b) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        ans.add(list);
    }

    private static String value(char[] chs, int l, int r) {
        int len = r - l + 1;
        char[] ans = new char[len];
        for (int i = 0; i < len; i++) {
            ans[i] = chs[i + l];
        }
        return String.valueOf(ans);
    }

    private static String reverse(char[] chs, int l, int r) {
        int len = r - l + 1;
        char[] ans = new char[len];
        for (int i = 0; i < len; i++) {
            ans[i] = chs[r - i];
        }
        return String.valueOf(ans);
    }




    private static boolean isP(String s) {
        return false;
    }

    private static List<Integer> manacher(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        //s 长度至少为 1
        //处理过的串至少为  1 * 2 + 1 = 3 因此一定会进入下面的for循环
        char[] chs = addAuxiliaryWords(s);
        int[] pArr = new int[chs.length];
        int max = Integer.MIN_VALUE;//max 一定会被更新, 因为一个字符也算回文长度为 1
        int R = 1;
        int C = 0;
        int i = 1;

        ArrayList<Integer> list = new ArrayList<>();
        int ansIndex = 0;
        while (i < chs.length) {
            pArr[i] = i < R ? Math.min(pArr[(C << 1) - i], R - i) : 1;
            while (i + pArr[i] < chs.length && i - pArr[i] >= 0 && chs[i + pArr[i]] == chs[ i - pArr[i]]) {
                pArr[i] ++;
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(pArr[i], max);
            if (max - 1 - i == 0) {
                list.add(i - 1);
            }
            i ++;
        }
        return list;
    }
    //辅助字符 char[]数组默认值 0
    private static char[] addAuxiliaryWords(String s) {
        if (s == null) {
            return null;
        }
        char[] chs = s.toCharArray();
        char[] ans = new char[chs.length << 1 | 1];
        for (int i = 0; i < chs.length; i++) {
            ans[i << 1 | 1] = chs[i];
        }
        return ans;
    }

    public static void main(String[] args) {

        String[] words = {"ab","ba","abc","cba"};
        System.out.println(palindromePairs(words));

    }
}
