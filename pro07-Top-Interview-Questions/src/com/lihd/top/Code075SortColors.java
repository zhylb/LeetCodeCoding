package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/5 21:59
 */
public class Code075SortColors {

    /**
     * 就是数组的partition过程, 快排的子过程简单化而已, 非常简单<br/>
     * 力扣的排名太假了, 我就用了三个变量不至于说浪费很多空间吧<br/>
     * 0 ms, faster than 100.00%, 43.2 MB, less than 5.42%
     * @param nums
     * @author lihd
     * @date 2022/9/5 22:08
     */
    public void sortColors(int[] nums) {
        // [0,l] 的值都是 0 , 代表0区
        int l = -1;
        // [r, nums.len - 1] 的值都是2, 代表 2区
        int r = nums.length;
        int i = 0;
        while (i < r) {
            if (nums[i] == 0) {
                swap(nums, ++l, i++);
            } else if (nums[i] == 2) {
                swap(nums, --r, i);
            } else {
                i ++;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
