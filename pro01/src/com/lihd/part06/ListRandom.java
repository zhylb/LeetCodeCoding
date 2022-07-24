package com.lihd.part06;

import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

/**
 * 已经测试过 绝对没有问题
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/6 23:18
 */
public class ListRandom {
    private static final Random random = new Random();
    //随机的节点
    public static class RandNode{
        int value;
        RandNode next;
        RandNode rand;
        public RandNode(int value) {
            this.value = value;
        }
    }


    public static RandNode copyRandNodeList(RandNode head){
        HashMap<RandNode, RandNode> map = new HashMap<>();
        RandNode cur = head;
        while(cur != null){
            map.put(cur,new RandNode(cur.value));
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static RandNode copyRandNodeListImp(RandNode head){

        if(head == null){
            return null;
        }
        RandNode cur = head;
        RandNode next = null;
        while(cur != null){
            next = cur.next;
            RandNode node = new RandNode(cur.value);
            cur.next = node;
            node.next = next;
            cur = next;
        }

        cur = head;
        while(cur != null){
            cur.next.rand = cur.rand == null ? null : cur.rand.next;
            cur = cur.next.next;
        }
        cur = head;
        RandNode retHead = cur.next;

        while(cur != null){
            next = cur.next.next;
            cur.next = next == null ? null : next.next;
            cur = next;
        }
        return retHead;
    }

    public static RandNode createRandomList(int maxLen){
        int len = random.nextInt(maxLen) + 1;
        RandNode[] randNodes = new RandNode[len];
        for (int i = 0; i < len; i++) {
            randNodes[i] = new RandNode(random.nextInt(10));
        }
        for (int i = 0; i < len; i++) {
            int rad = random.nextInt(len + 1);
            if(rad == len){
                randNodes[i].rand = null;
            }else{
                //可能指向自己
                randNodes[i].rand = randNodes[rad];
            }
            if(i != len - 1){
                randNodes[i].next = randNodes[i + 1];
            }
        }
        return randNodes[0];
    }

    public static boolean listEqual(RandNode headA,RandNode headB){
        RandNode curA = headA;
        RandNode curB = headB;

        while(curA != null){
            RandNode radA = curA.rand;
            RandNode radB = curB.rand;
            boolean valueEqual = curA.value == curB.value;
            boolean randEqual = (radA != null && radB != null && curA.rand.value == curB.rand.value)||
                    (radA == null && radB == null);
            if(!(valueEqual && randEqual)){
                return false;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return true;
    }




    @Test
    public void test01(){
        for (int i = 0; i < 100; i++) {
            RandNode head = createRandomList(10);
            RandNode node1 = copyRandNodeList(head);
            RandNode node2 = copyRandNodeListImp(head);
            boolean b1 = listEqual(head, node1);
            boolean b2 = listEqual(head, node2);
            if (!b1 || !b2) {
                System.out.println("失败");
                System.out.println("b1 = " + b1);
                System.out.println("b2 = " + b2);
                System.exit(0);
            }
        }

    }
}
