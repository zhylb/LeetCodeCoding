package com.lihd.top;


/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 14:08
 */
public class Code019RemoveNthNodeFromEndOfList {


    /**
     * 就是一个链表删除问题
     * 1 ms, faster than 72.10%, 42.3 MB, less than 43.89%
     * @param head 链表头
	 * @param n 要删除的节点
     * @return com.lihd.top.Code019RemoveNthNodeFromEndOfList.ListNode
     * @author lihd
     * @date 2022/9/2 14:10
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode cur = head;
        // 先移动 n - 1次
        for (int i = 0; i < n - 1; i++) {
            cur = cur.next;
        }
        // cur 已经是null了, 说明我们根本没有n的长度, 返回head
        if (cur == null) {
            return head;
        }
        // cur.next 已经是null了, 说明我们恰好是 n 的长度,也就是说我们要删除头 返回 head.next
        if (cur.next == null) {
            return head.next;
        }
        // last 登场, cur向后移动一步
        // 保证 last是要删除节点的上一个
        ListNode last = head;
        cur = cur.next;

        // 当cur的下一个不是null时, 才一起后移
        // 如果cur是null, 就代表着last来到了删除位置, 来到这个位置是删除不了当前位置的
        while (cur.next != null) {
            last = last.next;
            cur = cur.next;
        }

        last.next = last.next.next;
        return head;
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
