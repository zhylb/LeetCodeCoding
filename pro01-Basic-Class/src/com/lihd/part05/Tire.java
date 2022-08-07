package com.lihd.part05;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/4 15:56
 */
public class Tire {
    private final Node root = new Node();
    public void insert(String s){
        Node temp = root;
        temp.p ++;
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            int index = chs[i]  - 'a';
            if(temp.arr[index] == null){
                temp.arr[index] = new Node();
            }
            temp = temp.arr[index];
            temp.p ++;
        }
        temp.e ++;
    }

    public int search(String s){
        if(s == null || s.length() == 0) return 0;
        Node temp = root;
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            int index = chs[i] - 'a';
            if(temp.arr[index] == null){
                return 0;
            }
            temp = temp.arr[index];
        }
        return temp.e;
    }

    public int prefixNum(String s){
        if(s == null || s.length() == 0) return 0;
        char[] chs = s.toCharArray();
        Node temp = root;
        for (int i = 0; i < chs.length; i++) {
            int index = chs[i] - 'a';
            if(temp.arr[index] == null){
                return 0;
            }
            temp = temp.arr[index];
        }
        return temp.p;
    }

    public void delete(String s){
        if(search(s) > 0){
            char[] chs = s.toCharArray();
            Node temp = root;
            temp.p --;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if(temp.arr[index].p == 1){
                    temp.arr[index] = null;
                    return;//应该是return而不是 break
                }
                temp = temp.arr[index];
                temp.p --;
            }
            temp.e --;
        }
    }
}
class Node{
    int p;
    int e;
    Node[] arr = new Node[26];
}