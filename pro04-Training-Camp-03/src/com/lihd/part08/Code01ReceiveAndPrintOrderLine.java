package com.lihd.part08;

import java.util.HashMap;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/9 13:03
 */
public class Code01ReceiveAndPrintOrderLine {

    public static class Node{
        String val;
        Node next;
        public Node(String val) {
            this.val = val;
        }
    }

    public static class MessageBox {

        HashMap<Integer, Node> headMap = new HashMap<>();
        HashMap<Integer, Node> tailMap = new HashMap<>();
        int expect = 1;

        public void receive(int num, String val) {

            if (num < 1) {
                return;
            }
            Node node = new Node(val);
            headMap.put(num, node);
            tailMap.put(num, node);

            if (tailMap.containsKey(num - 1)) {
                tailMap.get(num - 1).next = node;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }
            if (headMap.containsKey(num + 1)) {
                node.next = headMap.get(num + 1);
                headMap.remove(num + 1);
                tailMap.remove(num);
            }

            if (num == expect) {
                print();
            }

        }
        private void print() {
            Node head = headMap.get(expect);
            headMap.remove(expect);
            while (head != null) {
                System.out.print(head.val + " ");
                head = head.next;
                expect ++;
            }
            System.out.println();
            tailMap.remove(expect - 1);
        }

    }

    public static void main(String[] args) {
        MessageBox box = new MessageBox();
        box.receive(2,"B"); // - 2"
        box.receive(1,"A"); // 1 2 -> print, trigger is 1

        box.receive(4,"D"); // - 4
        box.receive(5,"E"); // - 4 5
        box.receive(7,"G"); // - 4 5 - 7
        box.receive(8,"H"); // - 4 5 - 7 8
        box.receive(6,"F"); // - 4 5 6 7 8
        box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3

        box.receive(9,"I"); // 9 -> print, trigger is 9

        box.receive(10,"J"); // 10 -> print, trigger is 10

        box.receive(12,"L"); // - 12
        box.receive(13,"M"); // - 12 13
        box.receive(11,"K"); // 11 12 13 -> print, trigger is 11
    }

}
