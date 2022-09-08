package com.lihd.top;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/6 17:17
 */
public class Code084LargestRectangleInHistogram {


    /**
     * 老师讲了一个新的方法, 这种方法的设计比较巧妙<br/>
     * 就是 求每个值的时候, 并不是这个值的最优解, 但是会在某个时候求出这个解<br/>
     * 这种舍弃部分可能性的解比较难以想到<br/>
     * 169 ms, faster than 62.36%, 79.4 MB, less than 79.00%
     * @param heights 参数
     * @see Code042TrappingRainWater 同样舍弃了可能
     * @return int
     * @author lihd
     * @date 2022/9/8 21:41
     */
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        int i = 0;
        while (i < heights.length) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                // 结算, 值一样都结算, 值一样结算的值并不是其最优解
                // 不过我们不再需要这个位置的值, 和其相等的值, 他们的最优解是一样的
                // 下面的严格的单调栈代码也可以看出这一点
                int c = stack.pop();
                int l = stack.isEmpty() ? -1 : stack.peek();
                ans = Math.max(ans, (i - l - 1) * heights[c]);
            }
            stack.add(i);
            i++;
        }
        while (!stack.isEmpty()) {
            int c = stack.pop();
            int l = stack.isEmpty() ? -1 : stack.peek();
            ans = Math.max(ans, (i - l - 1) * heights[c]);
        }
        return ans;
    }



    //*************************************************************************//
    //                     Talk is cheap, Show me the code                     //
    //*************************************************************************//


    /**
     * 单调栈 解决 <br/>
     * 207 ms, faster than 43.14%, 103.8 MB, less than 8.27%
     * @param heights
     * @return int
     * @author lihd
     * @date 2022/9/6 17:48
     */
    public static int largestRectangleArea1(int[] heights) {
        // 单调栈 栈中的元素从小到大
        // 单调栈中存放的是索引
        Stack<List<Integer>> stack = new Stack<>();
        int ans = 0;
        int i = 0;
        while (i < heights.length) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek().get(0)]) {
                List<Integer> list = stack.pop();
                int l = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                ans = Math.max(ans, (i - l - 1) * heights[list.get(0)]);
            }
            if (!stack.isEmpty() && heights[i] == heights[stack.peek().get(0)]) {
                stack.peek().add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
            i ++;
        }

        int r = heights.length;
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            int l = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            ans = Math.max(ans, (r - l - 1) * heights[list.get(0)]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 1};
        int area = largestRectangleArea1(arr);
        System.out.println(area);
    }
}
