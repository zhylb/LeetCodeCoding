package com.lihd.part12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/19 19:34
 */
public class Code03PrintAllPermutations {

    public static List<String> permutations(String str) {
        ArrayList<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return ans;
        }
        char[] chs = str.toCharArray();
        permutations(chs, 0, ans);
        return ans;
    }


    public static void permutations(char[] chs, int index, ArrayList<String> ans) {
        if (index == chs.length) {
            ans.add(new String(chs));
        }
        for (int i = index; i < chs.length; i++) {
            swap(chs, i, index);
            permutations(chs, index + 1,ans);
            swap(chs, i, index);
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }
    public static List<String> permutations2(String str) {
        ArrayList<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return ans;
        }
        char[] chs = str.toCharArray();
        permutations2(chs, 0, ans);
        return ans;
    }

    public static void permutations2(char[] chs, int index, ArrayList<String> ans) {
        if (index == chs.length) {
            ans.add(new String(chs));
        }
        //使用hashSet实现去重 这里的去重称之为 分支限界
        HashSet<Character> used = new HashSet<>();
        for (int i = index; i < chs.length; i++) {
            if (!used.contains(chs[i])) {
                used.add(chs[i]);
                swap(chs, i, index);
                permutations2(chs, index + 1,ans);
                swap(chs, i, index);
            }

        }
    }

    public static void main(String[] args) {
        List<String> permutations = permutations("1244");
        permutations.forEach(System.out::println);
        System.out.println("==============================");
        List<String> strings = permutations2("1244");
        strings.forEach(System.out::println);
        System.out.println("=================");
        System.out.println(permutations.size());
        System.out.println(strings.size());
    }

}
