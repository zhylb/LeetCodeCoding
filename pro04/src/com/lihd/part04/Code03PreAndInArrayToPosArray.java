package com.lihd.part04;

import sun.security.util.Length;

import java.util.HashMap;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/16 22:51
 */
public class Code03PreAndInArrayToPosArray {


    public static int[] getPostArr(int[] preArr, int[] inArr) {
        int[] postArr = new int[preArr.length];
        int l = preArr.length - 1;
        generatePostArr(preArr, 0, l, inArr, 0, l, postArr, 0, l);
        return postArr;
    }

    public static int[] getPostArr2(int[] preArr, int[] inArr) {
        int[] postArr = new int[preArr.length];
        int l = preArr.length - 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inArr.length; i++) {
            map.put(inArr[i], i);
        }
        generatePostArr(preArr, 0, l, inArr, 0, l, postArr, 0, l, map);
        return postArr;
    }



    public static void generatePostArr(int[] preArr, int preL, int preR,
                                       int[] inArr, int inL, int inR,
                                       int[] postArr,int postL, int postR) {


        if (preL > preR) {
            return;
        }
        if (preL == preR) {
            postArr[postR] = preArr[preL];
        }
        postArr[postR] = preArr[preL];
        int leftLen = 0;
        for (int i = inL; i <= inR; i++) {
            if (inArr[i] == preArr[preL]) {
                leftLen = i - inL;
                break;
            }
        }
        generatePostArr(preArr, preL + 1, preL + leftLen, inArr, inL , inL + leftLen - 1, postArr, postL, postL + leftLen - 1);
        generatePostArr(preArr, preL + leftLen + 1, preR, inArr , inL + leftLen + 1, inR, postArr, postL + leftLen, postR - 1);
    }

    public static void generatePostArr(int[] preArr, int preL, int preR,
                                       int[] inArr, int inL, int inR,
                                       int[] postArr, int postL, int postR,
                                       HashMap<Integer,Integer> map) {


        if (preL > preR) {
            return;
        }
        if (preL == preR) {
            postArr[postR] = preArr[preL];
        }
        postArr[postR] = preArr[preL];
        Integer integer = map.get(preArr[preL]);
        int leftLen = integer - inL;
        generatePostArr(preArr, preL + 1, preL + leftLen, inArr, inL , inL + leftLen - 1, postArr, postL, postL + leftLen - 1, map);
        generatePostArr(preArr, preL + leftLen + 1, preR, inArr , inL + leftLen + 1, inR, postArr, postL + leftLen, postR - 1, map);
    }


    public static void main(String[] args) {

    }


}
