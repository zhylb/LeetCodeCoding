package com.lihd.part05;

import java.util.HashMap;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/4 16:19
 */
public class PrefixTree {
    private final PrefixNode root = new PrefixNode();
    public void insert(String str){
        if(str == null || str.length() == 0) return;
        PrefixNode temp = root;
        temp.p ++;
        char[] chs = str.toCharArray();
        for (char ch : chs) {
            PrefixNode node = temp.map.get(ch);
            if(node == null){
                node = new PrefixNode();
                temp.map.put(ch,node);
            }
            temp = node;
            temp.p ++;
        }
        temp.e ++;
    }
    public int search(String str){
        if(str == null || str.length() == 0) return 0;
        PrefixNode temp = root;
        for (char ch : str.toCharArray()) {
            PrefixNode node = temp.map.get(ch);
            if(node == null) return 0;
            temp = node;
        }
        return temp.e;
    }

    public int prefixNumber(String str){
        if(str == null || str.length() == 0) return 0;
        PrefixNode temp = root;
        for (char ch : str.toCharArray()) {
            PrefixNode node = temp.map.get(ch);
            if(node == null) return 0;
            temp = node;
        }
        return temp.p;
    }

    public void delete(String str){
        if(search(str) > 0){
            PrefixNode temp = root;
            temp.p --;
            for (char ch : str.toCharArray()) {
                PrefixNode node = temp.map.get(ch);
                if(node.p == 1){//Force
                    //node为temp的孩子节点 如果node的值为 1  temp指向空并且返回即可
                    temp.map.put(ch,null);
                    return;
                }
                temp = node;
                temp.p --;
            }
            temp.e --;
        }
    }
}

class PrefixNode{
    int p;
    int e;
    HashMap<Character,PrefixNode> map = new HashMap<>();
}