package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 22:42
 */
public class Code033SearchInRotatedSortedArray {
    /**
     * 二分的极致, 一定要有序才能二分吗 ? <br/>
     * 这个旋转过后的例子告诉我们在这种情况下, 只有 [l] [m] [r] 不全都相等就可以二分 <br/>
     * 这个题目 还不错 <br/>
     * 0 ms, faster than 100.00%,  42.1 MB, less than 85.94%
     *
     * @param nums
	 * @param target
     * @return int
     * @author lihd
     * @date 2022/9/4 11:43
     */
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }
            // 此时 [m] != target
            if (nums[l] == nums[r] && nums[l] == nums[m]) {
                // 全都一样 不能二分, 只能一个一个看
                while (l < m && nums[l] == nums[m]) {
                    l ++;
                }
                if (l == m) {
                    // l 已经到了m的位置, 去右边二分, 继续循环 continue, 不要往下面走
                    l = m + 1;
                    continue;
                }
                // 否则就是 num[l] != nums[m] 破坏了循环条件 什么也不要做
            }
            // 三个值一定不是完全相等
            if (nums[l] > nums[m] || nums[r] > nums[m]) {
                // 如果 [l] > [m] 或者 [r] > [m]
                // 说明 m 到 r是有序的
                if (nums[m] <= target && target <= nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }else{
                // 否则说明 l <= [m] && [r] <= [m], 两个等号不同时成立
                // 说明 l 到 m 有序
                if (nums[l] <= target && target <= nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }
        return -1;
    }
}
