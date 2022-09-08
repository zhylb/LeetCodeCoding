package com.lihd.top;

import com.sun.org.apache.bcel.internal.generic.DMUL;

import java.util.PriorityQueue;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 14:39
 */
public class Code023MergeKSortedLists {

    /**
     * 使用一个小根堆就行了, 使用傀儡节点减少判空 <br/>
     * 7 ms, faster than 64.01%,  47.2 MB, less than 58.29%
     * @param lists 一堆链表
     * @return com.lihd.top.Code023MergeKSortedLists.ListNode
     * @author lihd
     * @date 2022/9/2 14:48
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode list : lists) {
            // list 不能是null
            if (list != null) {
                heap.add(list);
                // list = list.next; 这一步没有必要
            }
        }
        if (heap.isEmpty()) {
            return dummy.next;
        }

        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                heap.add(node.next);
            }
        }
        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
