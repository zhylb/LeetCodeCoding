package com.lihd.part05;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/maximal-rectangle/submissions/">leetcode</a>
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/25 22:13
 */
public class Code04MaximalRectangle {
    /**
     * 42 ms, faster than 50.53%
     * 55.9 MB, less than 7.68%
     * @param matrix 矩阵
     * @return int
     * @author lihd
     * @date 2022/8/25 22:45
     */
    public static int maximalRectangle(char[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        char[] arr = new char[C];
        int ans = 0;
        for (char[] rows : matrix) {
            for (int i = 0; i < C; i++) {
                arr[i] = (char) (rows[i] == '0' ? 0 : arr[i] + 1);
            }
            ans = Math.max(ans, maxArea(arr));
        }
        return ans;
    }

    /**
     * 该数组柱状图 可以组成 最大的 矩形 面积
     * @param arr 代表柱状图的数组
     * @return int
     * @author lihd
     * @date 2022/8/25 22:46
     */
    private static int maxArea(char[] arr) {
        Stack<List<Integer>> indexStack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {

            while (!indexStack.isEmpty() && arr[i] < arr[indexStack.peek().get(0)]) {

                // 结算
                List<Integer> list = indexStack.pop();
                int r = i;
                int l;
                if (indexStack.isEmpty()) {
                    l = -1;
                } else {
                    List<Integer> lastList = indexStack.peek();
                    l = lastList.get(lastList.size() - 1);
                }
                // 只用结算第一个的面积就行了, 因为他们的高相同并且连着
                int area = arr[list.get(0)] * (r - l - 1);
                ans = Math.max(ans, area);
            }

            if (!indexStack.isEmpty() && arr[i] == arr[indexStack.peek().get(0)]) {
                indexStack.peek().add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                indexStack.push(list);
            }

        }
        // 最后结算 栈里面的元素
        while (!indexStack.isEmpty()) {
            // 结算
            List<Integer> list = indexStack.pop();
            int r = arr.length;
            int l;
            if (indexStack.isEmpty()) {
                l = -1;
            } else {
                List<Integer> lastList = indexStack.peek();
                l = lastList.get(lastList.size() - 1);
            }
            // 只用结算第一个的面积就行了, 因为他们的高相同并且连着
            int area = arr[list.get(0)] * (r - l - 1);
            ans = Math.max(ans, area);
        }
        return ans;
    }

    public static void main(String[] args) {
        char[][] m = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(maximalRectangle(m));
    }
}
