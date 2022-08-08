package com.lihd.part02;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/1 21:06
 */
public class DoublyNode <T>{
    public T value;
    public DoublyNode<T> next;
    public DoublyNode<T> last;

    public DoublyNode(T value) {
        this.value = value;
    }


    public DoublyNode<T> append(DoublyNode<T> head, T value){

        DoublyNode<T> dummy = new DoublyNode<>(null);
        dummy.next = head;
        DoublyNode<T> node = new DoublyNode<>(value);
        DoublyNode<T> cur = dummy;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = node;
        node.last = cur;
        return dummy.next;
    }

    public static<T> void printDoublyNode(DoublyNode<T> node){
        StringBuilder sb = new StringBuilder();
        if(node == null){
            return;
        }
        sb.append("[").append(node.value);
        while(node.next != null){
            node = node.next;
            sb.append(", ").append(node.value);
        }
        System.out.println(sb.append(" ]"));
    }

    public static<T> void printDoublyNodeTail(DoublyNode<T> node){
        if(node == null){
            return;
        }

        while(node.next != null){
            node = node.next;
        }


        StringBuilder sb = new StringBuilder();

        sb.append("[").append(node.value);
        while(node.last != null){
            node = node.last;
            sb.append(", ").append(node.value);
        }
        System.out.println(sb.append(" ]"));
    }


}
