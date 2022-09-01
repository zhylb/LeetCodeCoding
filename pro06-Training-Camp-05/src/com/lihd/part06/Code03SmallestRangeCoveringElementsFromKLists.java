package com.lihd.part06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/29 12:00
 */
public class Code03SmallestRangeCoveringElementsFromKLists {




    /**
     *
     *  46 ms, faster than 67.82%
     *  54.4 MB, less than 83.41%
     * @param nums
     * @return int[]
     * @author lihd
     * @date 2022/8/29 15:42
     */
    public static int[] smallestRange(List<List<Integer>> nums) {

        class Node {
            final int val;
            final int listIndex;
            final int index;
            public Node(int val, int listIndex, int index) {
                this.val = val;
                this.listIndex = listIndex;
                this.index = index;
            }
        }
        TreeSet<Node> set = new TreeSet<>( (a,b) -> a.val == b.val ? a.listIndex - b.listIndex : a.val - b.val);
        for (int i = 0; i < nums.size(); i++) {
            set.add(new Node(nums.get(i).get(0), i, 0));
        }
        int b = set.first().val;
        int e = set.last().val;
        // 注意下面while的判断语句 和 TreeSet的比较方式
        // treeSet 需要满足 同一个 val 要取分出不同来, 否则会出现问题
        while (set.size() == nums.size()) {
            Node first = set.pollFirst();
            int listIndex = first.listIndex;
            int index = first.index;
            List<Integer> list = nums.get(listIndex);
            if (index + 1 < list.size()) {
                set.add(new Node(list.get(index + 1), listIndex, index + 1));
            } else {
                break;
            }
            int c = set.first().val;
            int d = set.last().val;
            if (d - c < e - b) {
                e = d;
                b = c;
            }
        }
        return new int[]{b, e};
    }

    public static void main(String[] args) {
        int[][] arr = {{11, 38, 83, 84, 84, 85, 88, 89, 89, 92}, {28, 61, 89}, {52, 77, 79, 80, 81},
                {21, 25, 26, 26, 26, 27}, {9, 83, 85, 90}, {84, 85, 87}, {26, 68, 70, 71}, {36, 40, 41, 42, 45},
                {-34, 21}, {-28, -28, -23, 1, 13, 21, 28, 37, 37, 38}, {-74, 1, 2, 22, 33, 35, 43, 45},
                {54, 96, 98, 98, 99}, {43, 54, 60, 65, 71, 75}, {43, 46}, {50, 50, 58, 67, 69}, {7, 14, 15},
                {78, 80, 89, 89, 90}, {35, 47, 63, 69, 77, 92, 94}};

        List<List<Integer>> nums = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nums.add(new ArrayList<>());
            for (int j = 0; j < arr[i].length; j++) {
                nums.get(i).add(arr[i][j]);
            }
        }
        System.out.println(Arrays.toString(smallestRange(nums)));
    }


}
