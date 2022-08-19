package com.lihd.part01;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/11 13:19
 */
public class Code03MaxSumOfRectangleNoLargerThanK {


    public static int maxSumSubMatrix(int[][] matrix, int K) {


        int R = matrix.length;
        int C = matrix[0].length;
        int ans = 0;
        for (int begin = 0; begin < R; begin++) {
            int[] arr = new int[C];
            for (int end = begin; end < R; end++) {
                //子数组 [begin ... end]
                //子数组先累加上
                for (int i = 0; i < C; i++) {
                    arr[i] += matrix[end][i];
                }
                //寻找arr[i]的子数组问题即可
                int val = sumLessOrEqualK2(arr, K);
                ans = Math.max(ans, val);
            }
        }
        return ans;
    }


    //下面的代码是从  上一节粘过来的

    public static int sumLessOrEqualK2(int[] arr, int K) {

        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        for (int i = 1; i < sum.length; i++) {
            sum[i] = Math.max(sum[i], sum[i - 1]);
        }
        int ans = 0;
        int s = 0;
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
            int index = binarySearch(sum, s - K);
            if (index != -1) {
                ans = Math.max(ans, s - sum[index]);
            }
        }
        return ans;
    }

    public static int binarySearch(int[] arr, int K) {
        int l = 0;
        int r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (K > arr[m]) {
                l = m + 1;
            }else{
                ans = m;
                r = m - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }


}
