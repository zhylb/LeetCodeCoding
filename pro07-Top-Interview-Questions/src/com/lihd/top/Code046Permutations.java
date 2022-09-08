package com.lihd.top;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 19:37
 */
public class Code046Permutations {

    /**
     * 就是一个全排列, 没什么难的 <br/>
     * 1 ms, faster than 98.38%, 44.6 MB, less than 63.87%
     * @param nums 要全排列的数组
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @author lihd
     * @date 2022/9/4 19:42
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        fullArray(nums, 0, ans);
        return ans;
    }

    private void fullArray(int[] nums, int i, List<List<Integer>> ans) {
        if (i == nums.length) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            ans.add(list);
        } else {

            for (int j = i; j < nums.length; j++) {
                swap(nums, i, j);
                fullArray(nums, i + 1, ans);
                // 恢复现场
                swap(nums, i, j);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
