package com.lihd.part06;

import org.junit.Test;

import java.util.Random;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/5 12:55
 */
public class ListCrossTest {

    private static final int RANDOM_VALUE_RANGE = 10;

    Random random = new Random();
    ListCross listCross = new ListCross();


    public Node createCircle(int range){
        int length = random.nextInt(range);
        Node head = new Node(0);
        Node cur = head;
        for (int i = 0; i < length; i++) {
            cur.next = new Node(random.nextInt(RANDOM_VALUE_RANGE));
            cur = cur.next;
        }
        cur.next = head;
        return head;
    }

    public Node createCircleList(int range){
        int length = random.nextInt(range);
        Node head = new Node(0);
        Node cur = head;
        for (int i = 0; i < length; i++) {
            cur.next = new Node(random.nextInt(RANDOM_VALUE_RANGE));
            cur = cur.next;
        }
        cur.next = createCircle(range);
        return head;
    }

    public Node createNormalList(int range){
        int length = random.nextInt(range * 2);
        Node head = new Node(0);
        Node cur = head;
        for (int i = 0; i < length; i++) {
            cur.next = new Node(random.nextInt(RANDOM_VALUE_RANGE));
            cur = cur.next;
        }
        return head;
    }

    public Node[] getOneCircleOneNormal(int range){
        return new Node[]{createCircle(range),createNormalList(range),null};
    }
    public Node[] getOneNormalOneCircle(int range){
        return new Node[]{createNormalList(range),createCircle(range),null};
    }
    public Node[] getTwoNormal(int range){
        return new Node[]{createNormalList(range),createNormalList(range),null};
    }
    public Node[] getTwoCircle(int range){
        return new Node[]{createCircle(range),createCircle(range),null};
    }

    public Node[] getNormalCross(int range){
        Node same = createNormalList(range);
        Node headA = createNormalList(range);
        Node headB = createNormalList(range);
        headA.next = same;
        headB.next = same;
        return new Node[]{headA,headB,same};
    }

    public Node[] getCircleCrossSame(int range){
        Node same = createCircleList(range);
        Node headA = createNormalList(range);
        Node headB = createNormalList(range);
        headA.next = same;
        headB.next = same;
        return new Node[]{headA,headB,same};
    }

    public Node[] getCircleCrossNotSame(int range){
        Node same = createCircle(range);
        Node headA = createNormalList(range);
        Node headB = createNormalList(range);
        headA.next = same;
        int step = random.nextInt(range);
        for (int i = 0; i < step; i++) {
            same = same.next;
        }
        headB.next = same;
        return new Node[]{headA,headB,same};
    }

    @Test
    public void testAll(){
        int range = 10;
        for (int i = 0; i < 1000; i++) {
            int rad = random.nextInt(7);
            Node[] infoResult = new Node[0];
            if(rad == 0) infoResult = getOneCircleOneNormal(range);
            if(rad == 1) infoResult = getOneNormalOneCircle(range);
            if(rad == 2) infoResult = getTwoNormal(range);
            if(rad == 3) infoResult = getTwoCircle(range);
            if(rad == 4) infoResult = getNormalCross(range);
            if(rad == 5) infoResult = getCircleCrossSame(range);
            if(rad == 6) infoResult = getCircleCrossNotSame(range);
            Node result = listCross.getAnyIntersectNode(infoResult[0], infoResult[1]);
            if(result != infoResult[2]){
                System.out.println("Fail! : " + rad);
            }
        }
    }

    @Test
    public void testRandom(){
        Node circle = createCircleList(5);
        Node normalList = createNormalList(5);
        System.out.println(normalList);
        System.out.println(circle);
    }


}
