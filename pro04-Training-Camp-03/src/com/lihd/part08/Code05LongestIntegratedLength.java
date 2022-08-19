package com.lihd.part08;

import java.util.HashSet;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/9 21:22
 */
public class Code05LongestIntegratedLength {

    public static int maxLength(int[] arr) {
        int ans = 1;
        for (int begin = 0; begin < arr.length; begin++) {
            HashSet<Integer> set = new HashSet<>();
            int max = arr[begin];
            int min = arr[begin];
            //从begin + 1开始是因为 begin的值在上面已经被包括
            for (int end = begin + 1; end < arr.length; end++) {
                //[begin - end]为子数组
                if (set.contains(arr[end])) {
                    break;
                }
                set.add(arr[end]);
                max = Math.max(max, arr[end]);
                min = Math.min(min, arr[end]);
                if (max - min == end - begin) {
                    ans = Math.max(ans, max - min + 1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 5, 3, 2, 6, 4, 3 };
        System.out.println(maxLength(arr));
    }

}
