package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 19:45
 */
public class Code055JumpGame {

    /**
     * 这个问题讲过很多次了, 很简单的<br/>
     * 6 ms, faster than 32.38%,  67.7 MB, less than 62.10%
     * @param nums 跳跃数组
     * @return boolean
     * @see Code045JumpGameII 跳跃游戏2
     * @author lihd
     * @date 2022/9/4 19:47
     */
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }
}
