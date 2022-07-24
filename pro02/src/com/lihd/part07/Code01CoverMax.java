package com.lihd.part07;

import org.omg.CORBA.INTERNAL;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/1 19:45
 */
public class Code01CoverMax {

    public static class Line{
        int begin;
        int end;

        public Line(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }


    public static int maxCover(int[][] lines) {
        Line[] lineArr = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            lineArr[i] = new Line(lines[i][0], lines[i][1]);
        }
        Arrays.sort(lineArr, (a, b) -> a.begin - b.begin);
        PriorityQueue<Line> heap = new PriorityQueue<>((a, b) -> a.end - b.end);
        int max = 0;
        for (Line line : lineArr) {
            while (!heap.isEmpty() && line.begin >= heap.peek().end) {
                heap.poll();
            }
            heap.add(line);
            max = Math.max(max, heap.size());
        }
        return max;
    }


    public static int maxCover2(int[][] lines) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int[] line : lines) {
            min = Math.min(min, line[0]);
            max = Math.max(max, line[1]);
        }
        int len = max - min + 1;
        int[] ans = new int[len];
        for (int[] line : lines) {
            for (int j = line[0]; j < line[1]; j++) {
                ans[j - min]++;
            }
        }
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            maxVal = Math.max(maxVal, ans[i]);
        }
        return maxVal;
    }



    // for test
	public static int[][] generateLines(int N, int L, int R) {
		int size = (int) (Math.random() * N) + 1;
		int[][] ans = new int[size][2];
		for (int i = 0; i < size; i++) {
			int a = L + (int) (Math.random() * (R - L + 1));
			int b = L + (int) (Math.random() * (R - L + 1));
			if (a == b) {
				b = a + 1;
			}
			ans[i][0] = Math.min(a, b);
			ans[i][1] = Math.max(a, b);
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		int N = 100;
		int L = 0;
		int R = 200;
		int testTimes = 200000;
		for (int i = 0; i < testTimes; i++) {
			int[][] lines = generateLines(N, L, R);
			int ans1 = maxCover(lines);
			int ans2 = maxCover2(lines);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");
	}

}
