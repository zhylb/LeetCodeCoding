package com.lihd.part04;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/16 22:52
 */
public class Code05EnvelopesProblem {


    public static class Envelope{
        int length;
        int width;

        public Envelope(int length, int width) {
            this.length = length;
            this.width = width;
        }
    }

    public static Envelope[] generateSortedEnvelopesArr(int[][] matrix) {
        Envelope[] envelopes = new Envelope[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            envelopes[i] = new Envelope(matrix[i][0], matrix[i][1]);
        }
        Arrays.sort(envelopes, (a, b) -> a.length == b.length ? Integer.compare(b.width, a.width) : Integer.compare(a.length, b.length));
        return envelopes;
    }


    public static int maxEnvelopes(int[][] matrix) {
        Envelope[] envelopes = generateSortedEnvelopesArr(matrix);
        //只看第二维数字，即只看width 来一个最长公共子序列问题即可。

        int[] ends = new int[envelopes.length];
        int[] dp = new int[envelopes.length];
        ends[0] = envelopes[0].width;
        dp[0] = 1;
        int right = 0;
        int l;
        int r;
        int m;
        for (int i = 1; i < envelopes.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = l + (r - l) / 2;
                if (ends[m] < envelopes[i].width) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = envelopes[i].width;
            dp[i] = l + 1;
        }

        int ans = dp[0];
        for (int i = 1; i < dp.length; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] test = { { 3, 4 }, { 2, 3 }, { 4, 5 }, { 1, 3 }, { 2, 2 }, { 3, 6 }, { 1, 2 }, { 3, 2 }, { 2, 4 } };
        int envelopes = maxEnvelopes(test);
        System.out.println("envelopes = " + envelopes);
    }


}
