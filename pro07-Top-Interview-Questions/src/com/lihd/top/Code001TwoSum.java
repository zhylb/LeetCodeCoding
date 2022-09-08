package com.lihd.top;

import java.util.HashMap;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/1 11:53
 */
public class Code001TwoSum {

    /**
     * 使用一个map , key 存放值, val 存放位置<br/>
     * 来到一个位置时, 如果map中有 (target - nums[i]) 说明找到了,直接返回 <br/>
     * 否则把这个值加到map中 map.put(nums[i], i), 遍历完所有元素即可 <br/>
     * 1 ms, faster than 99.77% ,  42.5 MB, less than 93.21%
     * @param nums 数组
	 * @param target 目标值
     * @return int[]
     * @author lihd
     * @date 2022/9/1 11:57
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
