package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/1 17:22
 */
public class Code011ContainerWithMostWater {

    /**
     * 自学成才
     * 3 ms, faster than 97.52%, 52.9 MB, less than 88.66%
     * @param height
     * @return int
     * @author lihd
     * @date 2022/9/1 17:26
     */
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int ans = 0;
        while (l < r) {
            ans = Math.max(ans, (r - l) * Math.min(height[l], height[r]));
            if (height[l] <= height[r]) {
                l++;
            } else {
                r --;
            }
        }
        return ans;
    }

}
