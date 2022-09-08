package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 22:38
 */
public class Code026RemoveDuplicatesFromSortedArray {

    /**
     * 比较简单, 没什么说的
     * 1 ms, faster than 100.00%, 47.9 MB, less than 48.69%
     * @param nums
     * @return int
     * @author lihd
     * @date 2022/9/2 22:40
     */
    public int removeDuplicates(int[] nums) {
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
