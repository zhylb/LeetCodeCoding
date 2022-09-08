package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 19:15
 */
public class Code053MaximumSubarray {

    /**
     * 想法 : 求出必须以每个位置做结尾的子数组, 答案必在其中<br/>
     * 于是 必须以i位置结尾的 子数组最大值求法如下 <br/>
     * 1 如果 i - 1 的值 >= 0, 我们的值是 [i - 1] + nums[i] <br/>
     * 2 否则 我们的值是 nums[i] <br/>
     * 不用动态规划表, 因为一个变量last即可记录上一个位置的值 <br/>
     * 2 ms, faster than 74.41%, 73.1 MB, less than 75.45%
     * @param nums
     * @return int
     * @author lihd
     * @date 2022/9/4 19:19
     */
    public int maxSubArray(int[] nums) {
        // last 代表 必须以 [i - 1]结尾的情况下, 累加和最好是什么
        int last = nums[0];
        // 结果
        int ans = last;
        // 当前值
        int cur;
        for (int i = 1; i < nums.length; i++) {
            if (last >= 0) {
                cur = nums[i] + last;
            } else {
                cur = nums[i];
            }
            ans = Math.max(ans, cur);
            last = cur;
        }
        return ans;
    }
}
