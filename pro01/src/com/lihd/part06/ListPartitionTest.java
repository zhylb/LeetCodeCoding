package com.lihd.part06;

import org.junit.Test;

import java.util.Random;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/4 23:46
 */
public class ListPartitionTest {

    ListPartition partition = new ListPartition();
    Random random = new Random();


    public Node createRandomNode(int range){
        return new Node(random.nextInt(range));
    }

    public Node createRandomNodeList(int length, int range){
        length = random.nextInt(length);

        Node head = createRandomNode(range);
        Node temp = head;
        for (int i = 0; i < length; i++) {
            temp.next = createRandomNode(range);
            temp = temp.next;
        }
        return head;
    }

    public void printNode(Node head){
        Node temp = head;
        StringBuilder sb = new StringBuilder();
        while(temp!= null){
            sb.append(temp.value).append(", ");
            temp = temp.next;
        }
        sb.append("\n");
        System.out.println(sb);
    }

    public Node copyNodeList(Node head){
        if(head == null) return head;
        Node temp = head;
        Node newHead = new Node(0);
        Node newTemp = newHead;
        while (temp != null){
            newTemp.next = new Node(temp.value);
            temp = temp.next;
            newTemp = newTemp.next;
        }
        return newHead.next;
    }

    public void simpleEqual(Node headA,Node headB,int pivot,Node before){

        Node tempA = headA;
        Node tempB = headB;
        while(tempA != null){
            if(tempA.value == tempB.value && tempB.value == pivot || (tempA.value - pivot) * (tempB.value - pivot) > 0){
                //满足条件
                tempA = tempA.next;
                tempB = tempB.next;
            }else{
                System.out.println("pivot = " + pivot);
                printNode(before);
                printNode(headA);
                printNode(headB);
                System.exit(-1);
            }
        }
    }


    @Test
    public void testEqual(){
        for (int i = 0; i < 200; i++) {
            Node head = createRandomNodeList(100, 100);
            Node copyHead = copyNodeList(head);
            Node soutHead = copyNodeList(head);
            int ran = random.nextInt(100);
            Node listPartition = this.partition.listPartition(head, ran);
            Node listPartitionPro = this.partition.listPartitionPro(copyHead, ran);
            simpleEqual(listPartition,listPartitionPro,ran,soutHead);
        }


    }

    @Test
    public void testCopy(){
        Node head = createRandomNodeList(100, 100);
        Node copyHead = copyNodeList(head);
        printNode(head);
        printNode(copyHead);
    }

    @Test
    public void testSimple(){
        Node head = createRandomNodeList(10, 10);

        partition.listPartitionPro(head,5);

        System.out.println(partition);
    }
}
