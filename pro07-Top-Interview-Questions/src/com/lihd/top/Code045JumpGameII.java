package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 21:56
 */
public class Code045JumpGameII {


    /**
     * 还是比较简单的 <br/>
     * 1 ms, faster than 99.88%, 49 MB, less than 74.24%
     * @param nums 原数组
     * @return int
     * @see Code055JumpGame 跳跃游戏1
     * @author lihd
     * @date 2022/9/4 22:00
     */
    public int jump(int[] nums) {
        int step = 0;
        int curBorder = 0;
        int nextBorder = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i > curBorder) {
                curBorder = nextBorder;
                step ++;
            }
            nextBorder = Math.max(nextBorder, nums[i] + i);
        }
        return step;
    }
}
