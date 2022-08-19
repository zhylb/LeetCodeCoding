package com.lihd.part01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Runtime: 49 ms, faster than 97.96% of Java online submissions for Word Search II.
 * Memory Usage: 54.9 MB, less than 35.34% of Java online submissions for Word Search II.
 * 真的很难了
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/11 9:56
 */
public class Code04WordSearch {




    public static List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        LinkedList<Character> path = new LinkedList<>();
        Tire tire = new Tire();
        tire.buildBatch(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWords(i, j, board, tire.root, path, ans);
            }
        }
        return ans;

    }

    /**
     * 递归过程，比较恶心。
     * 注意 把答案加上后记得 让node.e --
     * @param row 当前来的row
	 * @param col 当前来到的col
	 * @param board 字符棋盘 固定参数
	 * @param node 前缀树的某个节点，我们来到这个节点
	 * @param path 之前的路径
	 * @param ans 答案返回的集合
     * @return int 把多少条数据放到了ans中 这个返回值可以加速我们的寻找
     * @author lihd
     * @date 2022/8/11 10:55
     */
    public static int findWords(int row, int col, char[][] board,
                                Node node, LinkedList<Character> path,
                                List<String> ans
    ) {

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            //不合法的情况。
            return 0;
        }

        if (board[row][col] == 0) {
            //走了重复的方法数，返回 0 没有合法的方法数
            return 0;
        }
        int res = 0;
        char before = board[row][col];
        int next = before - 'a';
        if (node.nexts[next] == null || node.nexts[next].p == 0) {
            //没有后面的路
            return 0;
        }
        //记录当前的字符是什么 用于数据恢复
        board[row][col] = 0;
        //路径加上当前的字符
        path.add(before);
        node = node.nexts[next];

        if (node.e != 0) {
            //是结尾
            res++;
            node.e--;
            ans.add(getString(path));
        }
        int p1 = findWords(row + 1, col, board, node, path, ans);
        int p2 = findWords(row - 1, col, board, node, path, ans);
        int p3 = findWords(row, col + 1, board, node, path, ans);
        int p4 = findWords(row, col - 1, board, node, path, ans);
        res = res + p1 + p2 + p3 + p4;
        //数据恢复
        board[row][col] = before;
        path.pollLast();
        node.p -= res;
        return res;
    }

    public static String getString(LinkedList<Character> list) {
        StringBuilder sb = new StringBuilder();
        for (Character character : list) {
            sb.append(character);
        }
        return sb.toString();
    }



    public static class Node{
        int p;
        int e;
        Node[] nexts = new Node[26];
    }

    public static class Tire{
        Node root = new Node();

        public void build(String s) {
            Node cur = root;
            char[] chs = s.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                int next = chs[i] - 'a';
                cur.p++;
                if (cur.nexts[next] == null) {
                    cur.nexts[next] = new Node();
                }
                cur = cur.nexts[next];
            }
            cur.p++;
            cur.e++;
        }

        public void buildBatch(String[] words) {
            for (String word : words) {
                build(word);
            }
        }

    }

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String [] words = {"oath", "pea", "eat", "rain"};
        List<String> list = findWords(board, words);
        for (String s : list) {
            System.out.println(s);
        }
    }

}
