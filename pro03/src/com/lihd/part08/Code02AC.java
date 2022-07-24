package com.lihd.part08;

import java.util.*;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/14 8:36
 */
public class Code02AC {
    public static class Node{
        boolean isUsed;
        String end;
        Node fail;
        HashMap<Character, Node> map = new HashMap<>();
    }

    public static class ACAutomation {
        private Node root = new Node();
        public void insert(String s) {
            char[] chs = s.toCharArray();
            Node cur = root;
            for (char ch : chs) {
                Node node = cur.map.get(ch);
                if (node == null) {
                    node = new Node();
                    cur.map.put(ch, node);
                }
                cur = cur.map.get(ch);
            }
            cur.end = s;
        }

        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur;
            Node fail;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (Map.Entry<Character, Node> entry : cur.map.entrySet()) {
                    fail = cur.fail;
                    entry.getValue().fail = root;
                    while (fail != null) {
                        if (fail.map.containsKey(entry.getKey())) {
                            entry.getValue().fail = fail.map.get(entry.getKey());
                            break;
                        }
                        fail = fail.fail;
                    }
                    queue.add(entry.getValue());
                }
            }
        }

        public List<String> containWords(String words) {
            char[] chs = words.toCharArray();
            List<String> ans = new ArrayList<>();
            Node cur = root;
            Node follow;
            for (char ch : chs) {
                while (!cur.map.containsKey(ch) && cur != root) {
                    cur = cur.fail;
                }

                cur = cur.map.containsKey(ch) ? cur.map.get(ch) : root;
                follow = cur;
                while (follow != null && !follow.isUsed) {
                    if (follow.end != null) {
                        ans.add(follow.end);
                        follow.isUsed = true;
                    }
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("我是可爱的中国人");
        ac.insert("我是可爱的人");
        ac.insert("我是中国人");
        ac.insert("我是爱国的中国人");

        ac.build();

        for (String s : ac.containWords("我是中国人我是爱国的中国人我是可爱的人")) {
            System.out.println(s);
        }

    }





}
