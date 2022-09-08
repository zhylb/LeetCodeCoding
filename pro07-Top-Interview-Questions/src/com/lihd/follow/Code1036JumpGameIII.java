package com.lihd.follow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 22:05
 */
public class Code1036JumpGameIII {

    /**
     * 看了一下评论区大佬的解法, 简直牛逼<br/>
     * 3 ms, faster than 99.40%, 53.8 MB, less than 91.17%
     * @param arr 数组
	 * @param start 开始位置
     * @return boolean
     * @author lihd
     * @date 2022/9/4 22:22
     */
    public boolean canReach(int[] arr, int start) {
        return 0 <= start && start < arr.length && arr[start] >= 0
                && ((arr[start] = -arr[start]) == 0 || canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]));
    }


    //*************************************************************************//
    //                     Talk is cheap, Show me the code                     //
    //*************************************************************************//



    // 12 ms, faster than 30.90%, 50 MB, less than 96.54%
    public boolean canReach2(int[] arr, int start) {
        return bfs(arr, start);
    }

    private boolean bfs(int[] arr, int start) {
        HashSet<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (arr[poll] == 0) {
                return true;
            }
            int left = poll - arr[poll];
            int right = poll + arr[poll];

            if (left >= 0 && !set.contains(left)) {
                set.add(left);
                queue.add(left);
            }
            if (right < arr.length && !set.contains(right)) {
                set.add(right);
                queue.add(right);
            }
        }
        return false;
    }
}
