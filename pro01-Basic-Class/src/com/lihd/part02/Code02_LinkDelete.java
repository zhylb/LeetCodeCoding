package com.lihd.part02;

import static com.lihd.part02.DoublyNode.printDoublyNode;
import static com.lihd.part02.DoublyNode.printDoublyNodeTail;
import static com.lihd.part02.LinkNode.printNode;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/1 21:52
 */
public class Code02_LinkDelete {


    public static<T> LinkNode<T> deleteByValue(LinkNode<T> head, T value){
        while(head != null && head.value.equals(value)){
            head = head.next;
        }
        //处理完成后head 的值 不是value
        if(head == null) return null;
        LinkNode<T> pre = head;
        LinkNode<T> cur = head.next;
        while(cur != null){
            if(cur.value.equals(value)){
                //pre = cur.next; //错误的 pre任何时候都不要指向 可能是要删除的节点
                pre.next = cur.next;
            }else {
                pre = pre.next;
            }
            cur = cur.next;
        }
        return head;
    }

    //连线没有问题
    public static<T> DoublyNode<T> deleteByValue(DoublyNode<T> head, T value){

        while(head != null && head.value.equals(value)){
            head = head.next;
            head.last = null;
        }

        if(head == null) return null;

        DoublyNode<T> cur = head.next;
        DoublyNode<T> pre = head;

        while(cur != null){
            cur.last = pre;
            if(cur.value.equals(value)){
                pre.next = cur.next;

            }else {
                pre = pre.next;
            }
            cur = cur.next;
        }
        return head;
    }


    public static void main(String[] args) {
        LinkNode<Integer> head = new LinkNode<>(12);
        head.append(head,12).append(head,8).append(head,12).append(head,8).append(head,-2).append(head,12);
        printNode(head);
        LinkNode<Integer> node = deleteByValue(head, 12);
        printNode(node);
        System.out.println("------------------------------------");
        DoublyNode<Integer> doublyNode = new DoublyNode<>(12);
        doublyNode.append(doublyNode,12).append(doublyNode,8).append(doublyNode,12).append(doublyNode,12).append(doublyNode,-2).append(doublyNode,12);
        printDoublyNode(doublyNode);

        DoublyNode<Integer> delete = deleteByValue(doublyNode, 12);

        printDoublyNode(delete);
        printDoublyNodeTail(delete);
    }
}
