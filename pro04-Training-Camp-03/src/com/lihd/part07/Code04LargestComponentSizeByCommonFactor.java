package com.lihd.part07;

import java.util.HashMap;
import java.util.Optional;
import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-component-size-by-common-factor/submissions/
 * Runtime: 1064 ms, faster than 5.02% of Java online submissions for Largest Component Size by Common Factor.
 * Memory Usage: 149.6 MB, less than 5.44% of Java online submissions for Largest Component Size by Common Factor.
 *
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/8 22:14
 */

public class Code04LargestComponentSizeByCommonFactor {


    public static class Node{
        int val;
        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }


    public static class UnionFind {
        private final HashMap<Integer, Node> nodesMap = new HashMap<>();
        private final HashMap<Node, Node> parentMap = new HashMap<>();
        private final HashMap<Node, Integer> sizeMap = new HashMap<>();

        private int size;

        public UnionFind(int[] nums){
            //初始化
            for (int num : nums) {
                Node node = new Node(num);
                nodesMap.put(num, node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
            size = nums.length;
        }


        private Node findParent(Node node) {
            Stack<Node> stack = new Stack<>();
            while (node != parentMap.get(node)) {
                //从父亲map获取的不是自己，说明有父亲
                stack.push(node);
                node = parentMap.get(node);
            }

            //此时 node 是父节点

            //扁平化处理，这样才能保证最终复杂度都是O(1)
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), node);
            }
            return node;
        }

        private boolean checked(int x, int y) {
            return nodesMap.containsKey(x) && nodesMap.containsKey(y);
        }

        public boolean isSameSet(int x, int y) {
            if (!checked(x, y)) {
                return false;
            }
            Node nodeX = nodesMap.get(x);
            Node nodeY = nodesMap.get(y);
            return findParent(nodeX) == findParent(nodeY);
        }

        public void union(int x, int y) {
            if (!checked(x, y)) {
                return;
            }
            Node parentX = findParent(nodesMap.get(x));
            Node parentY = findParent(nodesMap.get(y));
            if (parentX != parentY) {
                Integer sizeX = sizeMap.get(parentX);
                Integer sizeY = sizeMap.get(parentY);
                Node large = sizeX >= sizeY ? parentX : parentY;
                Node small = large == parentX ? parentY : parentX;
                parentMap.put(small, large);
                sizeMap.put(large, sizeX + sizeY);
                sizeMap.remove(small);
                size --;
            }

        }

        public int size() {
//            Optional<Integer> max = sizeMap.values().stream().max(Integer::compareTo);
//            return max.orElse(-1);

            int max = 0;
            for (Integer value : sizeMap.values()) {
                max = Math.max(max, value);
            }
            return max;
//            return sizeMap.size();

        }

    }

    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    public static int largestComponentSize(int[] nums) {
        UnionFind unionFind = new UnionFind(nums);
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {

        }

        for (int i = 0; i < nums.length; i++) {
            int range = (int) Math.sqrt(nums[i]);
            for (int factor = 1; factor <= range; factor++) {
                if (nums[i] % factor == 0) {
                    if (factor != 1) {
                        if (map.containsKey(factor)) {
                            Integer index = map.get(factor);
                            unionFind.union(nums[index], nums[i]);
                        } else {
                            map.put(factor, i );
                        }
                    }
                    int factorII = nums[i] / factor;
                    if (factorII != 1) {
                        if (map.containsKey(factorII)) {
                            Integer index = map.get(factorII);
                            unionFind.union(nums[index], nums[i]);
                        } else {
                            map.put(factorII, i );
                        }
                    }
                }
            }
        }

        return unionFind.size();
    }
    //备份的答案，时间过不了
//    public static int largestComponentSize(int[] nums) {
//        UnionFind unionFind = new UnionFind(nums);
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (gcd(nums[i], nums[j]) != 1) {
//                    unionFind.union(nums[i], nums[j]);
//                }
//            }
//        }
//        return unionFind.size();
//    }

    public static void main(String[] args) {
        int[] nums = {4,6,15,35};
        int i = largestComponentSize(nums);
        System.out.println("i = " + i);
    }
}
