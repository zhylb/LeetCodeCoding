package com.lihd.part06;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/5 13:34
 */
public class Code03JumpGame {

    public static int jump2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int step = 0;
        int border = 0;
        int nextBorder = -1;
        for (int i = 0; i < arr.length; i++) {
            if (i > border) {
                step++;
                border = nextBorder;
            }
            nextBorder = Math.max(nextBorder, i + arr[i]);
        }
        return step;
    }


    public static int minJumpNum(int[] jump) {

        int times = 0;
        int curRange = 0;
        int maxRange = -1;
        for (int i = 0; i < jump.length; i++) {
            if (i > curRange) {
                times ++;
                curRange = maxRange;
                maxRange = -1;
            }
            maxRange = Math.max(maxRange, jump[i] + i);
        }
        return times;
    }

    public static int[] randomArr() {
        return null;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 2, 3, 1, 1, 4 };
        arr = new int[]{3, 3, 2, 1, 2, 5, 8, 1};
        int minJumpNum = minJumpNum(arr);
        System.out.println("minJumpNum = " + minJumpNum);
        System.out.println(jump2(arr));
    }

}
