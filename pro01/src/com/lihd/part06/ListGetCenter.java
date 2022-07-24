package com.lihd.part06;

import org.junit.Test;

/**
 * 获取链表的节点的中点 对数器已经测试
 *
 * Palindrome
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/5 21:09
 */
public class ListGetCenter {

    ListPartitionTest test = new ListPartitionTest();


    //必须是无环节点
    //奇数中 偶数上中
    public Node getCenter1(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        //至少三个节点
        Node slow = head.next;
        Node fast = head.next.next;//肯定不为空
        //可以移动两下
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    //奇数中 偶数下中
    public Node getCenter2(Node head){
        if(head == null || head.next == null){
            return head;
        }
        //至少两个节点
        Node slow = head.next;
        Node fast = head.next;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public Node getCenter3(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        //至少三个节点
        Node slow = head;
        Node fast = head.next.next;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public Node getCenter4(Node head){
        if(head == null || head.next == null){
            return null;
        }
        //至少两个节点
        Node slow = head;
        Node fast = head.next;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    @Test
    public void test01(){
        for (int i = 0; i < 100; i++) {
            Node head = test.createRandomNodeList(10, 10);
            int length = length(head);
            Node center1 = getCenter1(head);
            Node center2 = getCenter2(head);
            Node center3 = getCenter3(head);
            Node center4 = getCenter4(head);
            boolean flag = false;
            if(length % 2 != 0){
                //奇数
                if(center1 == center2 && center3 == center4){
                    if(center3 != null && center3.next == center1){
                        //测试成功
                        flag = true;
                    }if(center3 == null){
                        flag = true;
                    }
                }
            }else{
                if(center1 == center4){
                    if(center3 != null && center1 != null && center3.next == center1 && center1.next == center2){
                        flag = true;
                    }else if(center3 == null && center1 != null && center1.next == center2){
                        flag = true;
                    }
                }
            }
            if(!flag){
                System.out.println("失败");
                System.out.println("length = " + length);
                test.printNode(head);
                test.printNode(center1);
                test.printNode(center2);
                test.printNode(center3);
                test.printNode(center4);
                break;

            }

        }

    }

    public int length(Node head){
        int len = 0;
        while(head != null){
            head = head.next;
            len ++;
        }
        return len;
    }



}
