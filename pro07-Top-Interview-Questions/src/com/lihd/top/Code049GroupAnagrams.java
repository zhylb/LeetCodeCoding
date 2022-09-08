package com.lihd.top;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 16:35
 */
public class Code049GroupAnagrams {

    /**
     * 自己提前写了, 感觉也不难 <br/>
     * 这个题目属于是看菜下饭了, 因为 strs.len比较大, 但是每个字符串的长度不大<br/>
     * 可以看出我们不能 分析每一对str是不是异位词, 一定会超时<br/>
     * 我们把每个字符串排序后作为map的键, list作为map的值处理, 这样只用遍历一遍<br/>
     * 而且每个字符串排序的代价也不高, 于是简单就解决了<br/>
     * 9 ms, faster than 89.91%, 56.3 MB, less than 42.11%
     * @param strs 一堆字符串
     * @return java.util.List<java.util.List<java.lang.String>>
     * @author lihd
     * @date 2022/9/4 16:46
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            String s = sort(str);
            if (map.containsKey(s)) {
                map.get(s).add(str);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, list);
            }
        }
        return new ArrayList<>(map.values());
    }

    private String sort(String s) {
        char[] chs = s.toCharArray();
        Arrays.sort(chs);
        return String.valueOf(chs);
    }

    public static void main(String[] args) {
        Code049GroupAnagrams anagrams = new Code049GroupAnagrams();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = anagrams.groupAnagrams(strs);
        System.out.println(lists);
    }

}
