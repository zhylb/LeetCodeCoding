package com.lihd.part12;

import java.util.*;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/19 18:21
 */
public class Code02PrintAllSubsequences {

    public static List<String> getAllSubsequences(String str) {
        ArrayList<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return ans;
        }
        char[] chs = str.toCharArray();
        //getAllSubsequences(chs, 0, new StringBuilder(), ans);
        getAllSubsequences(chs, 0, "", ans);
        return ans;
    }

    public static Set<String> getAllSubsequences2(String str) {
        HashSet<String> set = new HashSet<>();
        if (str == null || str.length() == 0) {
            return set;
        }
        char[] chs = str.toCharArray();
        getAllSubsequences(chs, 0, new StringBuilder(), set);
        return set;
    }

    //打印所有的子序列
    public static void getAllSubsequences(char[] chs, int index, StringBuilder prefix, List<String> ans) {
        if (index == chs.length) {
            ans.add(prefix.toString());
            return;
        }
        getAllSubsequences(chs, index + 1, prefix, ans);
        getAllSubsequences(chs, index + 1, prefix.append(chs[index]), ans);
        //由于 sb 是可变对象 因此注意 删除添加的 不要脏数据 改成String就没有这个问题
        prefix.deleteCharAt(prefix.length() - 1);
    }

    public static void getAllSubsequences(char[] chs, int index, StringBuilder prefix, HashSet<String> ans) {
        if (index == chs.length) {
            ans.add(prefix.toString());
            return;
        }
        getAllSubsequences(chs, index + 1, prefix, ans);
        getAllSubsequences(chs, index + 1, prefix.append(chs[index]), ans);
        //由于 sb 是可变对象 因此注意 删除添加的 不要脏数据 改成String就没有这个问题
        prefix.deleteCharAt(prefix.length() - 1);
    }

    public static void getAllSubsequences(char[] chs, int index, String prefix, List<String> ans) {
        if (index == chs.length) {
            ans.add(prefix);
            return;
        }
        getAllSubsequences(chs, index + 1, prefix, ans);
        getAllSubsequences(chs, index + 1, prefix + chs[index], ans);
    }

    public static void main(String[] args) {
        String str = "aabc";
        List<String> allSubsequences = getAllSubsequences(str);
        for (Object o : allSubsequences) {
            System.out.println(o);
        }

        getAllSubsequences2(str).forEach(System.out::println);

    }


}
