package com.lihd.part08;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/8 22:00
 */
public class Code01AC {

    public static class Node{
        boolean isUse;
        String end;
        Node fail;
        Node[] next = new Node[26];
    }

    public static class ACAutomation{
        private final Node root = new Node();

        public void insert(String s) {
            char[] chs = s.toCharArray();
            Node cur = root;
            for (int i = 0; i < chs.length; i++) {
                int path = chs[i] - 'a';
                if (cur.next[path] == null) {
                    cur.next[path] = new Node();
                }
                cur = cur.next[path];
            }
            cur.end = s;
        }

        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur;
            Node curFail;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    if (cur.next[i] != null) {
                        curFail = cur.fail;
                        cur.next[i].fail = root;
                        while (curFail != null) {
                            if (curFail.next[i] != null) {
                                cur.next[i].fail = curFail.next[i];
                                break;
                            }
                            curFail = curFail.fail;
                        }
                        queue.add(cur.next[i]);
                    }
                }
            }
        }


        public List<String> containWords(String content) {
            List<String> ans = new ArrayList<>();
            Node cur = root;
            Node follow;
            int path;
            char[] chs = content.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                path = chs[i] - 'a';
                while (cur.next[path] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.next[path] != null ? cur.next[path] : root;
                follow = cur;
                while (follow != root && !follow.isUse) {
                    if (follow.end != null) {
                        ans.add(follow.end);
                        follow.isUse = true;
                    }
                    follow = follow.fail;

                }
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();
        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }


}
