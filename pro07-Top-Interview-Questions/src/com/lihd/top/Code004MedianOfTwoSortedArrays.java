package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/1 12:30
 */
public class Code004MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0.0;
        } else if (nums1.length == 0) {
            if ((nums2.length & 1) == 1) {
                return nums2[nums2.length / 2];
            } else {
                return ((double) nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2;
            }
        } else if (nums2.length == 0) {
            if ((nums1.length & 1) == 1) {
                return nums1[nums1.length / 2];
            } else {
                return ((double) nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1]) / 2;
            }
        } else {
            if (((nums1.length + nums2.length) & 1) == 1) {
                // 奇数
                return getKthMin(nums1, nums2, (nums1.length + nums2.length) / 2 + 1);
            } else {
                // 偶数
                int k = (nums1.length + nums2.length) / 2;
                return ((double) getKthMin(nums1, nums2, k) + getKthMin(nums1, nums2, k + 1)) / 2;
            }
        }
    }

    private int getKthMin(int[] a, int[] b, int k) {
        int[] large = a.length > b.length ? a : b;
        int[] small = large == a ? b : a;
        if (k <= small.length) {
            return getUpMedianOfTwoArray(large, 0, k - 1, small, 0, k - 1);
        } else if (k > large.length) {
            if (small[k - large.length - 1] >= large[large.length - 1]) {
                return small[k - large.length - 1];
            }
            if (large[k - small.length - 1] >= small[small.length - 1]) {
                return large[k - small.length - 1];
            }
            return getUpMedianOfTwoArray(small, k - large.length, small.length - 1, large, k - small.length, large.length - 1);
        } else {
            if (large[k - small.length - 1] >= small[small.length - 1]) {
                return large[k - small.length - 1];
            }
            return getUpMedianOfTwoArray(small, 0, small.length - 1, large, k - small.length, k - 1 );
        }
    }

    // 找寻两个等长数组的上中位数 保证参数有效
    private int getUpMedianOfTwoArray(int[] a, int al, int ar, int[] b, int bl, int br) {
        while (al < ar) {
            int am = (al + ar) / 2;
            int bm = (bl + br) / 2;
            int len = ar - al + 1;
            if ((len % 2) == 1) {
                // 说明是奇数
                // 先判断中间位置
                if (a[am] == b[bm]) {
                    return a[am];
                }
                if (a[am] > b[bm]) {
                    if (b[bm] >= a[am - 1]) {
                        return b[bm];
                    }
                    ar = am - 1;
                    bl = bm + 1;
                } else {
                    if (a[am] >= b[bm - 1]) {
                        return a[am];
                    }
                    br = bm - 1;
                    al = am + 1;
                }
            } else {
                // 说明是偶数
                if (a[am] == b[bm]) {
                    return a[am];
                }
                if (a[am] > b[bm]) {
                    ar = am;
                    bl = bm + 1;
                } else {
                    br = bm;
                    al = am + 1;
                }
            }
        }
        return Math.min(a[al], b[bl]);
    }

    public static void main(String[] args) {
        int[] a = {1, 3};
        int[] b = { 2 };

        Code004MedianOfTwoSortedArrays m = new Code004MedianOfTwoSortedArrays();
        double arrays = m.findMedianSortedArrays(a, b);
        System.out.println(arrays);
//






    }


}
