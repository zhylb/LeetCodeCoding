package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 11:04
 */
public class Code042TrappingRainWater {

    /**
     * 容器接水问题, 讲过, 二维的比较难 <br/>
     * 解决这个问题的关键是 只关注每一个位置上可以放多少水 <br/>
     * 这里包含了一个思想, 就是我们没有必要求出每个位置的最优解, 这些没求出最优解的位置, 后面会被求出<br/>
     * 1 ms, faster than 99.77%, 48.7 MB, less than 57.64%
     * @param height 高度数组
     * @see Code084LargestRectangleInHistogram 同样舍弃了可能
     * @return int
     * @author lihd
     * @date 2022/9/4 11:08
     */
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int lMax = height[0];
        int rMax = height[height.length - 1];
        int l = 1;
        int r = height.length - 2;

        int water = 0;
        while (l <= r) {
            if (lMax <= rMax) {
                water += Math.max(0, lMax - height[l]);
                lMax = Math.max(lMax, height[l]);
                l++;
            } else {
                water += Math.max(0, rMax - height[r]);
                rMax = Math.max(rMax, height[r]);
                r --;
            }
        }
        return water;
    }

}
