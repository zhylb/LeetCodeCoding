package com.lihd.part03;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/27 16:58
 */
public class KMP {

    public static int indexOf(String src, String target) {
        if (src == null || target == null) {
            return -1;
        }
        Character[] s = new Character[src.length()];
        Character[] t = new Character[target.length()];
        char[] chars = src.toCharArray();
        char[] chars1 = target.toCharArray();
        for (int i = 0; i < src.length(); i++) {
            s[i] = chars[i];
        }
        for (int i = 0; i < t.length; i++) {
            t[i] = chars1[i];
        }
        return indexOf(s, t);
    }
    //在 src中寻找 target第一次出现的位置 使用KMP算法
    public static<T> int indexOf(T[] src, T[] target) {
        //规定,两者不为null, 并且src至少有一个长度, target长度不超过src
        if (src == null || target == null || src.length == 0 || target.length == 0 || target.length > src.length) {
            return -1;
        }
        int[] next = getNextArr(target);
        int i = 0;
        int j = 0;
        while (i < src.length && j < target.length) {
            if (src[i] == null &&  target[j] == null || Objects.equals(src[i], target[j])) {
                i ++;
                j ++;
            } else if (next[j] != -1) {// j != 0
                j = next[j];
            } else {
                //此时 j = 0
                i ++;
            }
        }
        return j == target.length ? i - j : -1;
    }

    //保证传入的数组不为null 长度不为0
    public static<T> int[] getNextArr(@NotNull T[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        }
        //此时长度至少为2
        int[] next = new int[arr.length];
        next[0] = -1;
        next[1] = 0;
        int last = next[1];
        int index = 2;
        while (index < arr.length) {
            if ( arr[index - 1] == null && arr[last] == null ||Objects.equals(arr[index - 1], arr[last])) {
                //相等
                next[index++] = ++last;
            } else if (next[last] != -1) {
                last = next[last];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }
}
