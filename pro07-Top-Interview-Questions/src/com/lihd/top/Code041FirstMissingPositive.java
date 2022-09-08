package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 11:10
 */
public class Code041FirstMissingPositive {

    /**
     * 这个题目 还是有难度的 !
     * 2 ms, faster than 93.51%, 57.5 MB, less than 87.45%
     * @param nums
     */
    public int firstMissingPositive(int[] nums) {
        // l 代表 [0, l - 1] , 每个位置 i上面的值都是 i + 1
        int l = 0;
        // r 代表 我们在最好情况下的范围 [1, r]
        int r = nums.length;
        while (l < r) {
            if (nums[l] == l + 1) {
                l ++;
            } else if (nums[l] > r || nums[l] <= l || nums[nums[l] - 1] == nums[l]) {
                // 这是三种情况
                // nums[l] > r : 代表 当前值大于最大期望值, 当前值是垃圾值
                // nums[l] <= l : 代表 当前值前面出现过, 是垃圾值
                // nums[nums[l] - 1] == nums[l] : 代表 当前值所期待的位置是j, j位置的值也是 当前值, 说明这个值已经重复, 是垃圾值

                // --r代表 期待减小, 同时将垃圾值放到垃圾堆, 并且将有用值放到 l
                swap(nums, l, --r);
            } else {
                // nums[nums[l] - 1] != nums[l] : 代表 当前值所期待的位置是j, j位置的值肯定不是 当前值
                // 要把这个值放到正确的位置, 即 和 l位置交换, 皆大欢喜
                swap(nums, l, nums[l] - 1);
            }
        }
        return l + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
