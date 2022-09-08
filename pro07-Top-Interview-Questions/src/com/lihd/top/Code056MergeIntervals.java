package com.lihd.top;

import java.util.*;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 19:58
 */
public class Code056MergeIntervals {

    /**
     * 老师的做法, 感觉更优雅 <br/>
     * 12 ms, faster than 74.53%, 56.2 MB, less than 5.90%<br/>
     * @param intervals
     * @return int[][]
     * @author lihd
     * @date 2022/9/8 22:35
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> list = new ArrayList<>();
        int b = intervals[0][0];
        int e = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= e) {
                e = Math.max(intervals[i][1], e);
            } else {
                // 可以合并了
                list.add(new int[]{b, e});
                b = intervals[i][0];
                e = intervals[i][1];
            }
        }
        // 别忘了, 最后一个没办法结算
        list.add(new int[]{b, e});
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    /*
     *                  ___====-_  _-====___
     *            _--^^^#####//      \\#####^^^--_
     *         _-^##########// (    ) \\##########^-_
     *        -############//  |\^^/|  \\############-
     *      _/############//   (@::@)   \\############\_
     *     /#############((     \\//     ))#############\
     *    -###############\\    (oo)    //###############-
     *   -#################\\  / VV \  //#################-
     *  -###################\\/      \//###################-
     * _#/|##########/\######(   /\   )######/\##########|\#_
     * |/ |#/\#/\#/\/  \#/\##\  |  |  /##/\#/  \/\#/\#/\#| \|
     * `  |/  V  V  `   V  \#\| |  | |/#/  V   '  V  V  \|  '
     *    `   `  `      `   / | |  | | \   '      '  '   '
     *                     (  | |  | |  )
     *                    __\ | |  | | /__
     *                   (vvv(VVV)(VVV)vvv)
     *                      Talk is cheap
     *                    Show me the code
     */


    /**
     * 这个题目再看看
     * 自己写的, 如果不改变参数就会很慢,
     * 18 ms, faster than 23.87%, 55.6 MB, less than 26.13%
     * @param intervals 要合并的数组
     * @return int[][]
     * @author lihd
     * @date 2022/9/4 20:30
     */
    public int[][] merge1(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int swap = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= intervals[i - 1][1]) {
                intervals[i][0] = intervals[i - 1][0];
                intervals[i][1] = Math.max(intervals[i][1], intervals[i - 1][1]);
                swap++;
                swapInnerOne(intervals[i]);
            }
        }
        int[][] ans = new int[intervals.length - swap][2];
        int ansIndex = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] <= intervals[i][1]) {
                ans[ansIndex][0] = intervals[i][0];
                ans[ansIndex][1] = intervals[i][1];
                ansIndex++;
            } else {
                swapInnerSecond(intervals[i]);
            }
        }
        return ans;
    }

    private void swapInnerOne(int[] arr) {
        int t = arr[0] - 1;
        arr[0] = arr[1];
        arr[1] = t;
    }

    private void swapInnerSecond(int[] arr) {
        int t = arr[0];
        arr[0] = arr[1];
        arr[1] = t + 1;
    }

}
