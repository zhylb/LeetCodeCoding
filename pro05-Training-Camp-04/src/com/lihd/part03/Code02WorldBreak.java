package com.lihd.part03;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/10 19:57
 */


public class Code02WorldBreak {


    public static class Node{
        int end;
        Node[] nexts = new Node[26];
    }

    public static class Tire{
        Node head = new Node();

        public void build(String s) {
            char[] chs = s.toCharArray();
            Node cur = head;
            for (char ch : chs) {
                int next = ch - 'a';
                if (cur.nexts[next] == null) {
                    cur.nexts[next] = new Node();
                }
                cur = cur.nexts[next];
            }
            cur.end ++;
        }

        public void buildBatch(String[] strings) {
            for (String s : strings) {
                build(s);
            }
        }
    }
    /**
     * 递归的调度函数
     * @param str 目标字符串
	 * @param arr 可以选用的数组
     * @return int  总方法数
     * @author lihd
     * @date 2022/8/10 20:20
     */
    public static int getMethodForce(String str, String[] arr) {
        Tire tire = new Tire();
        tire.buildBatch(arr);
        return getMethodForce(0, str.toCharArray(), tire.head);
    }

    /**
     * 最优解的实际暴力递归过程
     * @param index 当前来到的位置
	 * @param chs 目标字符串的char[]数组格式
	 * @param head 必须是前缀树的头结点
     * @return int 返回从index 到最后的方法数
     * @author lihd
     * @date 2022/8/10 20:22
     */
    public static int getMethodForce(int index, char[] chs, Node head) {
        if (index == chs.length) {
            return 1;//这种方法叫做 我什么也不做。
        }
        //cur 永远不会是null
        Node cur = head;
        int ans = 0;
        for (int i = index; i < chs.length; i++) {
            //[i ... chs.length) 遍历即可
            int next = chs[i] - 'a';
            if (cur.nexts[next] == null) {
                //没有后面的路了，证明这个前缀完全失效，跳出循环
                break;
            }
            cur = cur.nexts[next];
            if (cur.end != 0) {
                //说明当前位置 是某个字符串的结尾
                ans += getMethodForce(i + 1, chs, head);
//                ans += getMethodForce(index + 1, chs, head);
            }
        }
        return ans;
    }

    /**
     * 动态规划的方法。
     * @param str 目标字符串
	 * @param arr 可用的字符串
     * @return int
     * @author lihd
     * @date 2022/8/10 20:26
     */
    public static int getMethodDp(String str, String[] arr) {
        Tire tire = new Tire();
        tire.buildBatch(arr);
        char[] chs = str.toCharArray();
        int n = chs.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;

        for (int index = n - 1; index >= 0; index--) {
            Node cur = tire.head;
            for (int i = index; i < n; i++) {
                int next = chs[i] - 'a';
                if (cur.nexts[next] == null) {
                    break;
                }
                cur = cur.nexts[next];
                if (cur.end != 0) {
                    dp[index] += dp[i + 1];
                }
            }
        }
        return dp[0];

    }



    public static void main(String[] args) {
        String str = "aaabbbccc";
        String[] arr = {"a", "aa", "aab","b", "bc", "cc", "c"};
        System.out.println(getMethodDp(str, arr));
        System.out.println(getMethodForce(str,arr));
    }





}
