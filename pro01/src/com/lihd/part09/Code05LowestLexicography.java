package com.lihd.part09;

import java.util.*;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/17 9:55
 */
public class Code05LowestLexicography {

    public static void main(String[] args) {
        String[] strs = {"srg","wqp","werg","erg","asdg"};

        List<String> ans = generateAllArray(strs);
        String lowestLexicography = getLowestLexicography(ans);
        String lowestLexicography1 = getLowestLexicography(strs);
        System.out.println("lowestLexicography = " + lowestLexicography);
        System.out.println("lowestLexicography1 = " + lowestLexicography1);

    }

    public static String  getLowestLexicography(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        Collections.sort(list);
        return list.get(0);
    }

    public static List<String > generateAllArray(String[] strs) {
        ArrayList<String> ans = new ArrayList<>();
        generateAllArray(strs,0, ans);
        return ans;
    }

    public static void generateAllArray(String[] strs, int index, List<String> ans) {
        if (strs.length == index) {
            ans.add(Arrays.toString(strs));
            return;
        }
        for (int i = index; i < strs.length; i++) {
            swap(strs, i, index);
            generateAllArray(strs, index + 1, ans);
            swap(strs, i, index);
        }
    }

    public static void swap(String [] strs, int i, int j) {
        String  temp = strs[i];
        strs[i] = strs[j];
        strs[j] = temp;
    }

    public static void fullArray(String[] strs, String prefix, HashSet<Integer> used, ArrayList<String> result) {

        if (used.size() == strs.length) {
            result.add(prefix);
            return;
        }
        for (int i = 0; i < strs.length; i++) {
            if (!used.contains(i)) {
                used.add(i);
                fullArray(strs,prefix + strs[i],used,result);
                used.remove(i);
            }
        }
    }

    public static String getLowestLexicography(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        Arrays.sort(strs, (a, b) -> (a + b).compareTo(b + a));

        StringBuilder sb = new StringBuilder(strs.length);
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }


}
