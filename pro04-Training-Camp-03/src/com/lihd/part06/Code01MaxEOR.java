package com.lihd.part06;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/7 21:18
 */
public class Code01MaxEOR {


    public static class Node{
       Node[] next = new Node[2];
    }

    public static class Tire{

        private Node head = new Node();
        public void add(int num) {
            //将num转换为二进制
            Node node = head;
            for (int i = 31; i >= 0; i--) {
                int bitIndex = (num >> i) & 1 ;
                if (node.next[bitIndex] == null) {
                    node.next[bitIndex] = new Node();
                }
                node = node.next[bitIndex];
            }
        }

        public int getMaxEOR(int num) {
            int ans = 0;
            Node node = head;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1 ;
                //expect是期望的值
                int expect = i == 31 ? bit : bit ^ 1;
                //real是实际要走的值
                int real = node.next[expect] != null ? expect : expect ^ 1;
                ans |= (real ^ bit) << i;
                node = node.next[real];
            }
            return ans;
        }

    }
    public static int maxEOR(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Tire tire = new Tire();
        int eor = 0;
        tire.add(eor);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            max = Math.max(tire.getMaxEOR(eor), max);
            tire.add(eor);
        }
        return max;

    }
}
