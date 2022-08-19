package com.lihd.part06;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import java.util.Arrays;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/16 19:39
 */
public class Code04ShuffleProblem {


    public static void shuffle(int[] arr) {
        if (arr == null || arr.length == 0 || (arr.length & 1) != 0) {
            return;
        }
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int len = r - l + 1;
            int realLen = 1;
            int k = 0;
            while (realLen <= len + 1) {
                realLen *= 3;
                k++;
            }
            //正确求出了k的值 和 realLen的值
            realLen = realLen / 3 - 1;
            k --;


            int m = l + (r - l) / 2;
            int half = realLen / 2;
            rotate(arr, l + half, m, m + half);
//            shuffle(arr, l, realLen, k);
            cycles(arr, l, realLen, k);

            l = l + realLen;
            System.out.println(Arrays.toString(arr));
        }
    }

    private static void shuffle(int[] arr, int l, int len, int k) {
        //控制次数
        //开始位置分别是 0, 2, 8, 28
        int begin = 1;
        for (int i = 0; i < k; i++, begin *= 3) {
            int beginIndex = begin + l - 1;
            int endIndex = beginIndex;
            int beginVal = arr[beginIndex];
            while (getIndex(beginIndex, len) != endIndex) {
                arr[beginIndex] = arr[getIndex(beginIndex, len)];
                beginIndex = getIndex(beginIndex, len);
            }
            arr[beginIndex] = beginVal;
            //为什么前面的转反了

        }

    }




    /**
     * 将数组 [l, r]的位置元素,围绕 m,改变次序, m属于l
     * @param arr 要旋转的数组
	 * @param l 开始位置
	 * @param m 中点位置 l结束位置
	 * @param r 结束位置
     * @author lihd
     * @date 2022/8/16 19:45
     */
    public static void rotate(int[] arr, int l, int m, int r) {
        reverse(arr, l, m);
        reverse(arr, m + 1, r);
        reverse(arr, l, r);
    }



    /**
     * 将数组 [l, r]的位置元素,逆序
     * @param arr 要逆序的数组
	 * @param l 逆序的开始位置
	 * @param r 逆序的结束位置
     * @author lihd
     * @date 2022/8/16 19:40
     */
    public static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            swap(arr, l++, r--);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }





    /**
     *
     * @param index 索引 从0开始
	 * @param halfLen 长度
     * @return int 目标索引
     * @author lihd
     * @date 2022/8/16 19:50
     */
    private static int getIndex(int index, int halfLen) {

        if (index < halfLen) {
            return 2 * index + 1;
        } else {
            return (index - halfLen) * 2;
        }

//        if (index <= halfLen) {
//            return 2 * index;
//        } else {
//            return 2 * (index - (halfLen)) - 1;
//        }
    }

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void cycles(int[] arr, int start, int len, int k) {
        // 找到每一个出发位置trigger，一共k个
        // 每一个trigger都进行下标连续推
        // 出发位置是从1开始算的，而数组下标是从0开始算的。
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int preValue = arr[trigger + start - 1];
            int cur = modifyIndex2(trigger, len);
            while (cur != trigger) {
                int tmp = arr[cur + start - 1];
                arr[cur + start - 1] = preValue;
                preValue = tmp;
                cur = modifyIndex2(cur, len);
            }
            arr[cur + start - 1] = preValue;
        }
    }

    public static int modifyIndex2(int i, int len) {
        return (2 * i) % (len + 1);
    }

    public static int modifyIndex1(int i, int len) {
        if (i <= len / 2) {
            return 2 * i;
        } else {
            return 2 * (i - (len / 2)) - 1;
        }
    }



}
