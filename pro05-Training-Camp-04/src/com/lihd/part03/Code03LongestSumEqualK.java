package com.lihd.part03;

import java.util.HashMap;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/12 20:45
 */
public class Code03LongestSumEqualK {

    public static int ans;


    public static int maxNodes(Node head, int K) {
        if (head == null) {
            return -1;
        }
        //注意调节全局变量的值
        ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        //这里的map.put应该不用强调了
        map.put(0, -1);
        maxNodes(head, 0, 0, K, map);
        return ans;
    }
    /**
     * 动态更新静态变量 ans ,最后答案就在其中
     * @param node 当前来到的节点
	 * @param level 当前节点的层数
	 * @param preSum 来到这个节点前 的前缀和
	 * @param K 目标值 K
	 * @param map map 维持好map的定义，key 代表累加和，value代表 第一次 出现的level
     * @author lihd
     * @date 2022/8/12 22:38
     */
    public static void maxNodes(Node node, int level, int preSum, int K, HashMap<Integer, Integer> map) {
        if (node == null) {
            return;
        }
        int sum = preSum + node.val;
        if (!map.containsKey(sum)) {
            //只有第一次才放入
            map.put(sum, level);
        }
        if (map.containsKey(sum - K)) {
            // 注意这里是正确的，因为 0...lastLevel 累加和包括了 lastLevel这一层
            // 我们所在的层是 level 层 ，因此结果就是 level - lastLevel
            // 千万不要 + 1，因为范围就是 (lastLevel,level] 左开右闭
            int lastLevel = map.get(sum - K);
            ans = Math.max(ans,level -  lastLevel);
        }
        maxNodes(node.left, level + 1, sum, K, map);
        maxNodes(node.right, level + 1, sum, K, map);
        //恢复现场
        map.remove(sum);
    }


    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int K = 0;
		Node head = new Node(3);
		head.left = new Node(-2);
		head.left.left = new Node(1);
		head.left.right = new Node(4);
		head.left.left.left = new Node(3);
		head.left.left.right = new Node(5);
		head.left.right.left = new Node(2);
		head.left.right.right = new Node(5);

		head.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(-7);
		head.right.left.left = new Node(-5);
		head.right.left.right = new Node(-3);
		head.right.right.left = new Node(1);
		head.right.right.right = new Node(5);

		System.out.println(maxNodes(head, K));
    }




}
