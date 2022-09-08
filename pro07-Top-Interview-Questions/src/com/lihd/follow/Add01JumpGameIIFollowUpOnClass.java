package com.lihd.follow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 22:45
 */
public class Add01JumpGameIIFollowUpOnClass {

    //测试成功
    public static int jump1(int n, int start, int end, int[] arr) {
        return bfs(arr, start - 1, end - 1);
    }

    public static int bfs(int[] arr, int start, int end) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        map.put(start, 0);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            int level = map.get(poll);
            if (poll == end) {
                return level;
            }
            int left = poll - arr[poll];
            int right = poll + arr[poll];
            if (left >= 0 && !map.containsKey(left)) {
                map.put(left, level + 1);
                queue.add(left);
            }
            if (right < arr.length && !map.containsKey(right)) {
                map.put(right, level + 1);
                queue.add(right);
            }
        }
        return -1;
    }



    //*************************************************************************//
    //                     Talk is cheap, Show me the code                     //
    //*************************************************************************//

    // 有点难改哦
    public static int jumpDp(int n, int start, int end, int[] arr) {
        start--;
        end--;
        int[][] dp = new int[n][n];

        return 0;

    }


    // 测试成功
    public static int jump(int n, int start, int end, int[] arr) {
        return dfs( start - 1,end - 1,  0, arr);
    }

    private static int dfs(int start, int end, int k, int[] arr) {
        if (start == end) {
            // 有效
            return k;
        }
        if (start < 0 || start >= arr.length || arr[start] <= 0) {
            return -1;
        }
        arr[start] = -arr[start];
        int ans1 = dfs(start + arr[start], end, k + 1, arr);
        int ans2 = dfs(start - arr[start], end, k + 1, arr);
        arr[start] = -arr[start];
        if (ans1 != -1 && ans2 != -1) {
            return Math.min(ans1, ans2);
        } else if(ans1 == -1){
            return ans2;
        } else{
            return ans1;
        }

    }


}
