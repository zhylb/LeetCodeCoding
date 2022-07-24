package com.lihd.part06;

import org.junit.Test;

import java.util.Random;
import java.util.Stack;

/**
 * 判断一个链表是否为回文
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/5 21:46
 */
public class ListPalindrome {

    ListPartitionTest listPartitionTest = new ListPartitionTest();

    //链表的反转
    public Node reverse(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node next = null;
        Node pre = null;
        Node cur = head;
        while(cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public Node findCenter(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    public boolean isPalindrome1(Node head){

        if(head == null || head.next == null){
            return true;//空节点 也设置为true
        }
        //至少两个节点
        //使用一个栈
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while(!stack.isEmpty()){
            if(stack.pop().value != cur.value){
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    public boolean isPalindrome2(Node head){
        //小优化 多遍历一次 栈的大小减半
        if(head == null || head.next == null){
            return true;//空节点 也设置为true
        }
        //至少两个节点
        //使用一个栈
        Stack<Node> stack = new Stack<>();
        Node cur = head;

        Node center = findCenter(head);
        while(center.next != null){
            center = center.next;
            stack.push(center);
        }

        while(!stack.isEmpty()){
            if(stack.pop().value != cur.value){
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    public boolean isPalindrome3(Node head){
        //不使用任何额外节点
        if(head == null || head.next == null){
            return true;//空节点 也设置为true
        }
        Node center = findCenter(head);
        Node reverseHead = reverse(center);

        Node curLeft = head;
        Node curRight = reverseHead;

        while(curLeft != null && curRight != null && curLeft.value == curRight.value){
            curLeft = curLeft.next;
            curRight = curRight.next;
        }
        //重新反转 不要有多余的操作
        Node reverse = reverse(reverseHead);
//        center.next = reverse;
        return curLeft == null;
    }

    //
    public Node createPalindrome(){
        Node node = listPartitionTest.createRandomNodeList(10, 10);
        Node copyNode = listPartitionTest.copyNodeList(node);
        Node cur = node;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = reverse(copyNode);
        return node;
    }



    @Test
    public void test01(){
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Node palindrome;
            if (random.nextBoolean()) {
                palindrome = createPalindrome();
            }else {
                palindrome = listPartitionTest.createRandomNodeList(10,10);
            }


            boolean b1 = isPalindrome1(palindrome);
            boolean b2 = isPalindrome2(palindrome);
            boolean b3 = isPalindrome3(palindrome);

            if(b1 != b2 || b2 != b3){
                System.out.println("失败");
                listPartitionTest.printNode(palindrome);
                System.out.println(b1);
                System.out.println(b2);
                System.out.println(b3);
                break;
            }

        }
    }





}
