package com.lihd.top;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/6 16:45
 */
public class Code078Subsets {
    /**
     * 1 ms, faster than 89.73%, 43.4 MB, less than 44.14%
     * @param nums 求子序列的数组
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @author lihd
     * @date 2022/9/6 16:52
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        dfs(nums, 0, path, ans);
        return ans;
    }

    private void dfs(int[] nums, int i, ArrayList<Integer> path, List<List<Integer>> ans) {
        if (i == nums.length) {
            ans.add(path);
        } else {
            dfs(nums, i + 1, path, ans);
            ArrayList<Integer> list = new ArrayList<>(path);
            list.add(nums[i]);
            dfs(nums, i + 1, list, ans);
        }
    }



}
