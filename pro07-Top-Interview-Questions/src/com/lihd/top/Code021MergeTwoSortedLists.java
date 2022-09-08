package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 14:25
 */
public class Code021MergeTwoSortedLists {


    /**
     * 就是合并两个链表, 用傀儡节点 减少判断<br/>
     * 1 ms, faster than 80.06%, 43 MB, less than 60.22%
     * @param list1
	 * @param list2
     * @return com.lihd.top.Code021MergeTwoSortedLists.ListNode
     * @author lihd
     * @date 2022/9/2 14:35
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 傀儡节点
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            // 谁小移动谁
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        if (list1 == null) {
            cur.next = list2;
        } else {
            cur.next = list1;
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
