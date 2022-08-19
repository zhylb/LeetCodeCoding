package com.lihd.part04;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/12 19:55
 */
public class Code02FindKthMinNumber {

    /**
     * 返回第 k 小的数
     * @param aArr 第一个有序数组
	 * @param bArr 第二个有序数组
	 * @param k 第k小的数
     * @return int 返回第 k 小的数
     * @author lihd
     * @date 2022/8/12 22:14
     */
    public static int findKthMinNumber(int[] aArr, int[] bArr, int k) {

        int[] large = aArr.length > bArr.length ? aArr : bArr;
        int[] small = large == aArr ? bArr : aArr;
        int s = small.length;
        int l = large.length;

        if (k <= s) {
            return getMiddle(small, 0, k - 1, large, 0, k - 1);
        } else if (k > l) {

            if (small[k - l - 1] >= large[l - 1]) {
                return small[k - l - 1];
            }
            if (large[k - s - 1] >= small[s - 1]) {
                return large[k - s - 1];
            }


            return getMiddle(small, k - l, s - 1, large, k - s, l - 1);
        } else {
            int index = k - s - 1;
            if (large[index] >= small[s - 1]) {
                return large[index];
            }

            //一般情况
            return getMiddle(small, 0, s - 1, large, index + 1, k - 1);
        }


    }

    /**
     * 获取两个有序数组 如果排序后 的上中点的值
     * 始终保证 ar - al == br - al 即两者长度相等，且范围有效即可
     * @param aArr 第一个有序数组
	 * @param al 数组aArr开始索引
	 * @param ar 数组aArr结束索引
	 * @param bArr 第二个有序数组
	 * @param bl 数组bArr开始索引
	 * @param br 数组bArr结束索引
     * @return int 两个数组 如果排序后 的上中点的值
     * @author lihd
     * @date 2022/8/12 22:11
     */
    public static int getMiddle(int[] aArr, int al, int ar, int[] bArr, int bl, int br) {

        int am = 0;
        int bm = 0;
        while (al < ar) {
            //找到两个的中点
            am = al + (ar - al) >> 1;
            bm = bl + (br - bl) >> 1;
            if (aArr[am] == bArr[bm]) {
                return aArr[am];
            }
            //下面的情况一定是不相等的
            if (((ar - al + 1) & 1) == 0) {
                //说明是偶数
                if (aArr[am] > bArr[bm]) {
                    ar = am;
                    bl = bm + 1;
                } else {
                    br = bm;
                    al = am + 1;
                }
            } else {
                //说明是奇数
                if (aArr[am] > bArr[bm]) {
                    if (bArr[bm] >= aArr[am - 1]) {
                        return bArr[bm];
                    }
                    ar = am - 1;
                    bl = bm + 1;
                } else {
                    if (aArr[am] >= bArr[bm - 1]) {
                        return aArr[am];
                    }
                    br = bm - 1;
                    al = am + 1;
                }

            }
        }
        return Math.min(aArr[al], bArr[bl]);
    }


    // For test, this method is inefficient but absolutely right
    public static int[] getSortedAllArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new RuntimeException("Your arr is invalid!");
        }
        int[] arrAll = new int[arr1.length + arr2.length];
        int index = 0;
        for (int i = 0; i != arr1.length; i++) {
            arrAll[index++] = arr1[i];
        }
        for (int i = 0; i != arr2.length; i++) {
            arrAll[index++] = arr2[i];
        }
        Arrays.sort(arrAll);
        return arrAll;
    }

    public static int[] generateSortedArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != len; i++) {
            res[i] = (int) (Math.random() * (maxValue + 1));
        }
        Arrays.sort(res);
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len1 = 10;
        int len2 = 23;
        int maxValue1 = 20;
        int maxValue2 = 100;
        int[] arr1 = generateSortedArray(len1, maxValue1);
        int[] arr2 = generateSortedArray(len2, maxValue2);
        printArray(arr1);
        printArray(arr2);
        int[] sortedAll = getSortedAllArray(arr1, arr2);
        printArray(sortedAll);
        int kth = 17;
        System.out.println(findKthMinNumber(arr1, arr2, kth));
        System.out.println(sortedAll[kth - 1]);

    }
}
