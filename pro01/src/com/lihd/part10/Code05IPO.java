package com.lihd.part10;

import java.util.PriorityQueue;

/**
 * 困难 : 502
 * https://leetcode.com/problems/ipo/
 * Runtime: 146 ms, faster than 58.65% of Java online submissions for IPO.
 * Memory Usage: 104.7 MB, less than 31.78% of Java online submissions for IPO.
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/17 22:59
 */
public class Code05IPO {


    public static class Program{
        int capital;
        int profits;
        public Program(int capital, int profits) {
            this.capital = capital;
            this.profits = profits;
        }
    }


    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        //小根堆
        PriorityQueue<Program> capitalHeap = new PriorityQueue<>((a,b) -> a.capital - b.capital);
        //大根堆
        PriorityQueue<Program> profitsHeap = new PriorityQueue<>((a, b) -> -(a.profits - b.profits));
        //小根堆初始化
        for (int i = 0; i < profits.length; i++) {
            Program program = new Program(capital[i], profits[i]);
            capitalHeap.add(program);
        }

        int ans = w;
        //k 控制次数
        for (int i = 0; i < k; i++) {
            //把所有能打过
            while (!capitalHeap.isEmpty() && capitalHeap.peek().capital <= ans) {
                profitsHeap.add(capitalHeap.poll());
            }
            if (profitsHeap.isEmpty()) {
                return ans;
            }
            Program program = profitsHeap.poll();
            ans += program.profits;
        }
        return ans;
    }
}
