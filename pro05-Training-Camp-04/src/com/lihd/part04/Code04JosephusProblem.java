package com.lihd.part04;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/12 19:35
 */
public class Code04JosephusProblem {


    public static class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 约瑟夫问题
     * @param head 约瑟夫问题的开始位置。
	 * @param k 每数到k淘汰一个人
     * @return com.lihd.part04.Code04JosephusProblem.Node 最终存活的节点
     * @author lihd
     * @date 2022/8/12 22:05
     */
    public static Node josephus(Node head, int k) {
        if (head == null) {
            return null;
        }
        int len = getLen(head);
        int josephusNum = josephusNum(len, k);

        Node ans = head;

        //这里注意 循环终止条件
        // 因为 josephusNum == 1时。我们无需移动。
        for (int i = 0; i < josephusNum - 1; i++) {
            ans = head.next;
        }
        return ans;
    }

    /**
     * 约瑟夫获取最终存活人的编号，注意结果是 1...len 上游调用时一定注意。
     * 可在有需要时，自行 - 1
     * @param len 总共参与的人数
	 * @param k 每几次淘汰的人数
     * @return int 返回最终存活的人的编号[1, len]
     * @author lihd
     * @date 2022/8/12 22:02
     */
    public static int josephusNum(int len, int k) {
        //一个人的时候 结果是1
        int ans = 1;
        for (int i = 2; i <= len; i++) {
            ans = (ans + k - 1) % i + 1;
        }
        return ans;
    }

    /**
     * 获取循环单链表的长度，不会进行判空，注意传参的有效性
     * @param head 循环单链表 头节点（任意节点也行）
     * @return int 循环单链表的长度
     * @author lihd
     * @date 2022/8/12 22:01
     */
    private static int getLen(Node head) {
        int ans = 1;
        Node end = head;
        while (head.next != end) {
            head = head.next;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = head1;
        System.out.println(josephus(head1, 3).val);
    }

}
