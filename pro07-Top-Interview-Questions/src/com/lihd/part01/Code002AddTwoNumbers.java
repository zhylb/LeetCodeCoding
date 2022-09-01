package com.lihd.part01;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/1 12:02
 */
public class Code002AddTwoNumbers {

    /**
     * 不要想太多的转换, 直接在链表上做即可<br/>
     * 进位用一个变量表示 即可, 然后合并两个链表, 可以用傀儡节点 减少判空操作<br/>
     * 3 ms, faster than 80.50%, 47.5 MB, less than 75.58% <br/>
     * @param l1 第一个链表
	 * @param l2 第二个链表
     * @return com.lihd.part01.Code002AddTwoNumbers.ListNode 合并后的新链表
     * @author lihd
     * @date 2022/9/1 12:12
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;
        // 傀儡节点
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        // 有任何一个链表还有长度就 可以继续向下走, 就是为空的链表的值定为0, 不影响结果
        while (l1 != null || l2 != null) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;

            int add = a + b + carry;
            carry = add / 10;
            ListNode node = new ListNode(add % 10);

            cur.next = node;
            cur = node;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry != 0) {
            cur.next = new ListNode(carry);
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
