package com.lihd.part01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/11 11:09
 */
public class LeetCode212WordSearchII {

    public  List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        TireNode root = buildTree(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, board, root, ans);
            }
        }
        return ans;
    }


    public  void dfs(int row, int col, char[][] board, TireNode node, List<String> ans) {

        char before = board[row][col];
        int next = before - 'a';
        if (before == 0 || node.nexts[next] == null) {
            return;
        }
        node = node.nexts[next];
        if (node.word != null) {
            ans.add(node.word);
            node.word = null;
        }
        board[row][col] = 0;
        if(row > 0) dfs(row - 1, col, board, node, ans);
        if(col > 0) dfs(row, col -1 , board, node, ans);
        if(row + 1 < board.length) dfs(row + 1, col, board, node, ans);
        if(col + 1 < board[0].length) dfs(row, col + 1, board, node, ans);
        board[row][col] = before;
    }


    static class TireNode{
        String word;
        TireNode[] nexts = new TireNode[26];
    }

    public  TireNode buildTree(String[] words) {
        TireNode root = new TireNode();
        for (String word : words) {
            TireNode node = root;
            char[] chs = word.toCharArray();
            for (char ch : chs) {
                int next = ch - 'a';
                if (node.nexts[next] == null) {
                    node.nexts[next] = new TireNode();
                }
                node = node.nexts[next];
            }
            node.word = word;
        }
        return root;
    }
}
