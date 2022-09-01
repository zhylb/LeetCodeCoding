package com.lihd.part05;

import java.util.*;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/25 22:55
 */
public class Code01PalindromePairs {

    /**
     * 1985 ms, faster than 21.86%
     * 55.4 MB, less than 84.79%
     * @param words
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @author lihd
     * @date 2022/8/26 9:53
     */
    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        for (int strIndex = 0; strIndex < words.length; strIndex++) {
            char[] chs = words[strIndex].toCharArray();
            int n = chs.length;

            List<Integer> in = manacher(words[strIndex]);
            List<Integer> post = manacher(reverse(chs, 0, n - 1));
            post.replaceAll(integer -> n - 1 - integer);

            String re = reverse(chs, 0, n - 1);
            Integer em = map.get("");
            if (em != null && em != strIndex && re.equals(words[strIndex])) {
                addToAns(em, strIndex, ans);
                addToAns(strIndex, em, ans);
            }


//            if ( in.get(in.size() - 1) == n - 1 && map.containsKey("")) {
//
//                addToAns(strIndex, map.get(""), ans);
//                addToAns(map.get(""), strIndex, ans);
//            }

            for (Integer inOrder : in) {
                if (inOrder < n - 1) {
                    String reverse = reverse(chs, inOrder + 1, n - 1);
                    if (map.containsKey(reverse)) {
                        addToAns(map.get(reverse), strIndex, ans);
                    }
                }
            }

            for (Integer p : post) {
                if (p >= 1) {
                    String reverse = reverse(chs, 0, p - 1);
                    if (map.containsKey(reverse)) {
                        addToAns(strIndex,map.get(reverse),ans);
                    }
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

    private static List<Integer> manacher(String s) {
        if (s == null || s.length() == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(-1);
            return list;
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

    public static int[] manacherss(String word) {
        char[] mchs = addAuxiliaryWords(word);
        int[] rs = new int[mchs.length];
        int center = -1;
        int pr = -1;
        for (int i = 0; i != mchs.length; i++) {
            rs[i] = pr > i ? Math.min(rs[(center << 1) - i], pr - i) : 1;
            while (i + rs[i] < mchs.length && i - rs[i] > -1) {
                if (mchs[i + rs[i]] != mchs[i - rs[i]]) {
                    break;
                }
                rs[i]++;
            }
            if (i + rs[i] > pr) {
                pr = i + rs[i];
                center = i;
            }
        }
        return rs;
    }

    private static boolean isP(String s) {
        return false;
    }

    public static void main(String[] args) {


//        String s = "XAAABBAA";
//        System.out.println(manacher(s));
//        String reverse = reverse(s.toCharArray(), 0, s.length() - 1);
//        System.out.println(manacher(reverse));

//        String[] words = {"ab","ba","abc","cba"};


        String s = "ABA";
        System.out.println(manacher(s));
        System.out.println(Arrays.toString(manacherss(s)));

        String[] words = {"a",""};
        System.out.println(palindromePairs(words));

    }
}
