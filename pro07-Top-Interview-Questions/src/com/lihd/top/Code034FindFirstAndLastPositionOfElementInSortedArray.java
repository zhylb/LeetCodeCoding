package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 21:57
 */
public class Code034FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * 二分就行了<br/>
     * 0 ms, faster than 100.00%， 45.1 MB, less than 99.87%<br/>
     * @param nums
	 * @param target
     * @return int[]
     * @author lihd
     * @date 2022/9/2 22:37
     */
    public int[] searchRange(int[] nums, int target) {
        return new int[]{findLeft(nums, target), findRight(nums, target)};
    }


    private int findLeft(int[] nums, int target) {
        int ans = -1;
        int l = 0;
        int r = nums.length - 1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;

            if (nums[m] > target) {
                r = m - 1;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                ans = m;
                r = m - 1;
            }
        }
        return ans;
    }

    private int findRight(int[] nums, int target) {
        int ans = -1;
        int l = 0;
        int r = nums.length - 1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;


            if (nums[m] > target) {
                r = m - 1;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                ans = m;
                l = m + 1;
            }

        }
        return ans;
    }
}
