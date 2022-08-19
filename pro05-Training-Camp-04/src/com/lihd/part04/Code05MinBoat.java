package com.lihd.part04;

import ans.class04.Code05_MinBoat;
import com.lihd.utils.ArrayUtils;
import static org.junit.Assert.*;

import java.util.Arrays;


/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/13 12:46
 */
public class Code05MinBoat {


    public static int minBoat(int[] arr, int limit) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        //如果外界可以保证有序，则不用排序
        Arrays.sort(arr);

        int N = arr.length;
        if (arr[N - 1] > limit) {
            //最后一个位置的数 > limit 无法完成
            return -1;
        }

        int split = -1;

        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] <= limit / 2) {
                split = i;
                break;
            }
        }

        if (split == -1) {
            //说明 每个数 都 大于 limit/2
            return N;
        }

        //有讨论的必要
        int l = split;
        int r = split + 1;

        int used = 0;
        int unused = 0;

        while (l >= 0) {
            //下面的步骤记得保证 r不越界
            if (r < N && arr[l] + arr[r] <= limit) {
                //情况一
                r++;
                l--;
                used++;
            } else {
                //情况二
                l--;
                unused++;
            }
        }
        //l 一定 == -1 ，r 不一定 在那个位置
        int rightNum = N - r;
        return used + (unused + 1)/2 + rightNum;
    }

    public static void main(String[] args) {
        int minLen = 3;
        int maxLen = 50;
        int minVal = 1;
        int maxVal = 20;
        int limitBegin = 15;
        int limitEnd = 50;
        for (int i = 0; i < 10000; i++) {
            int limit = ArrayUtils.randomVal(limitBegin, limitEnd);
            int[] arr = ArrayUtils.randomNotNullArr(minLen, maxLen, minVal, maxVal);
            Arrays.sort(arr);
            int minBoat = minBoat(arr, limit);
            int minBoat1 = Code05_MinBoat.minBoat(arr, limit);
            assertEquals(minBoat1, minBoat);
        }
        System.out.println("测试成功");

    }




}
