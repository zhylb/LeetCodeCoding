package com.lihd.part04;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/27 21:15
 */
public class Code02Bfprt {

    public static int bfprt(int[] arr, int index) {
        return bfprt(arr, 0, arr.length - 1, index - 1);
    }

    //
    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int val = midInMidArr(arr, L, R);
        int[] partition = partition(arr, L, R, val);
        if (index < partition[0]) {
            return bfprt(arr, L, partition[0] - 1, index);
        } else if (index > partition[1]) {
            return bfprt(arr, partition[1] + 1, R, index);
        } else {
            return arr[index];
        }
    }

    //L - R一定大于1
    public static int midInMidArr(int[] arr, int L, int R) {
        int len = R - L + 1;
        int midArrLen = len % 5 == 0 ? len / 5 : len / 5 + 1;
        int[] midArr = new int[midArrLen];
        int leftIndex;
        int rightIndex;
        for (int i = 0; i < midArrLen; i++) {
            leftIndex = L + i * 5;
            rightIndex = Math.min(leftIndex + 4, R);
            midArr[i] = sortAndReturnMid(arr, leftIndex, rightIndex);
        }
        return bfprt(midArr, 0, midArrLen - 1, midArrLen/2);
    }

    // L , R范围必须合理
    public static int[] partition(int[] arr, int L, int R, int val) {
        int less = L - 1;
        int more = R + 1;
        int i = L;
        while (i < more) {
            if (arr[i] < val) {
                swap( arr, i ++, ++less);
            } else if (arr[i] > val) {
                swap(arr, i, --more);
            } else {
                i ++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static int sortAndReturnMid(int[] arr, int L, int R) {
        insertSort(arr, L, R);
        return arr[L + ((R - L) >> 1)];
    }


    public static void insertSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {8, 5, 3, 6, 9, 7, 4, 1, 2};
        int K = 3;
        System.out.println(bfprt(arr, 0, arr.length - 1, K - 1));
    }
    
}
