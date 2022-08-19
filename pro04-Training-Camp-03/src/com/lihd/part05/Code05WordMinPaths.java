package com.lihd.part05;

import java.util.*;

/**
 * 这道题还是很有难度的
 * 深度优先 广度优先 构造nexts集 确实很有意思
 *
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/4 19:45
 */
public class Code05WordMinPaths {


    public static List<List<String>> getMinPaths(String start, String end, List<String> list) {
        HashMap<String, List<String>> nexts = getNexts(start, list);
        HashMap<String, Integer> distanceMap = getDistanceMap(start, nexts);
        List<String> path = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        DFS(start, end, path, nexts, distanceMap, res);
        return res;
    }


    public static void  DFS(String start,
                            String end,
                            List<String> path,
                            HashMap<String, List<String>> nexts,
                            HashMap<String, Integer> distanceMap,
                            List<List<String>> res) {
        path.add(start);
        if (end.equals(start)) {
            res.add(new ArrayList<>(path));
        } else {
            for (String s : nexts.get(start)) {
                if (distanceMap.get(s) == distanceMap.get(start) + 1) {
                    DFS(s, end, path, nexts, distanceMap, res);
                }
            }
        }
        path.remove(start);
    }




    //宽度优先遍历
    public static HashMap<String, Integer> getDistanceMap(String start, HashMap<String, List<String>> nexts) {

        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> distance = new HashMap<>();
        //初始化队列
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        distance.put(start, 0);
        while (!queue.isEmpty()) {
            String poll = queue.poll();
            int dis = distance.get(poll);
            for (String s : nexts.get(poll)) {
                if (!set.contains(s)) {
                    set.add(s);
                    distance.put(s, dis + 1);
                    queue.add(s);
                }
            }
        }
        return distance;
    }

    public static HashMap<String, List<String>> getNexts(String start, List<String> list) {
        HashMap<String, List<String>> map = new HashMap<>();
        map.put(start, getNext(start, list));
        for (String s : list) {
            map.put(s, getNext(s, list));
        }
        return map;

    }

    public static List<String> getNext(String start, List<String> list) {
        List<String> ans = new ArrayList<>();
        char[] chs = start.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != chs[i]) {
                    char temp = chs[i];
                    chs[i] = ch;
                    String str = String.valueOf(chs);
                    if (list.contains(str)) {
                        ans.add(str);
                    }
                    chs[i] = temp;
                }
            }
        }
        return ans;
    }

    public static void printMinPaths(List<List<String>> paths) {
        for (List<String> path : paths) {
            printPath(path);
        }
    }

    private static void printPath(List<String> path) {
        int size = path.size();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                System.out.println(path.get(i));
            } else {
                System.out.print(path.get(i) + " -> ");
            }
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("cab", "acc", "cbc", "ccc", "cac", "cbb", "aab", "abb");
        List<List<String>> minPaths = getMinPaths("abc", "cab", list);
        printMinPaths(minPaths);
    }


}
