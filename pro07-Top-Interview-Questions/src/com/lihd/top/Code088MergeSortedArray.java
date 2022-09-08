package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/5 23:27
 */
public class Code088MergeSortedArray {


    /**
     * 倒着merge , 正的太难了, 和归并排序的merge差不多<br/>
     * 0 ms, faster than 100.00%, 41.9 MB, less than 99.18%
     * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
     * @author lihd
     * @date 2022/9/5 23:38
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index  = nums1.length;
        while (m > 0 && n > 0) {
            if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[--index] = nums1[--m];
            } else {
                nums1[--index] = nums2[--n];
            }
        }
        while (m > 0) {
            nums1[--index] = nums1[--m];
        }

        while (n > 0) {
            nums1[--index] = nums2[--n];
        }

    }
}
