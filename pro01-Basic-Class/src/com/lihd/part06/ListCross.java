package com.lihd.part06;

/**
 * 找到两个链表相交的节点
 * circularity
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/5 12:05
 */
public class ListCross {


    public Node getAnyIntersectNode(Node headA, Node headB){
        if(headA == null || headB == null) return null;
        if(headA == headB) return headA;
        Node circularityNodeA = getCircularityNode(headA);
        Node circularityNodeB = getCircularityNode(headB);
        if(circularityNodeA == null && circularityNodeB == null){
            return getParallelNode(headA, headB);
        }else if(circularityNodeA != null && circularityNodeB != null){
            //对数器还是有用的 本来这里写错了 写成了 : circularityNodeA == circularityNodeB
            //通过对数器找到了原因
            return getCrossNode(headA,circularityNodeA,headB,circularityNodeB);
        }
        return null;
    }


    //获取第一个环状节点
    public Node getCircularityNode(Node head){
        if(head == null || head.next == null ||head.next.next == null) return null;
        Node fast = head.next.next;
        Node slow = head.next;
        while(slow != fast){

            //快指针走到头 说明没有环
            if(fast.next == null || fast.next.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //此时两种在环状某一个位置
        //下面 fast从头开始 找到第一环状节点
        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        //再次相遇 这就是返回值
        return fast;
    }
    //两个无环链表 两个节点不能是null
    public Node getParallelNode(Node headA, Node headB){

        Node curA = headA;
        Node curB = headB;
        int n = 0;
        while(curA.next != null){
            curA = curA.next;
            n ++;
        }
        while(curB.next != null){
            curB = curB.next;
            n --;
        }
        if(curA != curB) return null;//两者没有关系
        //下面的情况下 两者一定有交点
        //n 代表两个链表的差值
        if(n >= 0){
            curA = headA;
            curB = headB;
        }else{
            n = -n;
            curA = headB;
            curB = headA;
        }

        //n >= 0 curA 为长的 curB为短的
        while(n > 0){
            curA = curA.next;
            n --;
        }
        //两者长度一样
        while(curA != curB){
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }

    public Node getCrossNode(Node headA,Node circleA, Node headB,Node circleB){

        if(circleA == circleB){
            //说明两者是一致的
            Node curA = headA;
            Node curB = headB;
            int n = 0;
            while(curA != circleA){
                curA = curA.next;
                n ++;
            }
            while(curB != circleB){
                curB = curB.next;
                n --;
            }
            if(n >= 0){
                curA = headA;
                curB = headB;
            }else{
                n = -n;
                curA = headB;
                curB = headA;
            }
            //n >= 0 curA 为长的 curB为短的
            while(n > 0){
                curA = curA.next;
                n --;
            }
            //两者长度一样
            while(curA != curB){
                curA = curA.next;
                curB = curB.next;
            }
            return curA;
        }else{

            Node curA = circleA.next;
            while(curA != circleA){
                if(curA == circleB){
                    return circleB;
                }
                curA = curA.next;
            }
            return null;
        }
    }



}
