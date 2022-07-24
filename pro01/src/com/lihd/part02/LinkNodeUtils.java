package com.lihd.part02;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/1 21:07
 */
public class LinkNodeUtils {


    public static <T> LinkNode<T> add1(LinkNode<T> head,T value){

        LinkNode<T> node = new LinkNode<>(value);

        if(head == null){
            return node;
        }

        LinkNode<T> cur = head;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = node;
        return head;
    }

    public static <T> LinkNode<T> add(LinkNode<T> head,T value){
        //使用哨兵节点 减少一次 if判断 多使用一个空间
        LinkNode<T> dummy = new LinkNode<>(null);
        dummy.next = head;

        LinkNode<T> node = new LinkNode<>(value);
        LinkNode<T> cur = dummy;

        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = node;
        return dummy.next;
    }

    public static <T> void printNode(LinkNode<T> node){
        StringBuilder sb = new StringBuilder();

        if(node == null){
            return;
        }

        sb.append("[").append(node.value);
        while(node.next != null){
            node = node.next;
            sb.append(", ").append(node.value).append(" ]");
        }
        System.out.println(sb);
    }

}
