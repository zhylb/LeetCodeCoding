package com.lihd.part02;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/2 23:22
 */
public class Code03LongestLessSumSubArrayLength {

    public static int maxLengthAwesome(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] minSum = new int[arr.length];
        int[] minSumEnd = new int[arr.length];

        minSum[arr.length - 1] = arr[arr.length - 1];
        minSumEnd[arr.length - 1] = arr.length - 1;
        //从倒数第二个位置开始填表
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSum[i + 1] <= 0) {//为什么用 < 也可以
                minSum[i] = minSum[i + 1] + arr[i];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        //预处理完成
        int R = 0;
        int preSum = 0;
        int ans = 0;
        int index = 0;
        while (index < arr.length) {
            while (R < arr.length && minSum[R] + preSum <= K) {
                preSum += minSum[R];
                R = minSumEnd[R] + 1;
            }

            ans = Math.max(ans, R - index);
            if (index < R) {
                preSum -= arr[index];
            } else {
                R ++;
            }
            index ++;
        }

//        for (int i = 0; i < arr.length; i++) {
//            while (R < arr.length && preSum + minSum[R] <= K) {
//                preSum += minSum[R];
//                R = minSumEnd[R] + 1;
//            }
//            //扩到不能再扩 比较一次答案， 在i + 1之前准备 相减
//            ans = Math.max(ans, R - i);
//            if (i < R) {
//                preSum -= arr[i];
//            } else {
//                R ++;
//            }
//        }
        return ans;
    }

    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
