package com.lihd.part02;

import static com.lihd.part02.DoublyNode.printDoublyNode;
import static com.lihd.part02.DoublyNode.printDoublyNodeTail;
import static com.lihd.part02.LinkNode.printNode;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/1 21:01
 */
public class Code01_LinkReverse {


    public static<T> LinkNode<T> reverse(LinkNode<T> head){

        if(head == null) return null;

        LinkNode<T> pre = null;
        LinkNode<T> temp = null;

        //控制好边界条件
        while (head != null){
            temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;

        }
        return pre;
    }


    public static<T> DoublyNode<T> reverseDoubly(DoublyNode<T> head){
        if(head == null) return null;
        DoublyNode<T> pre = null;
        DoublyNode<T> next = null;

        //控制好边界条件
        while(head != null){

            next = head.next;

            head.next = pre;
            head.last = next;

            pre = head;
            head = next;
        }
        return pre;
    }




    public static void main(String[] args) {
        LinkNode<Integer> head = new LinkNode<>(10);
        head.append(head,12).append(head,8).append(head,-2);
        printNode(head);

        LinkNode<Integer> reverse = reverse(head);
        printNode(reverse);
        System.out.println("------------------------------");
        DoublyNode<Integer> doublyNode = new DoublyNode<>(10);
        doublyNode.append(doublyNode,12).append(doublyNode,5).append(doublyNode,3);
        printDoublyNode(doublyNode);

        DoublyNode<Integer> reverseDoubly = reverseDoubly(doublyNode);

        printDoublyNode(reverseDoubly);
        printDoublyNodeTail(reverseDoubly);
    }

}
