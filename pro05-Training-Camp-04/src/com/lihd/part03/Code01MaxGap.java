package com.lihd.part03;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/10 16:51
 */
public class Code01MaxGap {

    public static int maxGap(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        //提前过滤 ，因为子过程有 除以（max - min)
        if (max == min) {
            return 0;
        }

        //桶的数量
        int bucket = arr.length + 1;

        boolean[] isEnter = new boolean[bucket];
        int[] maxArr = new int[bucket];
        int[] minArr = new int[bucket];

        for (int j : arr) {
            int index = getBucketIndex(j, max, min, bucket - 1);
            maxArr[index] = isEnter[index] ? Math.max(maxArr[index], j) : j;
            minArr[index] = isEnter[index] ? Math.min(minArr[index], j) : j;
            isEnter[index] = true;
        }

        int ans = 0;
        int lastMax = maxArr[0];
        for (int i = 1; i < bucket; i++) {
            if (isEnter[i]) {
                ans = Math.max(minArr[i] - lastMax, ans);
                lastMax = maxArr[i];
            }
        }
        return ans;
    }

    public static int getBucketIndex(int num,int max, int min, int bucketMaxIndex) {
        return (num - min) * bucketMaxIndex / (max - min);
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 7, 9};
        System.out.println(maxGap(arr));

    }

}
